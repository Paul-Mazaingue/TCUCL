# TCUCL — Mono-repo (Front + Back)

Ce dépôt contient les deux parties de l'application :
- Back‑end Java Spring Boot : Back-TCUCL
- Front‑end Angular : Front-TCUCL/frontend

Prerequis
- Java 17+ (ou version compatible avec le projet)
- Maven (ou utiliser le wrapper `mvnw`)
- Node.js 18+ et npm (ou yarn/pnpm)
- (facultatif) Angular CLI si vous voulez utiliser `ng` globalement

Lancer en développement

1) Démarrer le backend (port par défaut : 8080)
- Avec Maven wrapper (recommandé si présent) :
  - Windows : cd Back-TCUCL && .\mvnw spring-boot:run
  - Unix : cd Back-TCUCL && ./mvnw spring-boot:run
- Ou avec Maven installé :
  - cd Back-TCUCL && mvn spring-boot:run

2) Démarrer le frontend (Angular, port par défaut : 4200)
- cd Front-TCUCL/frontend
- Installer dépendances : `npm install`
- Lancer en dev : `ng serve` ou `npm start`
- L'application frontend attend l'API à `http://localhost:8080/api` (voir `api-endpoints.ts`).


Dépannage rapide
- Erreurs CORS : en développement, la config backend inclut une config CORS autorisant `http://localhost:4200`.
- Jeton / Auth : plusieurs appels nécessitent un token JWT — se connecter via l'interface pour l'obtenir.
- Vérifier la présence des secret dans application.properties du back
