package uk.ac.ebi.ddi.massive.extws.massive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 18/05/2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class DatasetList {

    @JsonProperty("datasets")
    public DataSetSummary[] datasets;

    public DataSetSummary[] getDatasets() {
        return datasets;
    }

    public void setDatasets(DataSetSummary[] datasets) {
        this.datasets = datasets;
    }
}
