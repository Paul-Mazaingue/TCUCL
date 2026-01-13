# Déploiement – Trajectoire Carbone
## Version Dockerisée

## 1. Résumé du fonctionnement

L’application comporte trois composants principaux :

### Backend (Spring Boot)
- Déployé dans un conteneur Docker.
- Exposé sur le port 8080.
- Utilisé via le chemin `/api`.

### Frontend (Angular + Nginx)
- Build Angular → servi via Nginx (conteneur Docker).
- Exposé sur le port 8081.

### Base PostgreSQL
- Installée directement sur la VM (hors Docker).

## 2. Pré-requis système

```bash
sudo apt update && sudo apt upgrade -y
```

## 3. Installation PostgreSQL

```bash
sudo apt install postgresql postgresql-contrib -y
sudo systemctl enable postgresql
sudo systemctl start postgresql
```

### Se connecter à PostgreSQL en tant que superuser

```bash
sudo -u postgres psql
```

### Création base + utilisateur

```sql
CREATE USER example_name WITH PASSWORD 'example_password';
CREATE DATABASE bdd OWNER example_name;
GRANT ALL PRIVILEGES ON DATABASE bdd TO example_name;
```

### Quitter
```sql
\q
```

### Config réseau PostgreSQL

Modifier la ligne dans le fichier `postgresql.conf` (/etc/postgresql/*/main/postgresql.conf) :
```
listen_addresses = '*'
```

Ajouter ces lignes dans le fichier `pg_hba.conf` (/etc/postgresql/*/main/pg_hba.conf) :
```
host    all     all     127.0.0.1/32       md5
host    all     all     172.16.0.0/12      md5
```

### Restart

```bash
sudo systemctl restart postgresql
```

## 4. Installation Docker + Compose

```bash
sudo apt install ca-certificates curl gnupg lsb-release -y
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo "deb [arch=$(dpkg --print-architecture) \
signed-by=/etc/apt/keyrings/docker.gpg] \
https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" \
| sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt update
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
sudo systemctl enable docker
```

## 5. Récupération du projet

```bash
git clone https://github.com/Paul-Mazaingue/TCUCL.git
cd TCUCL
git switch -c Docker origin/Docker
```

## 6. Fichier .env (exemple production)

### Copier le fichier .env
```bash
cp .env.example .env
```

### Éditer le fichier .env et renseigner les variables suivantes :
```bash
nano .env
```

```
# Ports exposed on the host
BACKEND_PORT=8080
FRONTEND_PORT=8081

# Origin of the frontend as seen by the backend (CORS)
FRONT_ORIGIN=http://localhost:8081

# API URL reachable by the browser once the stack is running
API_BASE_URL=http://localhost:8080/api
DEV_MODE=false

# Password reset configuration
APP_RESET_PASSWORD_TOKEN_VALIDITY_SECONDS=600
APP_RESET_PASSWORD_RATE_LIMIT_PER_USER=5
APP_RESET_PASSWORD_RATE_LIMIT_PER_IP=500
APP_RESET_PASSWORD_RATE_LIMIT_WINDOW_MINUTES=60

# Database connection (database stays outside of Docker)
SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/bdd
SPRING_DATASOURCE_USERNAME=example_name
SPRING_DATASOURCE_PASSWORD=example_password

# JWT configuration
APP_JWT_KEY=change-me
APP_JWT_EXPIRATION_MS=43200000

# Mail configuration
# Provider par défaut = internal (relais @univ-catholille.fr). Fallback Gmail si activé.
MAIL_PROVIDER=internal # internal ou gmail
MAIL_FALLBACK_TO_GMAIL=true

# Relais interne
MAIL_INTERNAL_HOST=XX.XX.XX.XX
MAIL_INTERNAL_PORT=XX
MAIL_INTERNAL_LOCALPART=TCUCL-no-reply

# Gmail 
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=
SPRING_MAIL_PASSWORD=
SPRING_MAIL_VERIFY_HOST=http://localhost:8081

# Superadmin (bootstrap)
SUPERADMIN_EMAIL=
```

## 7. Démarrage

```bash
sudo docker compose up -d --build
sudo docker compose ps
```

## 8. Configuration Apache

```
<VirtualHost *:80>
    ServerName trajectoirecarbone.univ-catholille.fr
    ServerAdmin webmaster@localhost

    ErrorLog ${APACHE_LOG_DIR}/trajcarbone_error.log
    CustomLog ${APACHE_LOG_DIR}/trajcarbone_access.log combined

    ProxyPass "/api/" "http://localhost:8080/api/"
    ProxyPassReverse "/api/" "http://localhost:8080/api/"

    ProxyPass "/" "http://localhost:8081/"
    ProxyPassReverse "/" "http://localhost:8081/"
</VirtualHost>

```

## 9. Maintenance

### Mise à jour
```bash
git pull
sudo docker compose down
sudo docker compose up -d --build
```

### Logs
```bash
sudo docker compose logs -f backend
sudo docker compose logs -f frontend
```

## 10. Lancement sans Docker

### Configuration du fichier .env
Créer et éditer le fichier .env à la racine du projet :
```bash
nano .env
```

```
# Ports exposed on the host
BACKEND_PORT=8080
FRONTEND_PORT=8081

# Origin of the frontend as seen by the backend (CORS)
FRONT_ORIGIN=http://localhost:4200

# API URL reachable by the browser once the stack is running
API_BASE_URL=http://localhost:8080/api
DEV_MODE=true

# Database connection (database stays outside of Docker)
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bdd
SPRING_DATASOURCE_USERNAME=username
SPRING_DATASOURCE_PASSWORD=password

APP_RESET_PASSWORD_TOKEN_VALIDITY_SECONDS=600
APP_RESET_PASSWORD_RATE_LIMIT_PER_USER=5
APP_RESET_PASSWORD_RATE_LIMIT_PER_IP=500
APP_RESET_PASSWORD_RATE_LIMIT_WINDOW_MINUTES=60

# JWT configuration
APP_JWT_KEY=changeme
APP_JWT_EXPIRATION_MS=43200000

# Mail configuration
# Provider par défaut = internal (relais @univ-catholille.fr). Fallback Gmail activé.
MAIL_PROVIDER=gmail
MAIL_FALLBACK_TO_GMAIL=false

# Relais interne
MAIL_INTERNAL_HOST=
MAIL_INTERNAL_PORT=
MAIL_INTERNAL_LOCALPART=

# Gmail (inchangé)
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=######################## <-------------------- à remplir
SPRING_MAIL_PASSWORD=######################## <-------------------- à remplir
SPRING_MAIL_VERIFY_HOST=http://localhost:8081

# Superadmin (bootstrap)
SUPERADMIN_EMAIL=######################## <-------------------- à remplir
```

### Base de données

- Installer pgAdmin
- Créer une base PostgreSQL nommée bdd
- Créer un utilisateur correspondant aux identifiants définis dans le .env
- Lui attribuer les droits sur la base

### Backend

- Installer Maven
- Se placer dans le dossier du backend :
```bash
cd .\Back-TCUCL\
```
- Lancer l’application Spring Boot :
```bash
mvn spring-boot:run
```

### Frontend

- Installer Node.js

- Se placer dans le dossier du frontend : 
```bash
cd .\Front-TCUCL\frontend\
```

- Installer les dépendances :
```bash
npm i
```

- Lancer l’application :
```bash
npm start
```
