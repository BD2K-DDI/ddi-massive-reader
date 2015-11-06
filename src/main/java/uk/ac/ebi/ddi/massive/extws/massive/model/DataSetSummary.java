package uk.ac.ebi.ddi.massive.extws.massive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 18/05/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class DataSetSummary extends AbstractDataset{

    @JsonProperty("dataset")
    String id;

    @JsonProperty("datasetNum")
    String datasetNum;

    @JsonProperty("site")
    String site;

    @JsonProperty("flowname")
    String flowName;

    @JsonProperty("createdMills")
    String createdMills;

    @JsonProperty("fileCount")
    String fileCount;

    @JsonProperty("fileSizeKB")
    String fileSize;

    @JsonProperty("status")
    String status;

    @JsonProperty("hash")
    String hash;

    @JsonProperty("task")
    String task;

    @JsonProperty("id")
    String ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetNum() {
        return datasetNum;
    }

    public void setDatasetNum(String datasetNum) {
        this.datasetNum = datasetNum;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getCreatedMills() {
        return createdMills;
    }

    public void setCreatedMills(String createdMills) {
        this.createdMills = createdMills;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "DataSetSummary{" +
                "id='" + id + '\'' +
                ", datasetNum='" + datasetNum + '\'' +
                ", site='" + site + '\'' +
                ", flowName='" + flowName + '\'' +
                ", createdMills='" + createdMills + '\'' +
                ", fileCount='" + fileCount + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", status='" + status + '\'' +
                ", hash='" + hash + '\'' +
                ", task='" + task + '\'' +
                ", ids='" + ids + '\'' +
                '}';
    }
}