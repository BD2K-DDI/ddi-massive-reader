package uk.ac.ebi.ddi.massive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.ac.ebi.ddi.massive.extws.entrez.client.taxonomy.TaxonomyWsClient;
import uk.ac.ebi.ddi.massive.extws.entrez.config.TaxWsConfigProd;
import uk.ac.ebi.ddi.massive.extws.entrez.ncbiresult.NCBITaxResult;
import uk.ac.ebi.ddi.massive.extws.massive.client.DatasetWsClient;

import uk.ac.ebi.ddi.massive.extws.massive.client.ISODetasetsWsClient;
import uk.ac.ebi.ddi.massive.extws.massive.config.MassiveWsConfigProd;
import uk.ac.ebi.ddi.massive.extws.massive.filters.DatasetSummarySizeFilter;
import uk.ac.ebi.ddi.massive.extws.massive.filters.DatasetSummaryUserFilter;
import uk.ac.ebi.ddi.massive.extws.massive.model.*;
import uk.ac.ebi.ddi.massive.model.Project;

import uk.ac.ebi.ddi.massive.model.Specie;
import uk.ac.ebi.ddi.massive.utils.Constants;
import uk.ac.ebi.ddi.massive.utils.ReaderMassiveProject;
import uk.ac.ebi.ddi.massive.utils.WriterEBeyeXML;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * This program takes a datasets from massive and convert them into DDI experiments
 *
 * @author Yasset Perez-Riverol
 */

public class GenerateMassiveEbeFiles {

    private static final Logger logger = LoggerFactory.getLogger(GenerateMassiveEbeFiles.class);

  /**
     * This program take an output folder as a parameter an create different EBE eyes files for
     * all the project in ProteomeXchange. It loop all the project in MetabolomeWorkbench and print them to the give output
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
        TaxWsConfigProd taxWsConfigProd = (TaxWsConfigProd) ctx.getBean("taxWsConfig");

        try {
            GenerateMassiveEbeFiles.generateMWXMLfiles(mwWsConfigProd, taxWsConfigProd, outputFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateMWXMLfiles(MassiveWsConfigProd configProd, TaxWsConfigProd taxonomyWsConfig, String outputFolder) throws Exception {

        DatasetWsClient datasetWsClient = new DatasetWsClient(configProd);

        ISODetasetsWsClient isoClient = new ISODetasetsWsClient(configProd);

        TaxonomyWsClient taxonomyWsClient = new TaxonomyWsClient(taxonomyWsConfig);

        DatasetList datasets = isoClient.getAllDatasets();

        if (datasets != null && datasets.datasets != null) {

            for (DataSetSummary dataset : datasets.datasets) {
                if(dataset.getTask() != null){
                    DatasetDetail datasetDetail = datasetWsClient.getDataset(dataset.getTask());

                    if(datasetDetail != null && datasetDetail.getId() != null
                            && new DatasetSummarySizeFilter(10).apply(dataset)
                            && new DatasetSummarySizeFilter(1).apply(dataset)
                            && !(new DatasetSummaryUserFilter("tranche_mbraga").apply(dataset))
                            && (dataset.getTitle() != null && !dataset.getTitle().isEmpty())){

                        if(datasetDetail != null && datasetDetail.getSpecies() != null){
                            String[] species = datasetDetail.getSpecies().split(Constants.MASSIVE_SEPARATOR);
                            List<Specie> taxonomies = new ArrayList<Specie>();
                            if(species.length > 1){
                                for(String specie: species){
                                    specie = new String(specie.getBytes(), "UTF-8");
                                    NCBITaxResult texId = taxonomyWsClient.getNCBITax(specie);
                                    if(texId != null && texId.getNCBITaxonomy() != null && texId.getNCBITaxonomy().length > 0 && texId.getNCBITaxonomy()[0] != null)
                                        taxonomies.add(new Specie(specie, texId.getNCBITaxonomy()[0]));
                                    else
                                        taxonomies.add(new Specie(specie, null));
                                }
                            }else{
                                NCBITaxResult texId = taxonomyWsClient.getNCBITax(datasetDetail.getSpecies());
                                if(texId != null && texId.getNCBITaxonomy() != null && texId.getNCBITaxonomy().length > 0 && texId.getNCBITaxonomy()[0] != null)
                                    taxonomies.add(new Specie(datasetDetail.getSpecies(), texId.getNCBITaxonomy()[0]));
                                else
                                    taxonomies.add(new Specie(datasetDetail.getSpecies(), null));
                            }

                            Project proj = ReaderMassiveProject.readProject(datasetDetail, taxonomies);
                            WriterEBeyeXML writer = new WriterEBeyeXML(proj, new File(outputFolder));
                            writer.generate();
                        }
                    }
                }
            }
        }
    }
}
