package uk.ac.ebi.ddi.massive.utils;

/**
 * @author ypriverol
 */
public class Constants {

    public static final String ROOTNODE_TAG             = "ProteomeXchangeDataset";
    public static final String DATASET_SUMMARY_TAG      = "DatasetSummary";
    public static final String PXID_TAG                 = "id";
    public static final String PXTITLE_TAG              = "title";
    public static final String PXREPO_TAG               = "hostingRepository";
    public static final String PXANOUNDATE_TAG          = "announceDate";
    public static final String PXDESC_TAG               = "Description";
    public static final String PXREVIEW_TAG             = "ReviewLevel";

    /**
     * Ontology Terms Constants to be handle inside the system
     */
    public static final String TAXONOMY_ACCESSION       = "MS:1001467";
    public static final String SUBMITTER_ACCESSION      = "MS:1002037";
    public static final String LABHEAD_ACCESSION        = "MS:1002332";
    public static final String SUBMITTERKEY_ACCESSION   = "MS:1001925";

    public static final String CURATORKEY_ACCESSION     = "MS:1001926";
    public static final String MASSIVEURL_ACCESSION     = "MS:1002488";
    public static final String PASSELURL_ACCESSION      = "MS:1002420";
    public static final String PUBMED_ACCESSION         = "MS:1000879";

    public static final String METABOLOMICS_TYPE        = "Metabolomics";
    public static final String PROTEOMICS_TYPE          = "Proteomics";

    public static final String METABOLOMICS_PATTERN     = "metabolomi";
    public static final String METABOLITE_PATTERN       = "metabolit";



    /**
     * Some keywords to be parse
     */
    public static final String SRM_KEYWORD              = "SRM";
    public static final String PXURL                    = "http://proteomecentral.proteomexchange.org/cgi/GetDataset?ID=";

    public static final String MASSIVE_SEPARATOR        = " \\| ";

}
