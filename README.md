# DailAnalytics — API Service

Simple Spring Boot API for cases, dockets, documents, and secondary sources.

## Quick Start

Requirements:
- Java 11+ (or OpenJDK)
- Maven (or use the included `mvnw` / `mvnw.cmd`)

## Database Setup

The application automatically creates database tables on startup using Hibernate/JPA.

**Step 1: Install PostgreSQL**
- Download: https://www.postgresql.org/download/
- Install and remember your superuser password

**Step 2: Create a database and user**

Open PostgreSQL command line (psql) and run:

```sql
CREATE USER dailytics WITH PASSWORD 'your_password_here';
CREATE DATABASE dail_litigation OWNER dailytics;
```

Replace `'your_password_here'` with a secure password.

**Step 3: Configure the application**

Create or edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dail_litigation
spring.datasource.username=dailytics
spring.datasource.password=your_password_here
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=false
```

- Change `dail_litigation`, `dailytics`, and password to match Step 2.
- `ddl-auto=create-drop` auto-creates tables and drops them on shutdown (for development).
- Use `ddl-auto=update` for production (tables persist).

**Step 4: Add PostgreSQL dependency**

Ensure `pom.xml` includes:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

**Step 4: Build and run**

(PostgreSQL dependency is already in `pom.xml`—no need to add it.)

Build and run (Windows):

```powershell
mvnw.cmd clean package
mvnw.cmd spring-boot:run
# Or run the built jar:
java -jar target/*.jar
```

Default server URL: http://localhost:8080

## API Endpoints

Base endpoints (methods):

- Cases: `GET /api/case` — list, `GET /api/case/{recordNo}` — get, `POST /api/case` — create, `PUT /api/case/{recordNo}` — update, `DELETE /api/case/{recordNo}` — delete
- Dockets: `GET /api/dockets`, `GET /api/dockets/{id}`, `GET /api/dockets/case/{caseId}`, `POST /api/dockets`, `PUT /api/dockets/{id}`, `DELETE /api/dockets/{id}`
- Documents: `GET /api/documents`, `GET /api/documents/{id}`, `GET /api/documents/case/{caseId}`, `GET /api/documents/date/{date}`, `GET /api/documents/court/{court}`, `POST /api/documents`, `PUT /api/documents/{id}`, `DELETE /api/documents/{id}`
- Secondary sources: `GET /api/sec-sources`, `GET /api/sec-sources/{id}`, `GET /api/sec-sources/case/{caseNumber}`, `POST /api/sec-sources`, `PUT /api/sec-sources/{id}`, `DELETE /api/sec-sources/{id}`

Use JSON for request/response bodies and set the header `Content-Type: application/json` for POST/PUT.

Example curl (list cases):

```bash
curl http://localhost:8080/api/case
```

## Postman — download and basic usage

1. Download and install Postman: https://www.postman.com/downloads/
2. Open Postman and click "New" → "Request".
3. Enter a name, select method (GET/POST/PUT/DELETE) and URL (e.g., `http://localhost:8080/api/documents`).
4. For POST/PUT: select the `Body` tab → `raw` → choose `JSON` and paste a JSON object (example below). Add header `Content-Type: application/json`.
5. Click `Send` and inspect the response.

Example JSON payload for creating a case:

```json
{
  "recordNo": "CASE-001",
  "title": "Example Case",
  "description": "Short description"
}
```

## Tests

Run tests with:

```powershell
mvnw.cmd test
```

## Where to look in the code

Controllers are in `src/main/java/com/dailanalytics/dailanalytics/controller`.

## Next steps
- Add a Postman collection export (optional)
- Add environment variables or a detailed API spec (OpenAPI)

---
Made concise for quick developer use.
