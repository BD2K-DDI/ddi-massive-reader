package uk.ac.ebi.ddi.massive.utils;

import uk.ac.ebi.ddi.massive.extws.massive.client.ISODetasetsWsClient;
import uk.ac.ebi.ddi.massive.extws.massive.config.AbstractMassiveWsConfig;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetList;
import uk.ac.ebi.ddi.massive.extws.massive.model.PrincipalInvestigator;
import uk.ac.ebi.ddi.massive.extws.massive.model.Publication;
import uk.ac.ebi.ddi.massive.model.CvParam;
import uk.ac.ebi.ddi.massive.model.Instrument;
import uk.ac.ebi.ddi.massive.model.Reference;
import uk.ac.ebi.ddi.massive.model.Submitter;
import uk.ac.ebi.ddi.xml.validator.parser.OmicsXMLFile;
import uk.ac.ebi.ddi.xml.validator.parser.marshaller.OmicsDataMarshaller;
import uk.ac.ebi.ddi.xml.validator.parser.model.Database;
import uk.ac.ebi.ddi.xml.validator.parser.model.Entries;
import uk.ac.ebi.ddi.xml.validator.parser.model.Entry;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaur on 17/1/17.
 */
public class Utilities {

    public static List<String> databases = Arrays.asList("MassIVE", "GNPS");

    private static final String MASSIVE_DESCRIPTION = "Massive is a community resource developed by the NIH-funded Center for Computational Mass Spectrometry to promote the global, free exchange of mass spectrometry data.";

    private static final String GNPS_DESCRIPTION = "The Global Natural Products Social Molecular Networking (GNPS) is a platform for providing an overview of the molecular features in mass spectrometry based metabolomics by comparing fragmentation patterns to identify chemical relationships.";

    private static final String MASSIVE = "MassIVE";

    private static final String GNPS    = "GNPS";

    private static final String PRIDE_FILTER = "PRIDE:0000398";

    private static final String PHD_FILTER = "PhD";

    private static final String AND_FILTER = "and";

    private static final String RELEASE_VERSION = "1.0";

    private static final String RELEASE_DATE = "2017-01-16";


    /**
     * This function select the name of the repository depending of the omics type provided
     * if the dataset contains metabolomics data, the database is GNPS, if not the database is MASSIVE (Default)
     *
     * @param omicsType
     * @return
     */
    public static String transformRepositoryTypes(List<String> omicsType) {
        if(omicsType != null && !omicsType.isEmpty()){
            for(String omics: omicsType)
                if(omics.equalsIgnoreCase(Constants.METABOLOMICS_TYPE))
                    return GNPS;
        }
        return MASSIVE;
    }


    public static List<String> transformModifications(String modification) {
        List<String> modifications = new ArrayList<>();
        if(modification != null && !modification.isEmpty()){
            String[] modArray = modification.split(Constants.MASSIVE_SEPARATOR);
            for(String mod: modArray){
                if(!mod.toLowerCase().contains(PRIDE_FILTER.toLowerCase()) && (mod.split("\"").length >=2)){
                    String name = mod.split("\"")[1];
                    modifications.add(name);
                }
            }
        }
        return modifications;
    }

    public static List<CvParam> transformModificationsParam(String modification) {
        List<CvParam> modifications = new ArrayList<>();
        if(modification != null && !modification.isEmpty()){
            String[] modArray = modification.split(Constants.MASSIVE_SEPARATOR);
            for(String mod: modArray){
                if(!mod.toLowerCase().contains(PRIDE_FILTER.toLowerCase()) && (mod.split("\"").length >=2)){
                    String name = mod.split("\"")[1];
                    modifications.add(new CvParam(null,name,null,null));
                }
            }
        }
        return modifications;
    }

