package uk.ac.ebi.ddi.massive.model;

/**
 * Reference information form px submission
 *
 * @author ypriverol
 */
public class Reference {

    private Integer pubmedId;

    private String referenceLine;

    public Integer getPubmedId() {
        return pubmedId;
    }

    public String getReferenceLine() {
        return referenceLine;
    }

    public void setPubmedId(Integer pubmedId) {
        this.pubmedId = pubmedId;
    }

    public void setReferenceLine(String referenceLine) {
        this.referenceLine = referenceLine;
    }
}
