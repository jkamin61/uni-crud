# Spring Boot CRUD Application with Hibernate and JPA

This is a simple Spring Boot application that demonstrates CRUD (Create, Read, Update, Delete) operations using Hibernate and JPA. The application manages student data through a command-line interface.

## Features

- **Create Student**: Add a new student with first name, last name, and email.
- **Read Student**: Find a student by ID or last name, and list all students.
- **Update Student**: Modify a student's first name, last name, or email.
- **Delete Student**: Remove a student by ID.

## Prerequisites

- Java 17 or higher
- Maven 3.x
- Spring Boot 3.x
- Hibernate
- MySQL or any other relational database (configured in `application.properties`)

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/spring-boot-crud.git
   cd spring-boot-crud

2. **Configure the database**:
   
   Update the application.properties file with your database credentials:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```  
3. **Build the project**:
   ```bash
   mvn clean install
   ```
4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
5. **Use the application**:

   Follow the on-screen menu to perform CRUD operations on student data.

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── soutdev
│   │           └── cmd_crud
│   │               ├── dao
│   │               │   ├── StudentDAO.java
│   │               │   └── StudentDAOImpl.java
│   │               ├── entity
│   │               │   └── Student.java
│   │               └── CmdCrudApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
└── test
└── java
```
## Dependencies: 
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Hibernate Core
- MySQL Connector Java
- Spring Boot DevTools (for development)
## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.
## License
This project is licensed under the MIT License - see the LICENSE file for details.
