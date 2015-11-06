package uk.ac.ebi.ddi.massive.model;

/**
 *  Simple class to parse the CV Params
 *  @author Yasset Perez-Riverol
 */
public class CvParam {

    // Accession of the CvTerm
    String accession;

    // Name of the CvTerm
    String name;

    // cvLabel of the CVTerm
    String cvLabel;

    // value of the CVTerm
    String value;

    public CvParam(String accession, String name, String cvLabel, String value) {
        this.accession = accession;
        this.name = name;
        this.cvLabel = cvLabel;
        this.value = value;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvLabel() {
        return cvLabel;
    }

    public void setCvLabel(String cvLabel) {
        this.cvLabel = cvLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
