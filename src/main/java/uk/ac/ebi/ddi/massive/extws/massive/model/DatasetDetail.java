package uk.ac.ebi.ddi.massive.extws.massive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 06/11/15
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class DatasetDetail extends AbstractDataset{

    @JsonProperty("description")
    String description;

    @JsonProperty("dataset_id")
    String id;

    @JsonProperty("publications")
    Publication[] publications;

    @JsonProperty("pxaccession")
    String pxAccession;

    @JsonProperty("convertedandcomputable")
    String converted;

    @JsonProperty("subscriptsions")
    String subscriptions;

    @JsonProperty("filesize")
    String fileSize;

    @JsonProperty("has_access")
    String hasAccess;

    @JsonProperty("filecount")
    String fileCount;

    @JsonProperty("keywords")
    String keywords;

    @JsonProperty("ftp")
    String ftp;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Publication[] getPublications() {
        return publications;
    }

    public void setPublications(Publication[] publications) {
        this.publications = publications;
    }

    public String getPxAccession() {
        return pxAccession;
    }

    public void setPxAccession(String pxAccession) {
        this.pxAccession = pxAccession;
    }

    public String getConverted() {
        return converted;
    }

    public void setConverted(String converted) {
        this.converted = converted;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(String hasAccess) {
        this.hasAccess = hasAccess;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFtp() {
        return ftp;
    }

    public void setFtp(String ftp) {
        this.ftp = ftp;
    }
}
