# Student-Management-System

This project implements a REST API service for Student Management Service, that allows to create students, teachers, courses, quizzes and assign students to courses and more.

## Documentation

https://documenter.getpostman.com/view/26688798/2s9YkgDk21

## Requirements
- Java (version 16 or higher)
- Maven
- MySQL
- Apache Tomcat 10.1.7

## Setup Instructions

## 1- Clone the repository
```bash
git clone https://github.com/ayman76/Student-Management-System
```
## 2- Setting Up Apache Tomcat
- Download Tomcat: Visit the [Tomcat download page](https://tomcat.apache.org/download-%5Bversion%5D.cgi) and download the appropriate version for your operating system.
- Install Tomcat: Follow the installation instructions provided on the Tomcat website or in the downloaded package to install Tomcat on your machine.

## 3- Build the Project:
```bash
mvn clean package
```
- Open Project in your editor
- Configure tomcat server on the project
- Configure dispatcher-servlet.xml
- Add database-port, database-name, user and password to configure your database

```bash
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:{database-port}/{database-name}?createDatabaseIfNotExist=true"/>
        <property name="username" value="{user}"/>
        <property name="password" value="{password}"/>
</bean>
```

## 4- Testing the API:
The API endpoints can be tested using tools like Postman, cURL, or any API testing tool.
## - Student
- Create Student:
    - Endpoint: POST /api/v1/students/create
    - Payload: JSON object with student details (firstName, lastName, studentEmail, studentAge)

- Get All Students:
    - Endpoint: GET /api/v1/students/all

- Get Student By Id:
    - Endpoint: GET /api/v1/students/{studentId}

- Get Student's Courses:
    - Endpoint: GET /api/v1/students/{studentId}/courses

- Update Student:
    - Endpoint: PUT /api/v1/students/{studentId}/update
    - Payload: JSON object with student details (firstName, lastName, studentEmail, studentAge)

- Delete Student:
    - Endpoint: DELETE /api/v1/students/{studentId}/delete

## - Teacher
- Create Teacher:
    - Endpoint: POST /api/v1/teachers/create
    - Payload: JSON object with teacher details (firstName, lastName, teacherEmail)

- Get All Teachers:
    - Endpoint: GET /api/v1/teachers/all

- Get Teacher By Id:
    - Endpoint: GET /api/v1/teachers/{teacherId}

- Get Teacher's Courses:
    - Endpoint: GET /api/v1/teachers/{teacherId}/courses

- Update Teacher:
    - Endpoint: PUT /api/v1/teachers/{teacherId}/update
    - Payload: JSON object with teacher details (firstName, lastName, teacherEmail)

- Delete Teacher:
    - Endpoint: DELETE /api/v1/teachers/{teacherId}/delete

## - Course
- Create Course:
    - Endpoint: POST /api/v1/courses/create
    - Payload: JSON object with course details (courseName, teacherId)

- Get All Courses:
    - Endpoint: GET /api/v1/courses/all

- Get Course By Id:
    - Endpoint: GET /api/v1/courses/{courseId}

- Assign Students to Course:
    - Endpoint: POST /api/v1/courses/{courseId}/assignStudent
    - Payload: JSON object with list of students id

- Update Course:
    - Endpoint: PUT /api/v1/courses/{courseId}/update
    - Payload: JSON object with course details (courseName, teacherId)

- Delete Course:
    - Endpoint: DELETE /api/v1/courses/{courseId}/delete


## - Quiz
- Create Quiz:
    - Endpoint: POST /api/v1/courses/{courseId}/quizzes/create
    - Payload: JSON object with quiz details (title)

- Get All Quizzes:
    - Endpoint: GET /api/v1/courses/{courseId}/quizzes/all

- Get Quiz By Id:
    - Endpoint: GET /api/v1/courses/{courseId}/quizzes/{quizId}

- Update Quiz:
    - Endpoint: PUT /api/v1/courses/{courseId}/quizzes/{quizId}/update
    - Payload: JSON object with course details (courseName, teacherId)

- Delete Quiz:
    - Endpoint: DELETE /api/v1/courses/{courseId}/quizzes/{quizId}/delete


The app will start running at http://localhost:8080/Student-Management-System
