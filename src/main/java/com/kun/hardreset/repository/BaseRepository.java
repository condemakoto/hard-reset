package com.kun.hardreset.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class BaseRepository<C> {

    @PersistenceContext
    protected EntityManager entityManager;

    public void create(C object) {
        entityManager.persist(object);
    }

    public void create(List<C> objects) {
        for (C object : objects) {
            try {
                entityManager.persist(object);
            } catch(Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public void delete(C object) {
        entityManager.remove(object);
    }

    public List<C> findAll() {
        return entityManager.createQuery("select c from " + getEntityName() + " c").getResultList();
    }

    public void deleteAll() {
        entityManager.createQuery("delete from " + getEntityName()).executeUpdate();
    }

    protected abstract String getEntityName();

}
