package uk.ac.ebi.ddi.massive.extws.massive.client;

import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.ddi.massive.extws.massive.config.AbstractMassiveWsConfig;

/**
 * Abstract client to query the EBI search.
 *
 * @author ypriverol
 */
public class MassiveClient {

    protected RestTemplate restTemplate;

    protected AbstractMassiveWsConfig config;

    /**
     * Default constructor for Archive clients
     * @param config
     */
    public MassiveClient(AbstractMassiveWsConfig config){
        this.config = config;
        this.restTemplate = new RestTemplate();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AbstractMassiveWsConfig getConfig() {
        return config;
    }

    public void setConfig(AbstractMassiveWsConfig config) {
        this.config = config;
    }
}