    /**
     * Convert the String date to a Date.
     * @param created String date
     * @return Date
     */
    public static Date transformDate(String created) {
        if(created != null && !created.isEmpty()){
            DateFormat df1 = new SimpleDateFormat("MMM. d, yyyy, h:mm a");
            try {
                return df1.parse(created);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Convert citations from Massive to References
     * @param publications
     * @return
     */
    public static List<Reference> transformReferences(Publication[] publications) {
        List<Reference> references = new ArrayList<>();
        if(publications != null && publications.length > 0){
            for(Publication publication:publications){
                if(publication != null){
                    String citation = (publication.getTitle() != null)?publication.getTitle():"";
                    citation = (publication.getAuthors() != null)? citation + ". " + publication.getAuthors():citation;
                    citation = (publication.getCitation() != null)?citation + ". " + publication.getCitation():citation;
                    references.add(new Reference(publication.getPubmedId(), (citation.isEmpty())?null:citation));
                }
            }
        }
        return references;

    }


    /**
     * This function transform alist of keywords from Massive to a list of omicsDI tags
     * also remove the whitespace and the end of each word.
     * @param keywords String with keywords from massive
     * @return List of String Tags
     */
    public static List<String> transformKeywords(String keywords) {
        List<String> resultKeys = new ArrayList<>();
        if(keywords != null && !keywords.isEmpty()){
            String[] instrumentArray = keywords.split(Constants.MASSIVE_SEPARATOR);
            for(String key: instrumentArray)
                resultKeys.add(key.trim());

        }
        return resultKeys;
    }

    public static List<String> transformSubmitterName(PrincipalInvestigator[] principalInvestigator) {
        String[] submitterArr = new String[principalInvestigator.length];
        if(principalInvestigator != null && principalInvestigator.length > 0){
            for(int i = 0; i < principalInvestigator.length; i++){
                String principalInvestigatorName = principalInvestigator[i].getName().replace(PHD_FILTER, ",").replace(AND_FILTER, ",");
                submitterArr = principalInvestigatorName.split(",");
            }
        }
        return Arrays.asList(submitterArr);
    }

    /**
     * Get the omicsType from each description and metadata, The GNPS is a shortcut because we know that all of this
     * datasets are metabolomics datasets. In this function we also set the Omics Respository
     * @param dataset MAssive dataset
     * @return List of OmicsDI Types
     */
    public static List<String> transformToOmicsType(DatasetDetail dataset) {
        List<String> types = new ArrayList<>();
        types.add(Constants.PROTEOMICS_TYPE);

        if(dataset != null && dataset.getKeywords() != null){
            if(dataset.getKeywords().toLowerCase().contains(Constants.METABOLOMICS_PATTERN) ||
                    dataset.getKeywords().toLowerCase().contains(Constants.METABOLITE_PATTERN) ||
                    (dataset.getTitle() != null && dataset.getTitle().toUpperCase().contains(GNPS))){
                types.add(Constants.METABOLOMICS_TYPE);
                types.remove(Constants.PROTEOMICS_TYPE);
            }
        }
        return types;
    }

    public static void addPubmedId(DatasetDetail datasetDetail,Entry entry) {
        List<Reference> references = transformReferences(datasetDetail.getPublications());
        if (references != null && !references.isEmpty()) {
            references.stream().filter(reference -> reference.getPubmedId() != null).forEach(reference -> {
                entry.addCrossReferenceValue("pubmed",reference.getPubmedId().toString());
            });
        }
    }

    public static <T> void addFields(String name,List<T> values,Entry entry) {
        for(T value:values) {
            entry.addAdditionalField(name,value.toString());
        }
    }

    public static List<String> transformDatsetFiles(String ftp) {
        List<String> datsetFTP = new ArrayList<>();
        if(ftp != null && !ftp.isEmpty())
            datsetFTP.add(ftp);
        return datsetFTP;
    }

    public static DatasetList getAllDatasets(AbstractMassiveWsConfig configProd) throws Exception {

        ISODetasetsWsClient isoClient = new ISODetasetsWsClient(configProd);

        DatasetList datasets = isoClient.getAllDatasets();

        return datasets;
    }

    public static Database gnpsDatabase() {
        return getDatabase(databases.get(1),GNPS_DESCRIPTION);
    }

    public static Database massiveDatabase(){
        return getDatabase(databases.get(0),MASSIVE_DESCRIPTION);
    }


    public static Database getDatabase(String databaseName,String description){
        Database database = new Database();
        database.setDescription(description);
        database.setRelease(RELEASE_VERSION);
        database.setReleaseDate(RELEASE_DATE);
        database.setName(databaseName);
        return database;
    }

    /**
     * Converts each Principal investigator into a submitter.
     *
     * @param principalInvestigator Principal investigator
     * @return A List of submitters
     */
    public static List<Submitter> transformSubmitter(PrincipalInvestigator[] principalInvestigator) {
        List<Submitter> submitters = new ArrayList<>();
        if(principalInvestigator != null && principalInvestigator.length > 0){
            for(int i = 0; i < principalInvestigator.length; i++){
                String principalInvestigatorName = principalInvestigator[i].getName().replace(PHD_FILTER, ",").replace(AND_FILTER, ",");
                String[] submitterArr = principalInvestigatorName.split(",");
                for(String submitterString: submitterArr)
                    if(!submitterString.isEmpty())
                        submitters.add(new Submitter(submitterString.trim(), principalInvestigator[i].getEmail(), principalInvestigator[i].getInstitution(), principalInvestigator[i].getCountry()));
            }
        }
        return submitters;
    }

    public static List<String> transformDataProcessing(DatasetDetail dataset) {
        return null;
    }


    /**
     * This function converts an Instrument String to a list of different Instruments, also it trim every
     * Instrument to remove the corresponding whitespace at the end of the String.
     * @param instrument
     * @return
     */
    public static Set<Instrument> transformInstrument(String instrument) {
        Set<Instrument> instruments = new HashSet<>();
        if(instrument != null && !instrument.isEmpty()){
            String[] instrumentArray = instrument.split(Constants.MASSIVE_SEPARATOR);
            for(String instrumentTrimed: instrumentArray)
                instruments.add(new Instrument(null, instrumentTrimed.trim()));
        }
        return instruments;
    }

    public static void generateXMLFile(Entries argEntries, String databaseName, FileWriter fw, File file){

        OmicsDataMarshaller mm = new OmicsDataMarshaller();

        Database database;

        if(databaseName == databases.get(0)) {
            database = massiveDatabase();
        }
        else if(databaseName == databases.get(1)){
            database = gnpsDatabase();
        }
        else {
            database = massiveDatabase();
        }
        database.setEntryCount(argEntries.getEntry().size());
        Entries entries = argEntries;
        database.setEntries(entries);
        mm.marshall(database, fw);

        OmicsXMLFile.isSchemaValid(file);
    }

}
