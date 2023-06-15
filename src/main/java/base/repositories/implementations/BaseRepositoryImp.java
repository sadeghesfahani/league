package base.repositories.implementations;

import base.repositories.interfaces.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImp<Entity, IDType extends Serializable> implements BaseRepository<Entity, IDType> {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("league");
    protected Class<Entity> entityClass;

    public BaseRepositoryImp(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public Entity findById(IDType id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Entity> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Entity> query = em.getCriteriaBuilder().createQuery(entityClass);
            query.select(query.from(entityClass));
            return em.createQuery(query).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Entity save(Entity entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }

    }

    @Override
    public void delete(Entity entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
