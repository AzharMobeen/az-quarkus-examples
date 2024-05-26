### az-quarkus-examples

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/.
<!-- TOC -->
  * [Examples:](#examples)
    * [Example-1](#example-1)
    * [Example-2](#example-2)
    * [Example-3](#example-3)
    * [Examole-4](#examole-4)
  * [Running the application in dev mode](#running-the-application-in-dev-mode)
  * [Packaging and running the application](#packaging-and-running-the-application)
  * [Creating a native executable](#creating-a-native-executable)
  * [Related Guides](#related-guides)
  * [Provided Code](#provided-code)
  * [REST](#rest)
  * [RESTEasy JAX-RS](#resteasy-jax-rs)
<!-- TOC -->
### Examples:
* `main` branch has all the examples
* `checkout` branches as-per-examples order, it will help to understand `Quarkus` from beginner to advance
* For Postman collection please  import openAPI specification `openapi.yml`
#### Example-1
* It's for Rest API and restClient
* Add below dependencies for `Rest` endpoints and `RestClient`
```
/*For Rest Endpoint example*/
implementation("io.quarkus:quarkus-rest")
implementation("io.quarkus:quarkus-rest-jackson")

/*For Rest Client we need below */
implementation("io.quarkus:quarkus-rest-client")
implementation("io.quarkus:quarkus-rest-client-jackson")
```
* For code please have a look on `ExampleResource`
* First API CURL
```
curl --location 'http://localhost:8080/hello' \
--header 'Accept: text/plain'
```
* Response
```
Hello from Quarkus REST
```
* Second API CURL (RestClient)
```
curl --location 'http://localhost:8080/hello/testClient' \
--header 'Accept: text/plain'
```
* Response
```
Hey ya! Great to see you here. Btw, nothing is configured for this request path. Create a rule and start building a mock API.
```
#### Example-2
* It's for Rest API with JPA and H2 in memory DB
#### Example-3
* Security added (JWT token generation and validation with symmetric approach)
#### Examole-4
* In this example, we will have Faceted Search technique with JPA

### API Documentation:
* Add below dependency
```
implementation 'io.quarkus:quarkus-smallrye-openapi'
```
* Add below properties
```
quarkus.smallrye-openapi.path=/openapi
quarkus.swagger-ui.always-include=true
quarkus.swagger-ui.path=/swagger-ui
```
* Open API : http://localhost:8080/openapi
* Swagger UI: http://localhost:8080/swagger-ui

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/az-quarkus-rest-example-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

### Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus
  REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and
  more

### Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)