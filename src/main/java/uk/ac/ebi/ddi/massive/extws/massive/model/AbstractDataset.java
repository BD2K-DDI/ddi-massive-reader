package uk.ac.ebi.ddi.massive.extws.massive.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 06/11/15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractDataset {

    @JsonProperty("title")
    String title;

    @JsonProperty("pi")
    String principalInvestigator;

    @JsonProperty("instrument")
    String instrument;

    @JsonProperty("complete")
    String complete;

    @JsonProperty("private")
    String privateStatus;

    @JsonProperty("user")
    String user;

    @JsonProperty("species")
    String species;


    @JsonProperty("created")
    String created;

    @JsonProperty("task")
    String task;

    String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(String principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(String privateStatus) {
        this.privateStatus = privateStatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    @Override
    public String toString() {
        return "AbstractDataset{" +
                "title='" + title + '\'' +
                ", principalInvestigator='" + principalInvestigator + '\'' +
                ", instrument='" + instrument + '\'' +
                ", complete='" + complete + '\'' +
                ", privateStatus='" + privateStatus + '\'' +
                ", user='" + user + '\'' +
                ", species='" + species + '\'' +
                ", created='" + created + '\'' +
                ", task='" + task + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
