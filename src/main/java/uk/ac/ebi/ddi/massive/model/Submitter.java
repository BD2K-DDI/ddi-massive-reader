package uk.ac.ebi.ddi.massive.model;

/**
 * @author ypriverol
 */
public class Submitter {

    //First name
    private String firstName;

    //Last Name
    private String lastName;

    //email
    private String email;

    //Affiliation
    private String affiliation;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setAffiliation(String department, String institute){
        this.affiliation = "";
        if(department != null && department.length() > 0)
            this.affiliation += department;
        if(institute != null && institute.length() > 0){
            if(this.affiliation.length() > 0)
               this.affiliation += ", " + institute;
            else
                this.affiliation += institute;
        }
    }

    public String getName(){
        String name = firstName;
        if(lastName != null && lastName.length() > 0){
            name = name + " " + lastName;
        }
        return name;
    }
}
