package uk.ac.ebi.ddi.massive.extws.massive.client;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
        this.restTemplate = new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(20000);
        factory.setConnectTimeout(20000);
        return factory;
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
