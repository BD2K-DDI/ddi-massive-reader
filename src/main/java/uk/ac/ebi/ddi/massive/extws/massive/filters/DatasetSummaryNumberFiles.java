package uk.ac.ebi.ddi.massive.extws.massive.filters;

import uk.ac.ebi.ddi.massive.extws.massive.model.DataSetSummary;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
public class DatasetSummaryNumberFiles<T> implements IPredicate<T>{

    int numberFile = 0;

    public DatasetSummaryNumberFiles(int numberFile) {
        this.numberFile = numberFile;
    }

    @Override
    public boolean apply(Object object) {
        return ((DataSetSummary) object).getFileCount() > numberFile;
    }
}
