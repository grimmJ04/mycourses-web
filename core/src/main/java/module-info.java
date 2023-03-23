module courses.core {
    requires lombok;
    requires java.persistence;
    requires spring.context;
    requires spring.data.commons;

    exports hu.szte.inf.models;
    exports hu.szte.inf.utils;
    exports hu.szte.inf.utils.db;
}
