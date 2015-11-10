package uk.ac.ebi.ddi.massive.extws.massive.filters;

import uk.ac.ebi.ddi.massive.extws.massive.model.DataSetSummary;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
public class DatasetSummaryUserFilter<T> implements IPredicate<T> {

    String user = null;

    public DatasetSummaryUserFilter(String user) {
        this.user = user;
    }

    @Override
    public boolean apply(Object object) {
        DataSetSummary dataset = (DataSetSummary) object;
        return (dataset.getUser() != null && dataset.getUser().toLowerCase().equalsIgnoreCase(user));
    }
}
