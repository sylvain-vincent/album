Album
==================

Le but de ce projet est d'afficher une liste de titres d'albums pour un test technique 

Architecture
==================

🚧
L'architecture mise en place est inspirée de [Now in Android](https://developer.android.com/series/now-in-android)

La clean architecture + MVVM sera utilisé ici
Le but étant de séparer le code en plusieurs couches pour afin d'avoir une séparation des préoccupations et d'avoir un code plus maintenable et testable.


**modules :** 

**:app** : Point de départ de l'app, c'est ici qu'on va gérer les thèmes, la navigation, etc.
**:core** : Module parent où se trouve plusieurs modules qui ne sont pas directement des features  
**:core:data** : C'est ici qu'on va récupérer des données de divers sources comme depuis database et network
**:core:database** : Module destiné à la gestion de la base de données pour les données qu'on veut garder en local
**:core:domain** : Module regroupant tous les usecases de l'app (ex : GetTracksUseCase.kt)
**:core:model** : Contient des classes métier qu'on va transiter dans toute l'app (ex : Track.kt)
**:core:network** : Module destiné aux appels réseaux, c'est ici qu'on va faire appel à Retrofit par exemple
**:feature** : Module parent où se trouve les fonctionnalités et parcours utilisation de l'app 
**:feature:track** : Module où se trouve la page affichant une liste de titres d'albums

Libs
==================

🚧

**Retrofit** : utilisé pour faire de la communication réseau en envoyant des requêtes HTTP et en gérant les réponses 
**Hilt** : utilisé pour faire de l'injection de dépendence et rendre ainsi le code plus modulaire, plus propre et plus facilement testable et maintenable
**Room** : utilisé pour garder des données structurés en local dans une base de donnée

Plugins 
==================

**ksp** : Alternative à kapt. KSP analyse le code en Kotlin code directement, ce qui permet d'effectuer l'analyse 2 fois plus rapidement