package uk.ac.ebi.ddi.massive.extws.massive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

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

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    @JsonProperty("ftp")
    String ftp;

    @JsonProperty("modifications")
    String modification;

    private List<String> taxonomy;

    private int fileSizeKB = 0;

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

    public void setTaxonomy(List<String> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public List<String> getTaxonomy() {
        return taxonomy;
    }

    public int getFileSizeKB() {
        return fileSizeKB;
    }

    public void setFileSizeKB(int fileSizeKB) {
        this.fileSizeKB = fileSizeKB;
    }

    @Override
    public String toString() {
        return "DatasetDetail{" +
                "description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", publications=" + Arrays.toString(publications) +
                ", pxAccession='" + pxAccession + '\'' +
                ", converted='" + converted + '\'' +
                ", subscriptions='" + subscriptions + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", hasAccess='" + hasAccess + '\'' +
                ", fileCount='" + fileCount + '\'' +
                ", keywords='" + keywords + '\'' +
                ", ftp='" + ftp + '\'' +
                '}';
    }


}
