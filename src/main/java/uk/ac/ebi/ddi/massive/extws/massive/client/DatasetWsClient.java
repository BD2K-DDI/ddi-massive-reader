package uk.ac.ebi.ddi.massive.extws.massive.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.ebi.ddi.massive.extws.massive.config.AbstractMassiveWsConfig;
import uk.ac.ebi.ddi.massive.extws.massive.model.*;
import uk.ac.ebi.ddi.massive.extws.massive.utils.CustomHttpMessageConverter;


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
        this.restTemplate.getMessageConverters().add(new CustomHttpMessageConverter());
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
