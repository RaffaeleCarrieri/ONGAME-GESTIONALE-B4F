# Ongame Gestionale

Backend Spring Boot 3 (Java 17) per la gestione clienti/sedi delle fumetterie Ongame.
Auth tramite Supabase Auth (il backend valida i JWT), DB Postgres su Supabase, migrazioni con Flyway.

## Avvio
1. Copia `.env.example`, compila i valori ed esportali come variabili d'ambiente
   (`PII_ENCRYPTION_KEY`: `openssl rand -base64 32`).
2. `mvn spring-boot:run`
3. Swagger UI: http://localhost:8080/swagger-ui.html

## Primo accesso
1. In Supabase disattiva le registrazioni pubbliche (Auth > Providers > Email).
2. `POST /api/auth/registrazione` crea il primo PROPRIETARIO (funziona solo con tabella `utenti` vuota).
3. `POST /api/auth/login`, copia `access_token` e incollalo in **Authorize** su Swagger.

## Da sviluppare
- CRUD utenti (`/api/utenti`), clienti, consensi, offerte, preferenze/questionario
- Invio WhatsApp e salvataggio in `messaggi_inviati` (vedi `CompleanniService`)
- Filtro per sede in base a `utenti_sedi`, audit su `audit_log`
- `SedeController` e' il CRUD di riferimento da replicare
