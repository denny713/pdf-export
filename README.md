# pdf-export
PDF Export Using Java

Requirement :
- Java JDK 11
- Maven
- MySQL Database
- Spring Boot
- JPA
- Docker Desktop

Step :
- Install MySQL in Docker Desktop using shell script in this location (src/main/resources/mysql-run.sh)
- Run SQL File to create database and table for store data (src/main/resources/data.sql)
- Clean and Install the project for firstly using command 'mvn clean install' without Single  Quote (').
- Run Project using command 'mvn spring-boot:run' without single quote (').
- Testing can execute using Postman with import this link (https://api.postman.com/collections/5581159-af83cb83-bd34-4ff3-a682-d9ddc2100372?access_key=PMAT-01GV5MSYQKQEAS4GJ6KCAG1APD)
- Also have Unit Testing in package 'src/test/java/com/pdf/export'.
- Testing also can execute using swagger in link 'localhost:7020/api/swagger-ui/index.html' without single quote (');

Created By:
Denny Afrizal