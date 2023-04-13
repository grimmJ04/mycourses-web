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

save
---
```java
<S extends T> S save(S entity)
```

Adott objektumot mentésére szolgál.

saveAll
---
```java
<S extends T> Iterable<S> saveAll(Iterable<S> entities)
```

Több objektum mentésére szolgál.

findById
---
```java
Optional<T> findById(ID id)
```

Adott entitás keresésére szolgál.
Amennyiben létezik a keresett elem, úgy a `get()`-el azt lekérhetjük,
amennyiben nem, abban az esetben a tárolt érték null.

existsById
---
```java
boolean existsById(ID id)
```

Megkérdezhetjük, hogy létezik-e az adott objektum az adatbázisunkban.

findAll
---
```java
Iterable<T> findAll()
```

Összes elem lekérése a db-ből.

findAllById
---
```java
Iterable<T> findAllById(Iterable<ID> ids)
```

Összes elem id alapú lekérdezése az adatbázisból.

count
---
```java
long count()
```

Db elemeinek számossága.

deleteById
---
```java
void deleteById(ID id)
```

Adott elem törlésére szolgál.

delete
---
```java
void delete(T entity)
```

Adott elem törlésére szolgál.

deleteAllById
---
```java
void deleteAllById(Iterable<? extends ID> ids)
```

Megadott id-vel rendelkező elemek törlése.

deleteAll
---
```java
void deleteAll(Iterable<? extends T> entities)
```

Adott elemek törlése.

deleteAll
---
```java
void deleteAll()
```

Pusztítás.

___

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
