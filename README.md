# Shift Management API
Spring boot applikation för hantering av skift

## Introduktion
Detta är en Spring Boot applikation för hantering av skift. Applikationen levererar RESTful API endpoints för skapande och läsning av skift. 
Applikationen har en enda endpoint för skapandet av skift och baserat på variablerna som skickas med i förfrågans 'Body' kommer en lämplig 'command' att köras.
Att hämta alla skift tar en sträng från @PathVariable för att avgöra om användaren vill ha skiften sorterade eller ej.



##  Databas-Konfigurering
För att konfigurera databasen, följ dessa steg:

1. **Skapa databasen och användare**:
```sql
CREATE DATABASE ShiftManagement;
CREATE USER 'shift_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ShiftManagement.* TO 'shift_user'@'localhost';
FLUSH PRIVILEGES;
```
2. **Skapa följande stored procedure för att generera 500 slumpmässiga skift**:
```sql
USE ShiftManagement;
DROP PROCEDURE IF EXISTS GenerateDemoShifts;

-- Skapande av 500 slumpmässiga skift som inträffar under 2024
DELIMITER $$

CREATE PROCEDURE GenerateDemoShifts()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE shift_date DATE;
    DECLARE start_time TIME;
    DECLARE end_time TIME;
    DECLARE start_hour INT;
    DECLARE end_hour INT;
    
   WHILE i <= 500 DO
        -- Generera ett slumpmässigt datum som inträffar 2024
        SET shift_date = DATE_ADD('2024-01-01', INTERVAL FLOOR(RAND() * 365) DAY);

        -- Generera slumpmässiga start- och slut timmar
        SET start_hour = 5 + FLOOR(RAND() * 7); 
        SET end_hour = start_hour + 6 + FLOOR(RAND() * 4);
        
        -- Bestämma start- och slut tiderna
        SET start_time = MAKETIME(start_hour, 0, 0);
        SET end_time = MAKETIME(end_hour, 0, 0);
        
        -- Inserta skiftet till DB
        INSERT INTO Shift (shift_date, start_time, end_time, recurring) 
        VALUES (shift_date, start_time, end_time, FALSE);
        
        -- Öka datum och räknare
        SET shift_date = DATE_ADD(shift_date, INTERVAL FLOOR(365 / 500) DAY);
        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;
```
3. **Uppdatera 'application.properties' filen med dina MySQL-databas referenser**:
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.show_sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/shiftmanagement
spring.datasource.username=shift_user
spring.datasource.password=password


4. **När applikationen startas och endast då, kalla på vår nyskapade stored procedure**:
 ```sql
   -- Kalla på den nya stored procedure för att generera skiften. Detta görs endast när-
   -- applikation körs p.g.a att 'spring.jpa.hibernate.ddl-auto=create-drop' kommer att skapa tabellen när programmet startas och-
   -- ta bort tabellen när programmet stängs
 CALL GenerateDemoShifts();
```

