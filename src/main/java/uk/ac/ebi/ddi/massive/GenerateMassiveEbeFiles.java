package uk.ac.ebi.ddi.massive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.ac.ebi.ddi.massive.extws.massive.client.DatasetWsClient;

import uk.ac.ebi.ddi.massive.extws.massive.client.ISODetasetsWsClient;
import uk.ac.ebi.ddi.massive.extws.massive.config.MassiveWsConfigProd;
import uk.ac.ebi.ddi.massive.extws.massive.filters.DatasetSummarySizeFilter;
import uk.ac.ebi.ddi.massive.extws.massive.filters.DatasetSummaryTrancheFilter;
import uk.ac.ebi.ddi.massive.extws.massive.filters.DatasetSummaryUserFilter;
import uk.ac.ebi.ddi.massive.extws.massive.model.*;
import uk.ac.ebi.ddi.massive.model.Project;

import uk.ac.ebi.ddi.massive.model.Specie;
import uk.ac.ebi.ddi.massive.utils.Constants;
import uk.ac.ebi.ddi.massive.utils.ReaderMassiveProject;
import uk.ac.ebi.ddi.massive.utils.WriterEBeyeXML;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This program takes a datasets from massive and convert them into DDI experiments
 *
 * @author Yasset Perez-Riverol
 */

public class GenerateMassiveEbeFiles {

    private static final Logger logger = LoggerFactory.getLogger(GenerateMassiveEbeFiles.class);

    private static List<String> databases = Arrays.asList("MassIVE", "GNPS");

  /**
   * This program generate the massive files in two different type of files MASSIVE and GNPS Files. The MASSIVE
   * files correspond to proteomics datasets and the GNPS correspond to metabolomics datasets.
   *
   * @param args
   */
    public static void main(String[] args) {

        String outputFolder = null;

        if (args != null && args.length > 0 && args[0] != null)
            outputFolder = args[0];
        else {
            System.exit(-1);
        }

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");
        MassiveWsConfigProd mwWsConfigProd = (MassiveWsConfigProd) ctx.getBean("mwWsConfig");

        try {
            GenerateMassiveEbeFiles.generateMWXMLfiles(mwWsConfigProd, outputFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateMWXMLfiles(MassiveWsConfigProd configProd, String outputFolder) throws Exception {

        DatasetWsClient datasetWsClient = new DatasetWsClient(configProd);

        ISODetasetsWsClient isoClient = new ISODetasetsWsClient(configProd);

        DatasetList datasets = isoClient.getAllDatasets();

        if (datasets != null && datasets.datasets != null) {

            List<DataSetSummary> dataSetSummaries = new ArrayList<>(Arrays.asList(datasets.datasets));

            logger.debug("number of datasets: " + dataSetSummaries.size());

            dataSetSummaries.parallelStream().forEach( dataset -> {
                if(dataset.getTask() != null){
                    try{
                        DatasetDetail datasetDetail = datasetWsClient.getDataset(dataset.getTask());

                        if(datasetDetail != null && datasetDetail.getId() != null
                                && new DatasetSummarySizeFilter(10).apply(dataset)
                                && new DatasetSummarySizeFilter(1).apply(dataset)
                                && !(new DatasetSummaryUserFilter("tranche_mbraga").apply(dataset))
                                && (dataset.getTitle() != null && !dataset.getTitle().isEmpty())
                                && new DatasetSummaryTrancheFilter<>().apply(dataset)){

                            if(dataset.getCreated() != null)
                                datasetDetail.setCreated(dataset.getCreated());

                            if(datasetDetail.getSpecies() != null){
                                String[] species = datasetDetail.getSpecies().split(Constants.MASSIVE_SEPARATOR);
                                List<Specie> taxonomies = new ArrayList<>();
                                if(species.length > 1){
                                    for(String specie: species){
                                        specie = new String(specie.getBytes(), "UTF-8");
                                        taxonomies.add(new Specie(specie, null));
                                    }
                                }else{
                                    taxonomies.add(new Specie(datasetDetail.getSpecies(), null));
                                }

                                Project proj = ReaderMassiveProject.readProject(datasetDetail, taxonomies);
                                WriterEBeyeXML writer = new WriterEBeyeXML(proj, new File(outputFolder));
                                writer.generate();
                            }
                        }
                    }catch (Exception e){
                        logger.debug(e.getMessage());
                    }
                }
                logger.info(dataset.getHash());
            });

        }
    }
}
