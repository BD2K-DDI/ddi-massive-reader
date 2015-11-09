package uk.ac.ebi.ddi.massive.extws.massive.filters;

import uk.ac.ebi.ddi.massive.extws.massive.model.DataSetSummary;
import uk.ac.ebi.ddi.massive.extws.massive.model.DatasetDetail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
public class DatasetSummarySizeFilter<T> implements IPredicate<T>{

    double dataSizeMb = 0;
//    Pattern regPattern = Pattern.compile("(-)?(([^\\d])(0)|[1-9][0-9]*)(.)([0-9]+)");

    public DatasetSummarySizeFilter(double dataSizeMb){
        this.dataSizeMb = dataSizeMb;
    }

    @Override
    public boolean apply(Object object) {
        return ((DataSetSummary) object).getFileSize()/1000 > dataSizeMb;
    }
}
