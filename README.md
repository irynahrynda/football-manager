## Football manager

This application is implemented as a football manager service.
- --

### Available endpoints in the project:
- /teams - CRUD operations
- /players - CRUD operations
- /transfers - transfer operation
- --

### If you want to run this project on your computer, you need:
1. Fork and clone this project:
```bash
git clone git@github.com:irynahrynda/football-manager.git
```
2. Add Lombok plugin to your IDE
3. Run the application
4. Use Postman for sending your requests during testing this application
- [collection_link](https://www.getpostman.com/collections/947ccb790e5194d41de4)
- [capture_link](https://prnt.sc/LET2DvhAlcjc)
5. Use H2-database in memory
6. Injection of players and teams are located in config/DataInitializer class.
- --

### Used technologies
- Java 11
- SpringBoot
- SpringBoot Data JPA
- Spring Web
- H2 DB
- Lombok
- Maven checkstyle plugin
