package uk.ac.ebi.ddi.massive.extws.massive.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.ebi.ddi.massive.extws.massive.config.AbstractMassiveWsConfig;
import uk.ac.ebi.ddi.massive.extws.massive.model.*;

import java.util.Map;


/**
 * @author Yasset Perez-Riverol ypriverol
 */
public class DatasetWsClient extends MassiveClient {

    private static final Logger logger = LoggerFactory.getLogger(DatasetWsClient.class);

    /**
     * Default constructor for Ws clients
     *
     * @param config
     */
    public DatasetWsClient(AbstractMassiveWsConfig config) {
        super(config);

    }

    /**
     * Returns the Datasets from MassiVe
     * @return A list of entries and the facets included
     */
    public DatasetList getAllDatasets(){

        String url = String.format("%s://%s/datasets_json.jsp",
                config.getProtocol(), config.getHostName());
        //Todo: Needs to be removed in the future, this is for debugging
        logger.debug(url);


        return this.restTemplate.getForObject(url, DatasetList.class);
    }


    /**
     * This function provides a way to retrieve the information of a dataset from Massive
     * Specially the metadata.
     * @param task the id of the dataset
     * @return the DatasetDetail
     */
    public DatasetDetail getDataset(String task){
        String url = String.format("%s://%s/MassiveServlet?task=%s&function=massiveinformation",
                config.getProtocol(), config.getHostName(), task);
        //Todo: Needs to be removed in the future, this is for debugging
        logger.debug(url);

        return this.restTemplate.getForObject(url, DatasetDetail.class);
    }



}
