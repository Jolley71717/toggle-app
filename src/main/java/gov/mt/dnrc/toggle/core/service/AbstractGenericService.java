package gov.mt.dnrc.toggle.core.service;

import gov.mt.dnrc.toggle.core.service.spi.IGenericService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

/**
 * Generic Abstract Service that allows developers to write once and use many times. Since
 * this application implements the CRUD methodology this should reduce the code footprint of the application.
 * Any additional implementations should be overwritten if needed.
 *
 * @param <T> Type of services this interface will be for
 * @param <I> the Id value type, placeholder for Id value.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractGenericService<T, I extends Serializable> implements IGenericService<T, I> {

    private final CrudRepository<T, I> repository;

    /**
     * Constructor for the Abstract Service. Built around the Spring Framework PagingAndSortingRepository Interface.
     *
     * @param repository Immutable object that can be subclassed to CrudRepository or PagingAndSortingRepository
     */
    public AbstractGenericService(CrudRepository<T, I> repository) {this.repository = repository;}

    /**
     * Retrieves an entity by its id.
     *
     * @param id The id or primary key of a database table.
     * @return returns an object if the id is a valid id. Otherwise will return null if none found.
     * @throws IllegalArgumentException Throws if Id is null
     */
    public T retrieve(I id) {
        return repository.findOne(id);
    }

    /**
     * Returns all entity of type T
     * @return returns an Iterable set of entities
     */
    public Iterable<T> retrieveAll() {
        return repository.findAll();
    }

    /**
     * Save a single entity and add audit info to each save.
     * @param entity The entity that will be saved to the database.
     * @return returns a saved entity object.
     */
    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Saves the set of entities. Wraps the save in a transaction in the case the process fails.
     * @param entities The list of toggles to be saved to the database.
     * @return returns the set of entities that were saved.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Set<T> save(Set<T> entities) {

        entities.forEach(this::save);
        return entities;
    }

    /**
     * Deletes the object from the repository.
     *
     * @param id the row identifier of the entity.
     */
    @Override
    public void delete(I id) {
        repository.delete(id);
    }
}
