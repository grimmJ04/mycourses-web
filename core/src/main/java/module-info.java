module courses.core {
    requires lombok;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires spring.context;
    requires spring.data.commons;

    exports hu.szte.inf.models;
    exports hu.szte.inf.repositories;
    exports hu.szte.inf.services;
    exports hu.szte.inf.utils;
    exports hu.szte.inf.utils.db;

    opens hu.szte.inf.models to org.hibernate.orm.core;
}
