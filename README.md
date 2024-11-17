# Sports Event Calendar
## Overview

Sports Event Calendar Application
Overview
The Sports Event Calendar application is a web application that allows users to view upcoming sports events and administrators to add new events. 
This application enables users to retrieve event information, including venue details, participating teams, and associated sports, with filtering 
capabilities for date and sport.

### Technologies Used
Backend: Java, Spring Boot \
Frontend: Javascript \
Database: PostgreSQL \
Testing: JUnit, Mockito, Spring MockMvc\
Build Tool: Maven


## Installation
### Clone the repository
1. ``git clone https://github.com/your-username/sports-event-calendar.git``
2. Make sure you have java 17 or higher and Maven installed on your system


### Configure the database connection
1. Install PostgreSQL on your System
2. Open the psql program that came with the Postgresql installation
   * Create a new database with the following command: \
     ```CREATE DATABASE sports_event_calendar```
3. In the application.properties file in the resources folder:
    * Check if the spring.datasource.url property
      in the application.properties file matches your PostgreSQL settings, particularly the port and database name
4. In the root of the project, create a file named ".env" with the following content:
    * DB_USERNAME=postgres_username \
      DB_PASSWORD=postgres_password

   Replace "postgres_username" and "postgres_password" with the username
   and password you chose in the installation process of Postgresql


### Run the Application
cd into the project root and in the terminal, type: \
``mvn spring-boot:run`` \
\
The app will be running on http://localhost:8080/.
