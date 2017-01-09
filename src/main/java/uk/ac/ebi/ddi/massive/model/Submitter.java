package uk.ac.ebi.ddi.massive.model;

/**
 * @author ypriverol
 */
public class Submitter {

    private String fullName;

    private String email;

    private String institution;

    private String country;

    public Submitter(String fullName) {
        this.fullName = fullName;
    }

    public Submitter(String fullName, String email, String institution, String country) {
        this.fullName = fullName;
        this.email = email;
        this.institution = institution;
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
