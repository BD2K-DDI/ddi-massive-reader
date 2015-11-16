package uk.ac.ebi.ddi.massive.model;



import java.util.*;

/**
 * General Project information of about the Project
 * @author ypriverol
 */
public class Project {

    private String accession;

    private String repositoryName;

    private String title;

    private String projectDescription;

    private List<Specie> species;

    private Date submissionDate;

    private List<String> dataProcessingProtocol;

    private Set<Instrument> instrument;

    private List<String> experimentTypes;

    private List<String> projectTags;

    private List<Submitter> submitter;

    private String datasetLink;

    private Set<String> factors;

    private String hash = null;

    private List<String> omicsType;

    private List<Reference> references;

    private List<CvParam> modifications;

    private List<String> dataFiles;

    /**
     * Default constructor create a List of every list-based attribute
     */
    public Project() {
        projectTags           = Collections.emptyList();
        experimentTypes       = Collections.emptyList();
    }

    public boolean isPublicProject() {
        return true;
    }

    public String getAccession() {
        return accession;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getTitle() {
        return title;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public List<Specie> getSpecies() {
        return species;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public List<String> getDataProcessingProtocol() {
        return dataProcessingProtocol;
    }

    public List<String> getExperimentTypes() {
        return experimentTypes;
    }

    public List<String> getProjectTags() {
        return projectTags;
    }

    public List<Submitter> getSubmitter() {
        return submitter;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setTitle(String title) {
        if(title != null && !title.equalsIgnoreCase("null"))
        this.title = title;
    }

    public void setProjectDescription(String projectDescription) {
        if(projectDescription != null && !projectDescription.equalsIgnoreCase("null"))
            this.projectDescription = projectDescription;
    }

    public void setSpecies(List<Specie> species) {
        this.species = species;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setDataProcessingProtocol(List<String> dataProcessingProtocol) {
        this.dataProcessingProtocol = dataProcessingProtocol;
    }

    public void setInstrument(Set<Instrument> instrument) {
        this.instrument = instrument;
    }

    public void setExperimentTypes(List<String> experimentTypes) {
        this.experimentTypes = experimentTypes;
    }

    public void setSubmitter(List<Submitter> submitter) {
        this.submitter = submitter;
    }

    public String getDatasetLink() {
        return datasetLink;
    }

    public void setDatasetLink(String datasetLink) {
        this.datasetLink = datasetLink;
    }

    public Set<Instrument> getInstrument() {
        return instrument;
    }

    public void setFactors(Set<String> factors) {
        this.factors = factors;
    }

    public Set<String> getFactors() {
        return factors;
    }

    public void setProjectTags(List<String> projectTags) {
        this.projectTags = projectTags;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<String> getOmicsType() {
        return omicsType;
    }

    public void setOmicsType(List<String> omicsType) {
        this.omicsType = omicsType;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<CvParam> getModifications() {
        return modifications;
    }

    public void setModifications(List<CvParam> modifications) {
        this.modifications = modifications;
    }

    public List<String> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(List<String> dataFiles) {
        this.dataFiles = dataFiles;
    }
}
