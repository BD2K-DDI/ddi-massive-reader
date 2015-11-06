package uk.ac.ebi.ddi.massive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.ac.ebi.ddi.massive.extws.entrez.client.taxonomy.TaxonomyWsClient;
import uk.ac.ebi.ddi.massive.extws.massive.client.DatasetWsClient;

import uk.ac.ebi.ddi.massive.extws.massive.config.MassiveWsConfigProd;
import uk.ac.ebi.ddi.massive.extws.massive.model.*;
import uk.ac.ebi.ddi.massive.model.Project;

import uk.ac.ebi.ddi.massive.utils.ReaderMWProject;
import uk.ac.ebi.ddi.massive.utils.WriterEBeyeXML;


import java.io.File;


/**
 * This program takes a ProteomeXchange URL and generate for all the experiments the
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

        try {
            GenerateMassiveEbeFiles.generateMWXMLfiles(mwWsConfigProd,outputFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateMWXMLfiles( MassiveWsConfigProd configProd, String outputFolder) throws Exception {

        DatasetWsClient datasetWsClient = new DatasetWsClient(configProd);


        //RestTemplate rest = (RestTemplate) ctx.getBean("restTemplate");

        DatasetList datasets = datasetWsClient.getAllDatasets();

        if (datasets != null && datasets.datasets != null) {
            for (DataSetSummary dataset : datasets.datasets) {



//                MetaboliteList metabolites = null;
//                FactorList factorList = null;
//                if(dataset != null && dataset.getId() != null){
//                    analysis = datasetWsClient.getAnalysisInformantion(dataset.getId());
//                    metabolites = datasetWsClient.getMataboliteList(dataset.getId());
//                    factorList = datasetWsClient.getFactorList(dataset.getId());
//                }
//                if(metabolites != null && metabolites.metabolites != null && metabolites.metabolites.size() > 0)
//                    metabolites = datasetWsClient.updateChebiId(metabolites);
//
//                if(dataset != null && dataset.getSubject_species() != null){
//                    NCBITaxResult texId = taxonomyWsClient.getNCBITax(dataset.getSubject_species());
//                    if(texId != null &&
//                            texId.getNCBITaxonomy() != null &&
//                            texId.getNCBITaxonomy().length > 0 &&
//                            texId.getNCBITaxonomy()[0] != null)
//                        dataset.setTaxonomy(texId.getNCBITaxonomy()[0]);
//                }
//                Project proj = ReaderMWProject.readProject(dataset, analysis, metabolites, factorList);
//                WriterEBeyeXML writer = new WriterEBeyeXML(proj, new File(outputFolder));
//                writer.generate();
            }
        }
    }
}
