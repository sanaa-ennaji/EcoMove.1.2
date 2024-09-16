# EcoMove1.2

## Brief 2
![alt text](https://github.com/sanaa-ennaji/EcoMove1.2/blob/main/assets/ECO2.drawio.png)

**Titre:** Module de gestion des clients et des réservations de la plateforme EcoMove  
**Description rapide:** Module de gestion des clients et des réservations de la plateforme EcoMove

### Contexte

EcoMove développe une plateforme de gestion de billets de transport qui permet aux clients de rechercher et réserver des billets pour différents moyens de transport (avion, train, bus). Le système est entièrement en mode console et ne nécessite aucune authentification des utilisateurs. Il doit gérer l'enregistrement des informations clients, la recherche de billets, et la réservation, le tout sans gestion de paiement.

### Travail demandé

1. **Enregistrement des informations clients**
    - **Objectif :** La plateforme doit enregistrer les informations des clients pour permettre des recherches et des réservations de billets.
    - **Fonctionnalités :**
        - L'utilisateur entre les informations suivantes lors de l'inscription :
            - Nom
            - Prénom
            - Adresse e-mail
            - Numéro de téléphone
        - Si un client est déjà enregistré, ses informations doivent être récupérées pour faciliter la réservation.
        - Si le client n’existe pas, la plateforme propose automatiquement de créer son profil sans authentification.

2. **Recherche des clients**
    - **Objectif :** Identifier si un client existe déjà dans la base de données avant toute opération de recherche ou de réservation de billets.
    - **Fonctionnalités :**
        - La plateforme doit rechercher un client à partir de ses informations de base (nom, e-mail, etc.).
        - Si le client n'existe pas, la plateforme propose de créer un profil immédiatement pour permettre l'accès aux fonctionnalités de réservation.

3. **Recherche et réservation de billets**
    - **Objectif :** Permettre aux clients d’effectuer une recherche de billets et de les réserver.
    - **Fonctionnalités :**
        - Une fois identifié, le client peut effectuer une recherche de billets en entrant les critères suivants :
            - Ville de départ
            - Ville de destination
            - Date de départ
        - La plateforme recherche les billets disponibles et affiche les détails complets du trajet :
            - Transporteur
            - Horaire et durée
            - Prix
        - Le client peut sélectionner et réserver un billet.

4. **Gestion des réservations**
    - **Objectif :** Suivre les billets réservés par les clients.
    - **Fonctionnalités :**
        - Une fois qu'un billet est réservé, la plateforme doit enregistrer la réservation sous le profil du client.
        - Les informations de réservation incluent :
            - Détails du trajet
            - Compagnies concernées
            - Prix du billet
        - Les réservations doivent pouvoir être consultées à tout moment par le client, avec la possibilité de les annuler.

### User Stories

1. **Enregistrement d'un nouveau client**
    - En tant que utilisateur non enregistré,
    - Je veux pouvoir entrer mes informations personnelles (nom, prénom, adresse e-mail, numéro de téléphone),
    - Afin de créer un profil client pour pouvoir rechercher et réserver des billets.

2. **Connexion d'un client existant**
    - En tant que utilisateur existant,
    - Je veux pouvoir entrer mon nom ou mon adresse e-mail,
    - Afin de retrouver et mettre à jour mes informations personnelles pour effectuer des réservations sans avoir à recréer un profil.

3. **Recherche de billets**
    - En tant que client authentifié,
    - Je veux pouvoir entrer des critères de recherche tels que ville de départ, ville de destination, et date de départ,
    - Afin de trouver les billets disponibles correspondant à ma recherche.

4. **Affichage des résultats de recherche**
    - En tant que client ayant effectué une recherche,
    - Je veux voir les détails des billets disponibles, y compris le transporteur, l'horaire, la durée et le prix,
    - Afin de choisir le billet qui correspond le mieux à mes besoins.

5. **Réservation d'un billet**
    - En tant que client,
    - Je veux pouvoir sélectionner un billet parmi les résultats de recherche et confirmer ma réservation,
    - Afin de réserver un billet pour le trajet désiré et obtenir une confirmation de réservation.

6. **Consultation des réservations**
    - En tant que client,
    - Je veux pouvoir consulter la liste de mes réservations actuelles et afficher les détails de chaque billet réservé,
    - Afin de vérifier mes réservations et les informations associées.

7. **Annulation d'une réservation**
    - En tant que client,
    - Je veux pouvoir annuler une réservation si mes plans changent,
    - Afin de libérer le billet et mettre à jour mes réservations.

8. **Mise à jour des informations personnelles**
    - En tant que client,
    - Je veux pouvoir mettre à jour mes informations personnelles (adresse e-mail, numéro de téléphone) après l'enregistrement,
    - Afin de m'assurer que mes informations sont toujours à jour.

9. **Gestion des erreurs de recherche**
    - En tant que client,
    - Je veux recevoir un message clair si aucune correspondance n'est trouvée lors de la recherche de billets,
    - Afin de comprendre que les critères de recherche n'ont pas abouti et pouvoir réessayer avec de nouveaux critères.

10. **Gestion des préférences de recherche**
    - En tant que client,
    - Je veux pouvoir enregistrer mes préférences de recherche (comme les villes préférées, les horaires de départ, les types de transport),
    - Afin de faciliter la recherche future et obtenir des résultats plus adaptés à mes préférences personnelles.

### Les technologies et concepts à utiliser et à connaître/Maîtriser

- JVM, JDK, JRE
- UML
- Java POO
- Base de données PostgreSQL
- JDBC
- Interfaces et interfaces fonctionnelles
- Lambda expression
- Java Time API
- Optional
- Map et Hashmap
- Stream API
- Collection API
- Classes internes
- Classes anonymes
