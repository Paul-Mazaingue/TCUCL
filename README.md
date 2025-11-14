# Déploiement – Trajectoire Carbone
## Version Dockerisée – Juillet 2025

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
CREATE USER carbonucldata_usermaster WITH PASSWORD 'mdpusermaster';
CREATE DATABASE bdd OWNER carbonucldata_usermaster;
GRANT ALL PRIVILEGES ON DATABASE bdd TO carbonucldata_usermaster;
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
BACKEND_PORT=8080
FRONTEND_PORT=8081

FRONT_ORIGIN=https://trajectoirecarbone.univ-catholille.fr

API_BASE_URL=/api
DEV_MODE=false

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bdd
SPRING_DATASOURCE_USERNAME=carbonucldata_usermaster
SPRING_DATASOURCE_PASSWORD=mdpusermaster

APP_JWT_KEY=XXXXXXXXXXXXXXXXXXXXXX
APP_JWT_EXPIRATION_MS=43200000

SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=XXXXXXXXXXXXXXXXXXXXXX
SPRING_MAIL_PASSWORD=XXXXXXXXXXXXXXXXXXXXXX
SPRING_MAIL_VERIFY_HOST=https://trajectoirecarbone.univ-catholille.fr
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

