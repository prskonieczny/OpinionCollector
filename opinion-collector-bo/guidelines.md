# Getting Started

###Swagger authorization

1. Send request to ```/login``` with body (admin credentials can be replaced with other user credentials):
```
{
  "username": "admin",
  "password": "admin"
}
```
2. Copy token from ``authorization:`` header (without white characters)
3. Click button ``Authorize`` from the top of the page, paste token into input field and click ``Authorize``
4. Now all endpoints are available (in future it will be automated)



### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/gradle-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#howto.data-initialization.migration-tool.liquibase)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#actuator)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#io.validation)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Local Swagger](http://localhost:8080/swagger-ui/index.html#/)
