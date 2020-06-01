# Shift Checker

Shift Checker is a RESTful API where several APIs are exposed to add shift codes, group codes and assign shifts to groups and to check if a certain shift belonging to a group is available or not. A separate client application is also built for user to consume the exposed APIs. This repository can be viewed using the following URL

[https://github.com/HPBmla/Shift-Management](https://github.com/HPBmla/Shift-Management)


## Relationship between tables

Between shift and shift_group table there is a many to many relationship. To handle a many to many relationship normally a separate table should be introduced which act as the relationship table. So shift_grouping table act as the relationship table in this scenario.Between shift table and shift_grouping table there is a one to many relationship where one shift code can have multiple shift groups. Between shift_group and shift_grouping also there is a one to many relationship where one shift group can have multiple shift codes. Therefore shift_grouping table is used as a relationship table between shift table and shift_group table. 

## Decisions and Assumptions

### Decisions
  - Use H2 database
   
H2 database was decided to handle data management as it need less effort in setting up when compared other databases. Also as all the requirements are internally available no need of extra installations
  
 - Use JPA as Object Relational Mapping (ORM)

JPA was selected as at any point changing of database type wouldn't affect the flow. Because queries related to specific databases aren't used within the application. So if customer requirement changes to use another database in future, still the flow would be normal.

 - Expose features as RESTfull APIs

Features are exposed as Restfull APIs as they are easily accessible and also in future these APIs can be used for another similar product if needed.

 - Use Aspect Oriented Programming ( AOP ) for logging feature

Rather than duplicating code lines to produce production level logs ,a separate class handling logs were introduced using AOP

- Build a Springboot web application

As the features are exposed as Restful APIs most efficient methodology is to use a Springboot web application

- Introduce second level caching in JPA

To handle continuous read from database, second level caching is introduced in JPA. So that if the same query need to be run, application wouldn't be querying from database but will get data from cache which will save lot of time and resources.

### Assumptions

 - Assume that there cannot be any two shifts with the same shift code
 - Assume that there cannot be any two shift groups cannot have same shift group


## Installation

1. Navigate to the directory where the pom.xml is located using the terminal.Run the following command to build the application. To run the following commands Java need to be already installed in the machine. 

```bash
mvn clean install
```

2. Run the following command to start the application using the jar file. 

```bash
java -jar target/shift-0.0.1-SNAPSHOT.jar
```
3. At the start of the application data will be inserted to shift, shift_group and shift_grouping tables automatically through the ApplicationRunner

4. To check if a shift belongs to a group navigate to the following URL

   [http://localhost:8080/checkShift](http://localhost:8080/checkShift)

5. Swagger documentation can be accessed via following URL

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

6. Following URL provide the available links in Shift Checker

    [http://localhost:8080](http://localhost:8080)

7. If you want to insert shift codes from UI, you can access the related UI from the following URL

    [http://localhost:8080/shift](http://localhost:8080/shift)

8. If you want to insert shift groups use the following URL
 
    [http://localhost:8080/group](http://localhost:8080/group)

9. If you want to assign a shift to a shift Group use the following URL

    [http://localhost:8080/shiftGroupPage](http://localhost:8080/shiftGroupPage)