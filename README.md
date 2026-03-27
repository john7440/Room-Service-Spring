# Room-Service-Spring-JPA

A console based Spring Boot app for managing booking rooms,  
developed as a Spring data JPA training exercise

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
  
## Overview

This application provides a console based admin interface for a booking platform.  
It allows managing rooms and booking stored in a relational database, with full CRUD.
It is built with Spring Boot and Spring Data JPA, following a multi-layered architecture   
(Entity, Repository, Business, UI)

## Features

### Room Management 
- Display all rooms
- Add a new room
- View a room by ID
- Update an existing Room
- Delete a Room by ID

### Booking management

- Display all booking
- Add a new booking
- Delete a booking by ID

### Browsing and Search

- Display all booking at a precise date
- Search rooms by minimal capicaty desired

## Architecture

The application follows a **multi-layered architecture**:

- **UI Layer** (Console UI) — User interaction and menu display
- **Business Layer** — Application logic wrapping repository calls
- **Repository Layer** (Spring Data JPA) — Database access via JpaRepository
- **Entity Layer** — JPA-mapped domain objects
- **Database** (MySQL / MariaDB)

## Technologies

- **Language**: Java 17
- **Framework**: Spring Boot 2.5.3
- **ORM**: Spring Data JPA / Hibernate
- **Database**: MySQL 8.x / MariaDB 10.x
- **Build Tool**: Maven
- **Code Quality**: SonarQube
- **Version Control**: Git

## Installation

### 1. Clone the repository
```bash
https://github.com/john7440/Room-Service-Spring.git
```
### 2.Open in IntellijIDEA
1. File -> Open -> Select the project folder
2. Wait for intellij to index the prject and download dependencies
3. Verify that the Maven `pom.xml` is correctly recognized

## Database Setup

The database configuration is located in `src/main/resources/application.properties`:
```text
spring.application.name=RoomServiceJpa

#Database
spring.datasource.url=jdbc:mariadb://localhost:3308/rooms?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

#Jpa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```
Note: Update credentials if your MySQL setup uses a different port, username or password

## Usage

Run the application  
Option A: From IntelliJ IDEA

Navigate to `src/main/java/fr/fms/RoomServiceJpaApplication.java`  
Right-click on the file    
Select `Run 'RoomServiceJpaApplication.main()'`   

Option B: From Command Line (Maven)  
```bash
mvn spring-boot:run
```

## Menu Navigation

```text
 Bienvenu dans notre application de gestion de salles!
 1: Afficher toutes les salles
 2: Afficher réservations par salle
 3: Afficher toutes les réservations
 ************************************
 4: Ajouter une salle
 5: Supprimer une salle
 6: Modifier une salle
 *********************************
 7: Créer une reservation
 8: Supprimer une réservation
 ********************************
 9: Voir réservations à une date précise
 10: Voir salles en fonction de la capacité désirée
 ************************************
 11: Sortir du programme
```
## Project Structure

```text
RoomServiceJPA/
├── src/
│   └── main/
│       ├── java/fr/fms/
│       │   ├── RoomServiceJpaApplication.java     # Entry point
│       │   ├── business/
│       │   │   └── BookingService.java            # Business logic layer
│       │   ├── dao/
│       │   │   ├── BookingRepository.java         # Spring Data JPA repository 
│       │   │   └── RoomRepository.java            # Spring Data JPA repository
│       │   ├── entities/
│       │   │   ├── Booking.java                   # Booking JPA entity
│       │   │   └── Room.java                      # Room JPA entity
│       │   └── ui/
│       │       └── BookingUI.java                 # Console UI layer
│       └── resources/
│           └── application.properties             # App & DB configuration
├── pom.xml                                        # Maven dependencies
└── README.md                                      # This file
````
### License  
This project is part of a Spring Boot / JPA training exercise and is for educational purposes only
