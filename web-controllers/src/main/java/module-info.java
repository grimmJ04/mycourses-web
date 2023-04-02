module courses.web.controllers {
    requires courses.core;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    exports hu.szte.inf.controllers;
    exports hu.szte.inf.controllers.api;
}
