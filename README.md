```text
PocketBattleAPI/
├── resources/
├── src/
│   └── nl/
│       └── pocketbattle/
│           └── api/
│               ├── common/error/ApiError.kt
│               ├── database/DatabaseFactory.kt
│               ├── features/
│               │   ├── monster/
│               │   │   ├── dto/
│               │   │   │   ├── CreateMonsterRequest.kt
│               │   │   │   ├── UpdateMonsterRequest.kt
│               │   │   │   └── MonsterResponse.kt
│               │   │   ├── model/
│               │   │   │   ├── Monster.kt
│               │   │   │   ├── FireMonster.kt
│               │   │   │   ├── WaterMonster.kt
│               │   │   │   ├── GrassMonster.kt
│               │   │   │   ├── ElementType.kt
│               │   │   │   └── Damageable.kt
│               │   │   ├── repository/
│               │   │   │   ├── MonsterRepository.kt
│               │   │   │   ├── InMemoryMonsterRepository.kt
│               │   │   │   ├── H2MonsterRepository.kt
│               │   │   │   └── MonstersTable.kt
│               │   │   ├── routes/MonsterRoutes.kt
│               │   │   └── service/MonsterService.kt
│               │   └── battle/
│               │       ├── dto/
│               │       │   ├── BattleRequest.kt
│               │       │   └── BattleResponse.kt
│               │       ├── model/
│               │       │   ├── Attack.kt
│               │       │   └── BattleResult.kt
│               │       ├── routes/BattleRoutes.kt
│               │       └── service/
│               │           ├── BattleService.kt
│               │           └── DamageCalculator.kt
│               ├── plugins/
│               │   ├── Routing.kt
│               │   ├── Serialization.kt
│               │   └── ErrorHandling.kt
│               └── Application.kt
```


## Fases

Mijn voorgestelde definitieve fasering

Fase 1 — Kotlin, OO en spelregels

Ontwikkel monsters, aanvallen, overerving, abstracte klassen, interfaces en een eenvoudig beurtgevecht. Voeg direct unittests toe.

Resultaat: twee monsters kunnen zonder API of database een volledig gevecht uitvoeren.




Fase 2 — In-memory opslag, services en FP

Maak een MonsterRepository-interface, een InMemoryMonsterRepository en een MonsterService. Implementeer CRUD, zoeken, filteren, null safety, lambdas en collectiebewerkingen.

Resultaat: monsters kunnen worden beheerd en opgezocht zonder database.




Fase 3 — Ktor REST API

Maak routes, DTO's, JSON-serialisatie, requestvalidatie, HTTP-foutafhandeling en integratietests. De routes gebruiken de bestaande services.

Resultaat: monsters beheren en gevechten uitvoeren via HTTP-verzoeken.




Fase 4 — H2-persistentie en architectuur

Maak een H2MonsterRepository met Exposed. Test dat de servicelaag met beide repository-implementaties werkt en dat gegevens bewaard blijven.

Resultaat: dezelfde API werkt met databaseopslag, zonder grote wijzigingen aan services en routes.



Waarom dit goed is voor je portfolio
Je kunt bij iedere fase precies hetzelfde documentatiepatroon gebruiken:
1. Wat wilde ik bereiken? Een concreet functioneel doel.
2. Welke leerstof heb ik toegepast? Bijvoorbeeld abstracte klassen, interfaces of lambdas.
3. Welke ontwerpkeuzes heb ik gemaakt en waarom? Inclusief alternatieven.
4. Hoe heb ik het getest? Met een paar duidelijke tests en resultaten.
5. Wat heb ik geleerd? Wat ging goed, wat begreep je eerst niet en wat zou je verbeteren?
   Maak na iedere fase ook een Git-commit. Dan is je stapsgewijze ontwikkeling zichtbaar en controleerbaar.