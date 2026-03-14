# Fitness Tracker Application

A full-stack fitness tracking web application that allows users to register, track fitness activities, and receive workout recommendations.

This project demonstrates modern backend development using Spring Boot with secure authentication and a responsive React frontend.

---

## Features

- User registration and login
- JWT based authentication
- DTO Layer for Data Transfer
- Fitness recommendation system
- Secure REST APIs
-  API documentation using Swagger
- Clean backend architecture
- Global exception handling
- Unit Testing Support
---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- Swagger

### Frontend
- React
- React Router
- Tailwind CSS
- Material UI
- Axios

### Database
- Postgresql

### Tools
- Git
- Docker
- Postman
- Git Hub

---

## Tools & Versions

| Tool | Version |
|-----|------|
| Java | 21 |
| Spring Boot | 4.0.1 |
| Node.js | 22.14.0 |
| React | 18.x |
| Postgresql | 17.x |
| Docker | 29.1.3 |

---

## Run Locally

Clone the repository

```bash
git clone https://github.com/Soumyarout3002/fitness-tracker-application.git
```

### Backend Setup

```bash
cd backend
mvn spring-boot:run
```

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```
## Run with Docker

Build the Docker image

```bash
docker build -t fitness_app .
```

Run the container

```bash
docker run -p 8080:8080 -e DB_URL=jdbc:postgesql://host.docker. internal : 5432/fitnessdb_demo -e DB USER=your_username -e DB PWD=your_password fitness_app
```

---

---

## Project Structure

```
fitness-tracker
│
├── backend
│   ├── Cofing
│   ├── Controller
│   ├── DTOs
│   ├── Entity
│   ├── Exceptions
│   ├── Repository
│   ├── Security
│   └── Service
│
├── frontend
│   ├── api
│   ├── components
│   ├── context
│   ├── pages
│   ├── routes
│   └── theme
│
└── README.md
```



