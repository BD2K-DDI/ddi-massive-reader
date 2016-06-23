package uk.ac.ebi.ddi.massive.extws.massive.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ebi.ddi.massive.extws.massive.config.AbstractMassiveWsConfig;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetList;
import uk.ac.ebi.ddi.massive.extws.massive.utils.HttpDownload;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
public class ISODetasetsWsClient extends MassiveClient{

    private ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

    /**
     * Default constructor for Archive clients
     *
     * @param config
     */
    public ISODetasetsWsClient(AbstractMassiveWsConfig config) {
        super(config);
    }
    public DatasetList getAllDatasets(){
        String url = String.format("%s://%s/datasets_json.jsp", config.getProtocol(), config.getHostName());

        try {
            InputStream in = HttpDownload.getPage(url);
            InputStreamReader isoInput = new InputStreamReader(in, Charset.forName("UTF-8"));
            return mapper.readValue(isoInput, DatasetList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
