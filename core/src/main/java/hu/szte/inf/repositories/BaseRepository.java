package hu.szte.inf.repositories;

import hu.szte.inf.services.EntityManagerFactoryService;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class BaseRepository<T, ID> implements CrudRepository<T, ID> {

    private final EntityManagerFactoryService serviceFactory = new EntityManagerFactoryService();
    private final Class<T> forClass;

    public BaseRepository(Class<T> forClass) {
        this.forClass = forClass;
    }

    private EntityManager createEM() {
        return serviceFactory.getService().createEntityManager();
    }

    @Override
    public <S extends T> S save(S entity) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            var transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                entityManager.persist(entity);
            } catch (PersistenceException e) {
                transaction.rollback();
                transaction.begin();
                entityManager.merge(entity);
            }
            transaction.commit();
            return entity;
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            var transaction = entityManager.getTransaction();
            transaction.begin();
            for (S entity : entities) {
                try {
                    entityManager.persist(entity);
                } catch (PersistenceException e) {
                    transaction.rollback();
                    transaction.begin();
                    entityManager.merge(entity);
                }
            }
            transaction.commit();
            return entities;
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<T> findById(ID aLong) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            T entity = entityManager.find(forClass, aLong);
            return Optional.of(entity);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean existsById(ID aLong) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            return entityManager.contains(aLong);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Iterable<T> findAll() {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            var query = entityManager.getCriteriaBuilder().createQuery(forClass);
            var root = query.from(forClass);
            var selection = query.select(root);
            var allQuery = entityManager.createQuery(selection);
            return allQuery.getResultList();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> longs) {
        EntityManager entityManager = null;
        try {
            var entities = new ArrayList<T>();
            entityManager = createEM();
            for (var id : longs) {
                T entity = entityManager.find(forClass, id);
                entities.add(entity);
            }
            return entities;
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public long count() {
        return StreamSupport.stream(findAll().spliterator(), false).count();
    }

    @Override
    public void deleteById(ID aLong) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            var entity = entityManager.find(forClass, aLong);
            if (entity != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            }
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> longs) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            entityManager.getTransaction().begin();
            for (var id : longs) {
                var entity = entityManager.find(forClass, id);
                if (entity != null) {
                    entityManager.remove(entity);
                }
            }
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            entityManager.getTransaction().begin();
            for (var entity : entities) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = null;
        try {
            entityManager = createEM();
            var query = entityManager.getCriteriaBuilder().createQuery(forClass);
            var root = query.from(forClass);
            var selection = query.select(root);
            var allQuery = entityManager.createQuery(selection);
            entityManager.getTransaction().begin();
            for (var entity : allQuery.getResultList()) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
