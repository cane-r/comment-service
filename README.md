# comment microservice

comment microservice with java11,spring boot,spring-data-rest and spring-data-jpa. WIP project.. 

# How to run :
mvn clean spring-boot:run -Dspring-boot.run.arguments="--test=test"

## diagrams

v0 current
```mermaid
graph LR
A[React UI] --> B(Service1)
A --> C(Service2)
B --> D{DB}
C --> E{DB}
```

v1 ..WIP/TBD later..
```mermaid
graph LR
A[React UI] --> B(Gateway)
B --> C(Service1)
B --> D(Service2)
C --> E{DB}
D --> F{DB}