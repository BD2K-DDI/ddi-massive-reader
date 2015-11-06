package uk.ac.ebi.ddi.massive.extws.massive.config;

/**
 * This class help to configure the web-service provider that would be used.
 */
public class MassiveWsConfigProd extends AbstractMassiveWsConfig {

    public MassiveWsConfigProd() {
        super("http", "massive.ucsd.edu/ProteoSAFe");
    }
}
