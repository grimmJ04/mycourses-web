package hu.szte.inf.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryService {

    private static EntityManagerFactory factory;

    public EntityManagerFactoryService() {
    }

    public synchronized EntityManagerFactory getService() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("global");
        }
        return factory;
    }
}

