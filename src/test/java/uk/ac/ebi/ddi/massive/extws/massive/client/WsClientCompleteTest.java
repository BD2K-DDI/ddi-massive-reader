package uk.ac.ebi.ddi.massive.extws.massive.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.ddi.massive.extws.massive.config.MassiveWsConfigProd;
import uk.ac.ebi.ddi.massive.extws.massive.model.DataSetSummary;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetList;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class WsClientCompleteTest {

    @Autowired
    MassiveWsConfigProd massiveWsConfig;

    ISODetasetsWsClient datasetISOWsClient;

    DatasetWsClient datasetWsClient;

    @Before
    public void setUp() throws Exception {
        datasetWsClient = new DatasetWsClient(massiveWsConfig);
        datasetISOWsClient = new ISODetasetsWsClient(massiveWsConfig);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllDatasets() throws Exception {

        DatasetList list = datasetISOWsClient.getAllDatasets();

        if(list != null && list.datasets != null && list.datasets.length > 0){
            DataSetSummary[] datasets = Arrays.copyOfRange(list.datasets, 0, (new Random().nextInt(50)));
            for(DataSetSummary dataSetSummary: datasets){
                System.out.println("Task: " + dataSetSummary.getTask());
                DatasetDetail dataset = datasetWsClient.getDataset(dataSetSummary.getTask());
                System.out.println(dataset.toString());
            }
        }
    }

}
