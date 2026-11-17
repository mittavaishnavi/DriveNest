:::writing{variant="document" id="73182"}
# DriveNest – Vehicle Rental Platform

**Your Journey, Your Ride.**

DriveNest is a full-stack vehicle rental platform that allows customers to browse available vehicles, request bookings, manage their profiles, and view booking invoices. Administrators can manage vehicles, customers, bookings, and dashboard statistics.

## Project Overview

DriveNest provides a platform for customers and administrators with role-based access and secure authentication.

### Technology Stack

**Backend**
- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Spring Data JPA
- Hibernate
- MySQL
- Maven

**Frontend**
- React
- JavaScript
- Vite
- HTML
- CSS

**Tools**
- Eclipse IDE
- Visual Studio Code
- Postman
- MySQL Workbench
- Git and GitHub

## Features

### Customer Features
- Customer registration and login.
- JWT-based authentication.
- Browse available vehicles.
- View vehicle details.
- Request vehicle bookings.
- Support for Self Drive and With Driver bookings.
- Booking date validation and availability checking.
- Manage customer profile.
- View booking history and booking status.
- View booking invoices.
- Receive booking notifications.
- Submit vehicle reviews.

### Administrator Features
- Secure administrator login.
- Add, update, view, and delete vehicles.
- View all customer bookings.
- Approve or reject booking requests.
- Provide rejection reasons.
- View and manage customer accounts.
- Block and unblock customers.
- View dashboard statistics.
- View most-booked vehicle analytics.

## Security

- JWT-based stateless authentication.
- Role-based authorization for customers and administrators.
- Password hashing using BCrypt.
- Protected API endpoints.
- Customer booking ownership validation.
- Server-side booking price calculation.
- Validation of incoming request data.
- Sensitive configuration stored using environment variables.

## Project Architecture

```text
DriveNest/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/carrental/
│   │   │   │   ├── configuration/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   ├── dto/
│   │   │   │   ├── enums/
│   │   │   │   └── exception/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── frontend/
│
├── .gitignore
├── README.md
└── start-all.bat
```

## Environment Configuration

Configure the following environment variables before running the backend.

| Variable | Purpose |
|---|---|
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `APP_JWT_SECRET` | JWT signing secret (minimum 32 bytes) |

Never commit database passwords or JWT secrets to GitHub.

The backend reads these values from environment variables through `application.properties`.

## Running the Project Locally

### Prerequisites
- Java 21
- Node.js and npm
- MySQL Server
- Maven (or the included Maven Wrapper)

### 1. Database Setup

- Start MySQL Server.
- Create the database named `car_rental_db`.
- Configure your MySQL username and password using environment variables.

### 2. Start Backend

Open the backend project and run:

`CarRentalApplication.java`

The backend runs at:

`http://localhost:7077`

### 3. Start Frontend

Open a terminal inside the frontend directory.

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Open the local URL displayed by Vite in your browser.

## API Overview

### Authentication
- `POST /api/auth/signup`
- `POST /api/auth/login`

### Customer
- Browse vehicles.
- View vehicle details.
- Create bookings.
- View booking history.
- Manage profiles.
- View invoices.
- Submit reviews.

### Administrator
- Manage vehicles.
- View and manage bookings.
- Approve and reject bookings.
- Manage customer accounts.
- View dashboard statistics.
- View booking analytics.

Most protected endpoints require a valid JWT token and the appropriate user role.

## Important Limitations

- Online payment processing is not integrated.
- Email delivery depends on external mail configuration.
- Deployment configuration and database migration scripts are not included.
- The project currently runs as a local development application.

## Future Enhancements

- Online payment gateway integration.
- Cloud-based image storage.
- Deployment to a cloud platform.
- Automated backend and frontend testing.
- Improved booking and availability management.

## Author

**DriveNest – Vehicle Rental Platform**

Built using Java, Spring Boot, React, and MySQL.
:::