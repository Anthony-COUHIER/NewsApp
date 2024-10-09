# NewsApp

## Description

Cette application affiche les actualités à la une en utilisant l'API de [https://newsapi.org/docs](https://newsapi.org/docs). Elle se compose de deux écrans :

* Un écran principal affichant une liste des articles avec leur titre et image.
* Un écran de détail présentant plus d'informations sur un article (titre, image, description, et un lien pour lire l'article complet).

| Liste des articles                                 | Détail d'un article                                 |
|----------------------------------------------------|-----------------------------------------------------|
| ![Liste des articles](screenshots/home_screen.png) | ![Détail d'un article](screenshots/detail_news.png) |


## Architecture

Pour ce projet, j'ai choisi d'utiliser une architecture Clean. Cette approche permet une meilleure organisation du code et facilite l'extensibilité et la maintenance à long terme.

## Technologies utilisées

* Kotlin : Langage principal pour le développement.
* Jetpack Compose : Pour la gestion de l'interface utilisateur.
* ViewModel : Pour la gestion de l'état de l'interface.
* Koin : DI (remplace le traditionnel Hilt, facile a mettre en place et est semblable a l'utilisation de Hilt avec les annotations)
* Ktor : Pour les appels API.
* Kotlinx-json : Pour la sérialisation des objets JSON.
* JUnit : Pour les tests unitaires.
* Mockk : Pour simuler les dépendances dans les tests.

## Tests

Les tests unitaires sont principalement situés dans la couche Domain pour s'assurer que les règles métiers sont bien respectées.

## Améliorations possibles

Étant donné le temps limité, certaines améliorations pourraient être apportées dans un cadre de production :
* Gestion de l'offline : Utilisation de Room pour stocker en local les articles et offrir une expérience hors ligne.
* Pull to refresh
* Filtrer les articles
* Utilisation des modules pour séparer les couches par module (ou par feature)


## Problèmes identifiés

* Gestion des erreurs : Le projet gère les erreurs réseau, évite les crash. Une gestion plus sophistiquée pourrait être envisagée pour améliorer l'expérience utilisateur (ex : snackbar spécifiques pour les erreurs).
* Localisation : L'application est en anglais. Une gestion de la localisation pourrait être ajoutée pour offrir une meilleure expérience aux utilisateurs.
* Design : L'application est très basique en termes de design. Une amélioration de l'interface utilisateur pourrait être envisagée pour offrir une meilleure expérience utilisateur.

## Temps consacré

J'ai consacré environ 7h heures à ce projet, réparties comme suit :

* Mise en place du projet : 1 heure.
* Développement des fonctionnalités principales : 5 heures.
* Tests unitaires : 1 heure.

## Installation

Clonez le dépôt :

```bash
git clone https://github.com/mon-compte/newsapp.git
```

Ajoutez une clé API valide de NewsAPI dans le fichier local.properties :
```
API_KEY="YOUR_API_KEY"
```
