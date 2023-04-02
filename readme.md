# Courses Web Project

A projekt egy kurzusok tárolására szolgáló webalkalmazás.

## Core module

Modell fájlok:
- [Course modell](./core/src/main/java/hu/szte/inf/models/Course.java)
- [Grade enum](./core/src/main/java/hu/szte/inf/models/Grade.java)
- [Semester enum](./core/src/main/java/hu/szte/inf/models/Semester.java)

## Repository / DAO

Használatos interface, amivel kommunikálunk az adatbázissal:
[IHibernateCourseRepository](./core/src/main/java/hu/szte/inf/repositories/IHibernateCourseRepository.java).

Amennyiben az ajánlott keretrendszert használjuk a megfelelő módon,
úgy az implementáció létrehozása automatikus lesz.
Amennyiben magunk szeretnénk implementációt szolgáltatni,
használhatjuk például a következőt:
[HibernateCourseRepository](./core/src/main/java/hu/szte/inf/repositories/HibernateCourseRepository.java).

Egyéb hasznos funkciók találhatók az **utils** package alatt.

## Web-Controllers module

Spring Boot használata esetén a web-controllers modulban ajánlatos elhelyezni egy **module-info.java** fájlt.
Tartalma megközelítőleg hasonló lesz majd:

```css
module courses.web.controllers {
    requires courses.core;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    exports hu.szte.inf.controllers;
    exports hu.szte.inf.controllers.api;
}
```

A web applikációs modulokon belül ne legyen ilyen module fájl!
