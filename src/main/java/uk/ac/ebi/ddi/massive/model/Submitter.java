package uk.ac.ebi.ddi.massive.model;

/**
 * @author ypriverol
 */
public class Submitter {

    private String fullName;

    public Submitter(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
