# my-portfolio-website
# Portfolio Website

Personal portfolio developed with **Java**, **Spring Boot**, and **Thymeleaf** to showcase software development projects and technical skills.

## Features

- 🎨 **Retro Windows Theme UI** - Creative desktop-like interface
- 📁 **Project Showcase** - Display your projects with descriptions and links
- 📧 **Contact Form** - Allow visitors to send messages
- 💾 **Database Integration** - Store projects and contact messages
- 🚀 **Production Ready** - Environment-based configuration

## Tech Stack

- **Backend**: Java 25, Spring Boot 4.0.3
- **Template Engine**: Thymeleaf
- **ORM**: Hibernate JPA
- **Database**: MySQL (Production), H2 (Development)
- **Build Tool**: Maven
- **Frontend**: HTML5, CSS3, Vanilla JavaScript

## Prerequisites

- **Java 17+** (or 25 as configured)
- **Maven 3.6+**
- **MySQL 8.0+** (for production)

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd PortfolioWebsite
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run Development Mode
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

The application will start on `http://localhost:8080` with H2 in-memory database.

### 4. Run Production Mode

Set environment variables:
```bash
export DB_URL=jdbc:mysql://localhost:3306/db_portfolio
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

Then run:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

Or build and run JAR:
```bash
mvn clean package
java -Dspring.profiles.active=prod -jar target/PortfolioWebsite-0.0.1-SNAPSHOT.jar
```

## Project Structure

```
src/
├── main/
│   ├── java/com/portfolio/PortfolioWebsite/
│   │   ├── controller/          # REST & Web Controllers
│   │   ├── Model/               # JPA Entities
│   │   ├── Repository/          # Data Access Layer
│   │   └── PortfolioWebsiteApplication.java
│   └── resources/
│       ├── templates/           # Thymeleaf HTML templates
│       ├── static/              # CSS, JS, Images
│       ├── application.properties         # Default (dev)
│       ├── application-dev.properties     # Development config
│       └── application-prod.properties    # Production config
└── test/                        # Unit & Integration Tests
```

## API Endpoints

### Projects
- `GET /projects` - List all projects (HTML)
- `GET /projects/{id}` - Get project details
- `GET /projects/api/all` - Get all projects (JSON)

### Contact
- `GET /contact` - Display contact form (HTML)
- `POST /contact` - Submit contact message (Form)
- `POST /contact/api/send` - Submit contact message (JSON)

## Database

### H2 Console (Development)
Access H2 console at: `http://localhost:8080/h2-console`
- URL: `jdbc:h2:mem:portfolio_db`
- Username: `sa`
- Password: (leave empty)

### Tables
- `projects` - Store portfolio projects
- `contact_messages` - Store contact form submissions

## Configuration

### Development (application-dev.properties)
- Uses H2 in-memory database
- Detailed SQL logging
- Debug level logging
- Auto-creates tables on startup

### Production (application-prod.properties)
- Uses MySQL database
- Environment variables for credentials
- Minimal logging (INFO level)
- Schema validation only (doesn't create tables)

## Deployment

### Option 1: Docker
Create `Dockerfile`:
```dockerfile
FROM openjdk:25-jdk-slim
COPY target/PortfolioWebsite-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t portfolio-website .
docker run -e DB_URL=jdbc:mysql://db:3306/db_portfolio -p 8080:8080 portfolio-website
```

### Option 2: Cloud Hosting
- **Heroku**: `Procfile` and `system.properties` required
- **AWS**: Elastic Beanstalk or EC2
- **Railway/Render**: Push to main branch

## Contributing

Feel free to fork and submit pull requests!

## License

See LICENSE file.

## Author

**Alfredo** - Java Developer  
[Portfolio](https://yourwebsite.com) | [GitHub](https://github.com/yourusername)
