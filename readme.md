# Courses Web Project

A projekt egy kurzusok tárolására szolgáló webalkalmazás.

Ez a readme nem a feladatleírásra szolgál, csupán egyfajta segítségként használható.

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

Adott objektumot mentésére és frissítésére is szolgál!
Ez attól függ, hogy kap-e, avagy olyan azonosítójú objektumot kap, mely már létezik.

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

Az `Optional<T>` típus egyéb hasznos metódusai még az `isPresent()` és az `isEmpty()`,
melyek pontosan úgy viselkednek, ahogyan arra az elnevezésük alapján számíthatunk.

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

```
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

## Web-View module

Használható többféle templating technológia is. Alapértelmezetten a JSP használata javasolt.
Spring Boot megoldás esetén szükségünk lehet a következő
`application.properties` fájlra/fájlokra a megfelelő projekt `resources` mappája alatt.

JSP esetén:
```properties
# resolve jsp view files
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create

# H2 DB
# spring.datasource.url=jdbc:h2:file:~/database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.database=h2

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL
spring.h2.console.path=/h2-console

# Statistics on and log SQL statements
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Extensive logging
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.type=trace
logging.level.org.hibernate.stat=debug
logging.level.org.springframework.web=debug

# enable debug (set to false, to disable)
debug=true
```

Thymeleaf esetén:
```properties
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create

# H2 DB
# spring.datasource.url=jdbc:h2:file:~/database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.database=h2

# Enabling H2 Console
spring.h2.console.enabled=true

# Custom H2 Console URL
spring.h2.console.path=/h2-console

# Statistics on and log SQL statements
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Extensive logging
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.type=trace
logging.level.org.hibernate.stat=debug
logging.level.org.springframework.web=debug

# enable debug (set to false, to disable)
debug=true

# one hot reload from source
spring.thymeleaf.prefix=file:web-thymeleaf/src/main/resources/templates/
spring.thymeleaf.cache=false
spring.web.resources.static-locations=file:web-thymeleaf/src/main/resources/static/
spring.web.resources.cache.period=0
```

## Templating segítség

- JSP oldalak esetén a core taglib uri-ja:
  - 1.2 jstl esetén: http://java.sun.com/jsp/jstl/core (default),
  - 1.0 jstl esetén: http://java.sun.com/jstl/core.
- Thymeleaf esetén hasznos namespace (xmlns): https://www.thymeleaf.org.

## Spring Boot segítség

### Applikáció létrehozása

```java
@SpringBootApplication
// You may need this, if your repositories are inside another module.
// @EnableJpaRepositories(value = "your.package.with.repositories")
public class WebApp /* extends SpringBootServletInitializer */ {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
    
    /*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApp.class);
    }
    */
}
```

A kommentelt részek jsp app esetén jöhetnek jól, de ott sem feltétlen szükségesek.
Használatuk elhagyható.

### Startup logika

```java
@Component // !!! IMPORTANT !!! so that spring can find our class
class SomeClassThatRunsOnStartup implements ApplicationRunner {

    // You can use anything autowireable here,
    // or create a corresponding constructor for
    // your final field.
    private static final Logger staticLogger = ...;

    private final Logger logger;

    public SomeClassThatRunsOnStartup(Logger logger) {
        ...
    }

    @Override
    public void run(String... args) {
        // any initialization logic here
        logger.info("...");
    }
}
```

### Web kontrollerek létrehozása

```java
@Controller // <-- Annotate your controllers
@RequestMapping(value = "/course") // You could use a default mapping.
public class IndexController {

    @GetMapping(value = "/create") // get request endpoint, (/book/create)
    public ModelAndView create() {
        // return a new model view with a model named "model"
        // when returning, we look for a file named create, inside the book directory
        return new ModelAndView("course/create", "model", new Course());
    }

    @PostMapping(value = "/create") //  post request endpoint
    public String create(Course model) { // get your model file through request param
        // save book to db, etc ...
        return "your page's name";
    }

    @GetMapping(value = {"/read", "/view"}) // mapping with multiple endpoint options
    public String index() {
        return "read data page";
    }
}
```

## Függőségek

A megoldás során valószílűleg előforduló függőségekről a [pom.xml](./pom.xml) fájlban találhatsz infót.

Nem kell mind, ugyanis jelentős részük már a core modulnak is része,
azaz a core module megadása, mint függőség, sok tranzitív package-t is behúz.

JSP webapp esetén leginkább szükséges függőségek a
- tomcat-embed-jasper (_provided_)
- jstl (_runtime_ vagy ha konténerünk kiszolgálja, esetleg _provided_)
- spring-boot-starter-web (opcionálisan ez is kellhet)
___

## Közös infók

- Bíró: [biro](https://biro.inf.u-szeged.hu)
- Gyakorlati anyag: [alkalmazasfejlesztes_kozos.zip](https://biro.inf.u-szeged.hu/kozos/alkfejl/alkalmazasfejlesztes_kozos.zip)
