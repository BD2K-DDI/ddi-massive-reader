package uk.ac.ebi.ddi.massive.extws.massive.filters;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 09/11/15
 */
public interface IPredicate<T> {

    boolean apply(T type);

}
