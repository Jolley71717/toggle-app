package gov.mt.dnrc.toggle.core.service.spi;

import java.io.Serializable;
import java.util.Set;

/**
 * Generic Service Interface that allows services run the same code
 *
 * @param <T> Type of services this interface will be for
 * @param <I> the Id value type, placeholder for Id value.
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IGenericService<T, I extends Serializable> {

    /**
     * Retrieves all of the toggles in the database.
     *
     * @return returns the list of toggles.
     */
    Iterable<T> retrieveAll();

    /**
     * Saves the toggle to the database.
     *
     * @param entity the toggle to be saved.
     * @return returns the saved copy of the toggle object.
     */
    T save(T entity);

    /**
     * Service to save the state of all toggles within the application.
     * We want to ensure that each toggle in a list of toggles to be saved
     *
     * @param entities The list of toggles to be saved to the database.
     * @return returns the saved list of toggles.
     */
    Set<T> save(Set<T> entities);

    /**
     * Delete object.
     *
     * @param id the row identifier of the entity.
     */
    void delete(I id);
}
