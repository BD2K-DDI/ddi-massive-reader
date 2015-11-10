package uk.ac.ebi.ddi.massive.extws.massive.filters;

import uk.ac.ebi.ddi.massive.extws.massive.model.DataSetSummary;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 10/11/15
 */
public class DatasetSummaryTrancheFilter<T> implements IPredicate {


    @Override
    public boolean apply(Object object) {
        DataSetSummary dataSetSummary = (DataSetSummary) object;
        if(dataSetSummary.getTask().toUpperCase().contains("TRANCHE")){
            if((dataSetSummary.getTitle() != null && !dataSetSummary.getTitle().toUpperCase().contains("TITLE HIDDEN") &&
                    dataSetSummary.getInstrument() != null && !dataSetSummary.getInstrument().isEmpty()))
                return true;
            else
                return false;
        }
        return true;
    }
}
