package uk.ac.ebi.ddi.massive.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;
import uk.ac.ebi.ddi.massive.extws.massive.model.PrincipalInvestigator;
import uk.ac.ebi.ddi.massive.extws.massive.model.Publication;
import uk.ac.ebi.ddi.massive.model.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static uk.ac.ebi.ddi.massive.utils.Utilities.*;


/**
 * Reader using SAX the XML file
 * @author ypriverol
 */
public class ReaderMassiveProject {

    private static final Logger logger = LoggerFactory.getLogger(ReaderMassiveProject.class);

    private static final String MASSIVE_LINK = "http://massive.ucsd.edu/ProteoSAFe/result.jsp?task=";

    /**
     * This method read the PX summary file and return a project structure to be use by the
     * EBE exporter.
     * @return Project object model
     */
    public static Project readProject(DatasetDetail dataset, List<Specie> species) throws Exception {

        Project proj = new Project();

        proj.setAccession(dataset.getId());
        
        proj.setTitle(dataset.getTitle());

        proj.setProjectDescription(dataset.getDescription());

        proj.setInstrument(transformInstrument(dataset.getInstrument()));

        proj.setSpecies(species);

        proj.setSubmitter(transformSubmitter(dataset.getPrincipalInvestigator()));

        proj.setDatasetLink(dataset.getUrl());

        proj.setSubmissionDate(transformDate(dataset.getCreated()));

        proj.setDataProcessingProtocol(transformDataProcessing(dataset));

        proj.setProjectTags(transformKeywords(dataset.getKeywords()));

        proj.setOmicsType(transformToOmicsType(dataset));

        proj.setRepositoryName(transformRepositoryTypes(proj.getOmicsType()));

        proj.setHash(dataset.getTask());

        proj.setDatasetLink(MASSIVE_LINK+dataset.getTask()+"&view=advanced_view");

        proj.setReferences(transformReferences(dataset.getPublications()));

        proj.setModifications(transformModificationsParam(dataset.getModification()));

        proj.setSubmitter(transformSubmitter(dataset.getPrincipalInvestigator()));

        proj.setDataFiles(transformDatsetFiles(dataset.getFtp()));

        return proj;
    }


}
