package uk.ac.ebi.ddi.massive.extws.massive.config;

/**
 * @author jadianes
 * @author ypriverol
 *
 */
public abstract class AbstractMassiveWsConfig {

    private String hostName;
    private String protocol;

    protected AbstractMassiveWsConfig(String protocol, String hostName) {
        this.hostName = hostName;
        this.protocol = protocol;
    }

    public String getHostName() {
        return hostName;
    }


    public String getProtocol() {
        return protocol;
    }

}
