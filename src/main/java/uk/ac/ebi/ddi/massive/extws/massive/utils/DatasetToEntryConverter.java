package uk.ac.ebi.ddi.massive.extws.massive.utils;

import uk.ac.ebi.ddi.massive.extws.massive.model.AbstractDataset;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;
import uk.ac.ebi.ddi.massive.extws.massive.model.PrincipalInvestigator;
import uk.ac.ebi.ddi.massive.extws.massive.model.Publication;
import uk.ac.ebi.ddi.massive.model.CvParam;
import uk.ac.ebi.ddi.massive.model.Reference;
import uk.ac.ebi.ddi.massive.utils.Constants;
import uk.ac.ebi.ddi.massive.utils.ReaderMassiveProject;
import uk.ac.ebi.ddi.massive.utils.Utilities;
import uk.ac.ebi.ddi.xml.validator.parser.model.AdditionalFields;
import uk.ac.ebi.ddi.xml.validator.parser.model.Entry;
import uk.ac.ebi.ddi.xml.validator.utils.Field;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.System.in;

/**
 * Created by gaur on 10/1/17.
 */
public class DatasetToEntryConverter extends Utilities{

    private static final String MASSIVE_LINK = "http://massive.ucsd.edu/ProteoSAFe/result.jsp?task=";

    private static final String MASSIVE = "MassIVE";

    private static final String GNPS    = "GNPS";

    public static Entry getEntryFromDataset(DatasetDetail datasetDetail){


        Entry entry = new Entry();

        entry.setName(datasetDetail.getTitle());
        entry.setDescription(datasetDetail.getDescription());
        entry.setAcc(datasetDetail.getId());
        entry.setId(datasetDetail.getId());

        entry.addDate(Field.SUBMISSION_DATE.getName(),transformDate(datasetDetail.getCreated()).toString());

        addFields(Field.OMICS.getName(),transformToOmicsType(datasetDetail),entry);
        addFields(Field.SUBMITTER.getName(),transformSubmitterName(datasetDetail.getPrincipalInvestigator()),entry);
        //addFields("technology_type","",entry);
        entry.addAdditionalField(Field.INSTRUMENT.getName(),datasetDetail.getInstrument());
        entry.addAdditionalField(Field.SPECIE_FIELD.getName(),datasetDetail.getSpecies());
        entry.addAdditionalField(Field.LINK.getName(),MASSIVE_LINK+datasetDetail.getTask()+"&view=advanced_view");
        addFields("dataset_file",transformDatsetFiles(datasetDetail.getFtp()),entry);
        addFields(Field.CURATOR_KEYWORDS.getName(), transformKeywords(datasetDetail.getKeywords()), entry);
        entry.addAdditionalField(Field.REPOSITORY.getName(),transformRepositoryTypes(entry.getAdditionalFieldValues(Field.OMICS.getName())));
        addFields("modification",transformModifications(datasetDetail.getModification()),entry);
        addPubmedId(datasetDetail,entry);

/*        addFields(Field.ENRICH_TITLE.getName(),);
        addFields(Field.ENRICH_ABSTRACT.getName(),);*/

        return entry;
    }





}

