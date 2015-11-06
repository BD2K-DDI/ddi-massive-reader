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
    public static Project readProject(DatasetDetail dataset) throws Exception {
        Project proj = new Project();

        proj.setAccession(dataset.getId());
        
        proj.setRepositoryName(MASSIVE);

        proj.setTitle(dataset.getTitle());

        proj.setProjectDescription(dataset.getDescription());

        proj.setInstrument(transformInstrument(dataset.getInstrument()));

        proj.setSpecie(transformSpecies(dataset.getSpecies()));

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

    private static Set<Instrument> transformInstrument(String instrument) {
        return null;
    }


}
