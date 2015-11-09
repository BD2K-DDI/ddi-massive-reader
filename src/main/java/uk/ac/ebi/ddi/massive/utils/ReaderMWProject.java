package uk.ac.ebi.ddi.massive.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;
import uk.ac.ebi.ddi.massive.model.Instrument;
import uk.ac.ebi.ddi.massive.model.Project;
import uk.ac.ebi.ddi.massive.model.Specie;
import uk.ac.ebi.ddi.massive.model.Submitter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Reader using SAX the XML file
 * @author ypriverol
 */
public class ReaderMWProject {

    private static final Logger logger = LoggerFactory.getLogger(ReaderMWProject.class);

    private static final String MASSIVE = "massive";

    private static final String MASSIVE_LINK = "http://massive.ucsd.edu/ProteoSAFe/result.jsp?task=";

    /**
     * This method read the PX summary file and return a project structure to be use by the
     * EBE exporter.
     * @return Project object model
     */
    public static Project readProject(DatasetDetail dataset, List<Specie> species) throws Exception {

        Project proj = new Project();

        proj.setAccession(dataset.getId());
        
        proj.setRepositoryName(MASSIVE);

        proj.setTitle(dataset.getTitle());

        proj.setProjectDescription(dataset.getDescription());

        proj.setInstrument(transformInstrument(dataset.getInstrument()));

        proj.setSpecies(species);

        proj.setSubmitter(transformSubmitter(dataset.getPrincipalInvestigator()));

        proj.setDatasetLink(dataset.getUrl());

        proj.setSubmissionDate(transformDate(dataset.getCreated()));

        proj.setDataProcessingProtocol(transformDataProcessing(dataset));

        return proj;
    }

    private static List<String> transformDataProcessing(DatasetDetail dataset) {
        return null;
    }

    private static Submitter transformSubmitter(String principalInvestigator) {
        return null;

    }

    private static Date transformDate(String created) {
        return null;
    }


    private static Specie transformSpecies(String species) {
        return null;
    }

    /**
     * This function converts an Instrument String to a list of different Instruments, also it trim every
     * Instrument to remove the corresponding whitespace at the end of the String.
     * @param instrument
     * @return
     */
    private static Set<Instrument> transformInstrument(String instrument) {
        Set<Instrument> instruments = new HashSet<>();
        if(instrument != null && !instrument.isEmpty()){
            String[] instrumentArray = instrument.split(Constants.MASSIVE_SEPARATOR);
            for(String instrumentTrimed: instrumentArray)
                instruments.add(new Instrument(null, instrumentTrimed.trim()));
        }
        return instruments;
    }




}
