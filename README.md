# NewsApp
Description
Cette application affiche les actualités à la une en utilisant l'API de NewsAPI. Elle se compose de deux écrans :

Un écran principal affichant une liste des articles avec leur titre et image.
Un écran de détail présentant plus d'informations sur un article (titre, image, description, et un lien pour lire l'article complet).
L'application s'adapte automatiquement à la langue du téléphone pour afficher les actualités correspondantes.

Architecture
Pour ce projet, j'ai choisi d'utiliser une architecture Clean. Cette approche permet une meilleure organisation du code et facilite l'extensibilité et la maintenance à long terme.

L'architecture est divisée en trois couches principales :

Presentation Layer (UI et ViewModel) : Gérée avec Jetpack Compose pour afficher les données. Les états sont gérés via des ViewModel qui communiquent avec le domaine pour récupérer les données.
Domain Layer (Use Cases) : Contient la logique métier, isolée des frameworks et gérée par des use cases.
Data Layer (API et Repository) : Responsable de la gestion des sources de données. Utilisation de Retrofit pour consommer l'API et de Moshi pour la sérialisation JSON.
Technologies utilisées
Kotlin : Langage principal pour le développement.
Jetpack Compose : Pour la gestion de l'interface utilisateur.
ViewModel : Pour la gestion de l'état de l'interface.
Retrofit : Pour les appels API.
Moshi : Pour la sérialisation des objets JSON.
JUnit : Pour les tests unitaires.
Mockk : Pour simuler les dépendances dans les tests.
Choix techniques
Clean Architecture
J'ai opté pour Clean Architecture afin d'assurer une séparation claire des responsabilités et faciliter les tests unitaires. L'isolation de la logique métier dans la couche Domain permet également de réutiliser cette logique indépendamment de la couche UI ou de l'API.

Tests
Les tests unitaires sont principalement situés dans la couche Domain pour s'assurer que les règles métiers sont bien respectées. Des tests d'intégration ont été réalisés sur le Data Layer pour valider la communication avec l'API.

Gestion de la langue
L'API est appelée en utilisant la langue du téléphone, récupérée via Locale.getDefault().language. Cela permet de retourner les articles pertinents selon la région et la langue de l'utilisateur.

Améliorations possibles
Étant donné le temps limité, certaines améliorations pourraient être apportées dans un cadre de production :

Gestion de l'offline : Utilisation de Room pour stocker en local les articles et offrir une expérience hors ligne.
Pagination : Charger progressivement les articles lors du défilement de la liste.
Tests d'interface utilisateur : Ajouter des tests instrumentés pour vérifier l'UI dans différents scénarios.
Problèmes identifiés
Limitation de l'API gratuite : L'API de NewsAPI impose certaines restrictions (par exemple, un nombre limité d'appels par jour). Cela pourrait poser problème dans un cadre de production, surtout pour une application avec un grand volume d'utilisateurs.
Gestion des erreurs : Le projet gère les erreurs réseau et les erreurs API de manière basique (via des messages d'erreurs). Une gestion plus sophistiquée pourrait être envisagée pour améliorer l'expérience utilisateur (ex : écrans spécifiques pour les erreurs).
Temps consacré
J'ai consacré environ X heures à ce projet, réparties comme suit :

Mise en place du projet : X heures.
Développement des fonctionnalités principales : X heures.
Tests unitaires : X heures.
Rédaction de la documentation : X heures.
Installation
Clonez le dépôt :
bash
Copy code
git clone https://github.com/mon-compte/newsapp.git
Ouvrez le projet dans Android Studio.
Ajoutez une clé API valide de NewsAPI dans le fichier local.properties :
bash
Copy code
NEWS_API_KEY=your_api_key_here
Compilez et exécutez l'application sur un appareil ou un émulateur.
Conclusion
Ce projet met en avant une approche modulable et testable en suivant les bonnes pratiques recommandées pour une application mobile Android moderne. Bien que l'exercice soit simple, j'ai privilégié la qualité du code, la séparation des responsabilités et les tests pour fournir une base solide.
