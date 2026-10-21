# ROADMAP — PocketBattleAPI

> **Status:** gepland · **Technologie:** Kotlin, Kotlin Toolchain / Amper (`module.yaml`), Ktor, kotlinx.serialization, H2 en Exposed DSL  
> **Doelgroep:** tweedejaars HBO Informatica · **Taal:** code in het Engels, uitleg en portfolio in het Nederlands

## Projectdoel en aanpak

PocketBattleAPI is een kleine Pokémon-geïnspireerde **turn-based battle simulator**. Het project levert eerst een zelfstandig werkend gevechtsdomein op, daarna in-memory CRUD en functionele bewerkingen, vervolgens een Ktor REST API en uiteindelijk H2-persistentie. Het project is tegelijk een gedocumenteerd leerportfolio voor Kotlin, OO, FP en backendontwikkeling.

**Prioriteit bij tijdsdruk:** eerst werkende, geteste kernfunctionaliteit en documentatie; geen extra features. De code wordt volledig aangeleverd om later te bestuderen, met theorie, uitleg, tests, Git-momenten en quizzen. Resultaten worden nooit als uitgevoerd voorgesteld zonder bevestiging.

### Drie oplevermijlpalen

| Mijlpaal | Fasen | Aantoonbaar resultaat | Prioriteit |
| --- | --- | --- | --- |
| **1 — Battle simulator** | Fase 1 | Drie monstertypes, schade, beurtwisseling, winnaar en unittests | Hoogst |
| **2 — In-memory REST API** | Fasen 2–3 | Repository, CRUD, FP, Ktor, JSON, validatie en integratietests | Hoog |
| **3 — Persistente API** | Fase 4 | File-based H2, Exposed, uitwisselbare repositories en persistentietests | Uitbreiding |

### Voortgang

- [ ] Mijlpaal 1 — Battle simulator
- [ ] Mijlpaal 2 — In-memory REST API
- [ ] Mijlpaal 3 — Persistente API

---

# 1. Technische uitgangspunten

We ontwikkelen met:

- **Kotlin**
- **JetBrains Kotlin Toolchain / Amper**
- `module.yaml`, dus geen Gradle
- **Ktor** voor de REST API
- **kotlinx.serialization** voor JSON
- **H2** als database
- **Exposed DSL** voor databasebewerkingen
- **Unit- en integratietests**
- **Git** voor versiebeheer

Gebruik moderne, gangbare Kotlin-conventies en houd de code begrijpelijk voor een tweedejaars HBO Informatica-student.

De code, klassennamen, functienamen en bestandsnamen zijn in het Engels. De uitleg, documentatie en quizvragen zijn in het Nederlands.

Gebruik een eenvoudige architectuur. Vermijd onnodige frameworks, ingewikkelde abstracties en overengineering.

Controleer bij afhankelijkheden en configuratie of ze passen bij de daadwerkelijk gebruikte Kotlin Toolchain en bibliotheekversies. Ga niet zomaar uit van Gradle-conventies.

---

# 2. Het project: PocketBattleAPI

PocketBattleAPI is een kleine turn-based battle simulator met drie elementtypes:

- Fire
- Water
- Grass

Monsters hebben onder andere een naam, type, level, HP en aanvallen.

De simulator ondersteunt:

- Monsters aanmaken.
- Monsters bekijken.
- Monsters wijzigen.
- Monsters verwijderen.
- Monsters zoeken, filteren en sorteren.
- Aanvallen met verschillende elementtypes.
- Schadeberekening en type-effectiviteit.
- Een volledig gesimuleerd beurtgevecht.
- Een winnaar bepalen.
- REST-endpoints met JSON.
- Validatie en duidelijke foutmeldingen.
- Eerst in-memory opslag.
- Later H2-persistentie.
- Unit- en integratietests.

We bouwen **geen frontend, gebruikersaccounts, authenticatie, multiplayer of ingewikkeld spelmanagement**.

Het project moet klein genoeg blijven om af te ronden, maar inhoudelijk rijk genoeg zijn om de Kotlin-leerstof aan te tonen.

---

# 3. Definitieve ontwerpbesluiten

## 4.1 Turn-based battle simulator

We bouwen een simulator waarbij één HTTP-request een volledig gevecht kan uitvoeren.

Bijvoorbeeld:

`POST /battles`

De request bevat de ID's van twee monsters.

De simulator laat beide monsters intern om de beurt aanvallen totdat één monster verslagen is.

De response bevat bijvoorbeeld:

- De deelnemende monsters.
- De winnaar.
- Het aantal beurten.
- Een overzicht van uitgevoerde aanvallen.
- De toegebrachte schade.
- De eindstatus van het gevecht.

We hoeven hierdoor geen actieve gevechtssessies of afzonderlijke HTTP-requests per beurt te beheren.

## 4.2 Gevechten veranderen opgeslagen monsters niet

Wanneer een gevecht begint, werken we met **onafhankelijke gevechtskopieën**.

Een monster dat tijdens een gevecht 0 HP bereikt, behoudt in de repository zijn oorspronkelijke HP.

Let op dat een oppervlakkige `copy()` niet altijd voldoende is wanneer objecten veranderlijke collecties of geneste objecten bevatten.

Kies een eenvoudige, betrouwbare oplossing, bijvoorbeeld een apart battle-state-model.

## 4.3 Eén repositorycontract

We definiëren één `MonsterRepository`-interface.

Daarvan maken we twee implementaties:

- `InMemoryMonsterRepository`
- `H2MonsterRepository`

De `MonsterService` werkt tegen de interface en is niet rechtstreeks afhankelijk van H2 of Exposed.

De bestaande services en routes moeten bij het vervangen van de repository zoveel mogelijk ongewijzigd blijven.

## 4.4 Scheiding van verantwoordelijkheden

Houd een duidelijke scheiding tussen:

- Domeinmodellen.
- Spelregels en schadeberekening.
- Services.
- Repositoryinterfaces.
- Repositoryimplementaties.
- REST-routes.
- Request- en response-DTO's.
- Databaseconfiguratie.
- Foutafhandeling.

Routes mogen geen ingewikkelde businesslogica bevatten.

## 4.5 Geen geforceerde Kotlin-concepten

We willen veel Kotlin-, OO- en FP-concepten behandelen, maar niet ten koste van een goed ontwerp.

Wanneer een concept niet natuurlijk in de applicatie past, maken we daarvoor een klein zelfstandig leerexperiment of portfolio-voorbeeld.

Leg dan duidelijk uit waarom het concept niet in de productiecode is opgenomen.

---

# 4. Lessen en ontwikkelfasen

We behouden vier ontwikkelfasen en drie oplevermijlpalen.

## Fase 1 — Kotlin, OO en spelregels

**Doel:** twee monsters kunnen zonder API of database een volledig gevecht uitvoeren.

### Les 1.1 — Kotlin-fundamenten

Behandel:

- `fun main()`
- Functies.
- `val` en `var`.
- Types en type inference.
- String templates.
- Packages en imports.
- `if`, `when`, `for`, `while`.
- Expressions versus statements.
- `Unit`, `Any` en `Nothing`.

### Les 1.2 — Klassen en objecten

Behandel:

- Classes en objecten.
- Properties.
- Primary en secondary constructors.
- `init`-blokken.
- Default parameters.
- Member functions.
- Encapsulatie.
- Visibility modifiers.
- Getters en setters.
- Invarianten.

Bouw een monster met HP en functies om schade te ontvangen en te herstellen.

HP mag niet onder nul of boven de maximale HP komen.

### Les 1.3 — Overerving en abstracte klassen

Behandel:

- `open`.
- `abstract`.
- Overerving.
- `override`.
- `super`.
- Polymorfisme.
- Overerving versus compositie.

Bouw een abstracte `Monster`-klasse en drie concrete monstertypes:

- `FireMonster`
- `WaterMonster`
- `GrassMonster`

Gebruik verschillen in gedrag alleen wanneer ze inhoudelijk zinvol zijn.

### Les 1.4 — Interfaces en polymorfisme

Behandel:

- Interfaces.
- Interface-implementatie.
- Abstracte klasse versus interface.
- Programmeren tegen contracten.
- Polymorfisme.

Onderzoek of een interface zoals `Damageable` daadwerkelijk meerwaarde heeft.

### Les 1.5 — Aanvallen en data classes

Behandel:

- Data classes.
- `equals()`, `hashCode()` en `toString()`.
- `copy()`.
- Destructuring.
- Enums.
- Compositie.
- Operatoren.
- Function overloading.

Bouw onder andere `Attack` en `ElementType`.

### Les 1.6 — Schadeberekening

Behandel:

- Functies met parameters en returntypes.
- Expression bodies.
- Pure functions.
- Immutability.
- Scheiding tussen berekeningen en veranderlijke toestand.

Bouw een `DamageCalculator`.

Type-effectiviteit:

| Aanvalstype | Sterk tegen | Zwak tegen |
| ----------- | ----------- | ---------- |
| Fire        | Grass       | Water      |
| Water       | Fire        | Grass      |
| Grass       | Water       | Fire       |

Leg de exacte schadeformule vast voordat deze wordt geïmplementeerd.

### Les 1.7 — Exceptions en foutafhandeling

Behandel:

- `throw`.
- `try` en `catch`.
- `require()`.
- `check()`.
- `IllegalArgumentException`.
- Eigen exceptions.
- Fail-fast.

Voorkom ongeldige aanvallen, negatieve aanvalskracht en acties van verslagen monsters.

### Les 1.8 — BattleService

Behandel:

- Samenwerking tussen objecten.
- Verantwoordelijkheden.
- State management.
- Compositie.
- Constructor injection.
- Cohesie en koppeling.

Bouw een `BattleService` die een volledig gevecht simuleert.

### Les 1.9 — Unit testing

Behandel:

- Unit tests.
- Arrange, Act, Assert.
- Testnamen.
- Happy paths.
- Edge cases.
- Deterministische tests.
- Testisolatie.

**Let op: begin al vanaf les 1.2 met tests. Wacht niet tot les 1.9.**

Les 1.9 is bedoeld om de testtheorie te verdiepen en de bestaande testdekking te beoordelen.

### Mijlpaal 1

Een werkende battle simulator met drie monstertypes, schadeberekening, beurtwisseling, winnaar en unittests.

---

## Fase 2 — In-memory opslag, services en functioneel programmeren

**Doel:** monsters beheren zonder externe database.

### Les 2.1 — Collecties en generics

- `List` en `MutableList`.
- `Set` en `MutableSet`.
- `Map` en `MutableMap`.
- `Collection`.
- Generics.
- Mutable versus immutable.

### Les 2.2 — Repository pattern

- Repositoryinterfaces.
- In-memory implementaties.
- Dependency inversion.
- Constructor injection.
- Scheiding tussen opslag en businesslogica.

Maak `MonsterRepository` en `InMemoryMonsterRepository`.

### Les 2.3 — CRUD en MonsterService

- Create, Read, Update, Delete.
- Service layer.
- Validatie.
- Unieke ID's.
- Objectidentiteit.

### Les 2.4 — Null safety

- Nullable types.
- `?.`.
- `?:`.
- `let`.
- Smart casts.
- `!!` en de risico's.
- `requireNotNull()`.

### Les 2.5 — Lambdas en higher-order functions

- Lambda expressions.
- Function types.
- Higher-order functions.
- `it`.
- Trailing lambdas.
- Function references.
- Closures.

### Les 2.6 — Functionele collectiebewerkingen

- `filter`.
- `map`.
- `find`.
- `firstOrNull`.
- `any`, `all`, `none`.
- `count`.
- `sortedBy`.
- `groupBy`.
- `associateBy`.
- `fold`.
- `sumOf`.

### Les 2.7 — Extension functions en scope functions

- Extension functions.
- `let`.
- `run`.
- `with`.
- `apply`.
- `also`.
- Receiver lambdas.
- `this` versus `it`.

### Les 2.8 — Geavanceerde Kotlin-constructies

- `object`.
- Companion objects.
- Delegated properties.
- `lazy`.
- Sealed classes en interfaces.
- Generic functions.
- Variance (`in` en `out`).
- Inline functions.

Behandel deze concepten op hoofdlijnen of met afzonderlijke voorbeelden wanneer ze niet natuurlijk in het project passen.

### Les 2.9 — Service- en repositorytests

Test:

- CRUD.
- Ontbrekende monsters.
- Validatie.
- Filteren.
- Sorteren.
- Repositorygedrag.

---

## Fase 3 — Ktor REST API

**Doel:** de bestaande functionaliteit via HTTP beschikbaar maken.

### Les 3.1 — HTTP en REST

- Client-servermodel.
- HTTP-methoden.
- Statuscodes.
- URL's.
- Path parameters.
- Query parameters.
- Requests en responses.
- REST-principes.

### Les 3.2 — Ktor-applicatiestructuur

- `Application.kt`.
- Plugins.
- Routing.
- Dependency wiring.
- Serverconfiguratie.
- `module.yaml`.

### Les 3.3 — MonsterRoutes

Bouw:

| Methode | Endpoint              | Doel                |
| ------- | --------------------- | ------------------- |
| GET     | `/monsters`           | Alle monsters       |
| GET     | `/monsters/{id}`      | Eén monster         |
| POST    | `/monsters`           | Monster aanmaken    |
| PUT     | `/monsters/{id}`      | Monster wijzigen    |
| DELETE  | `/monsters/{id}`      | Monster verwijderen |
| GET     | `/monsters?type=FIRE` | Filteren            |

### Les 3.4 — DTO's en JSON

- DTO's versus domeinmodellen.
- `@Serializable`.
- JSON.
- Request-DTO's.
- Response-DTO's.
- Mapping.

### Les 3.5 — Validatie en HTTP-fouten

- `400 Bad Request`.
- `404 Not Found`.
- `409 Conflict`.
- `500 Internal Server Error`.
- Centrale foutafhandeling.
- Consistente foutresponses.

### Les 3.6 — BattleRoutes

Bouw:

`POST /battles`

Deze route roept de bestaande `BattleService` aan en retourneert het gevechtsresultaat als JSON.

### Les 3.7 — Integratietests

- Ktor `testApplication`.
- HTTP-responses controleren.
- Testisolatie.
- Geldige en ongeldige requests.
- API-integratietests.

### Mijlpaal 2

Een werkende Ktor REST API met in-memory opslag, CRUD, gevechten, FP, services en integratietests.

---

## Fase 4 — H2-persistentie

**Doel:** dezelfde API laten werken met een persistente database.

### Les 4.1 — SQL en databases

- Tabellen.
- Rijen en kolommen.
- Primary keys.
- Constraints.
- SQL CRUD.
- Databaseverbindingen.
- In-memory versus file-based H2.

### Les 4.2 — H2 en Exposed

- Dependencies.
- `module.yaml`.
- Databaseconfiguratie.
- `DatabaseFactory`.
- File-based H2.

### Les 4.3 — Tabellen en mapping

- SQL DSL.
- Tabellen.
- Domeinobjecten versus databasegegevens.
- Objectmapping.
- Transacties.
- Opslag van verschillende monstertypes.

### Les 4.4 — H2MonsterRepository

Implementeer hetzelfde `MonsterRepository`-contract met H2 en Exposed.

### Les 4.5 — Repositoryimplementaties verwisselen

- Dependency inversion.
- Losse koppeling.
- Configuratie.
- Testbaarheid.

### Les 4.6 — Persistentietests

Test:

- Opslaan en teruglezen.
- Bijwerken.
- Verwijderen.
- Verschillende monstertypes.
- Gegevens behouden na opnieuw openen van de database.
- Gelijkwaardig gedrag van beide repositoryimplementaties.

### Mijlpaal 3

Een persistente applicatie met H2, Exposed en uitwisselbare repositoryimplementaties.

---

# 5. Leer- en ontwikkelwerkwijze

**Vaste volgorde bij iedere les.**

## Stap 1 — Theorie

Leg kort en begrijpelijk uit:

- Wat het concept betekent.
- Waarom het bestaat.
- Wanneer je het gebruikt.
- Hoe het in PocketBattleAPI wordt toegepast.

Gebruik waar nodig kleine voorbeelden.

Houd de theorie gericht op een tweedejaars HBO Informatica-student.

## Stap 2 — Volledige implementatie

Schrijf alle benodigde code volledig uit.

**Verplichte regels voor Kotlin-code:**

- Geef complete, direct kopieerbare bestanden.
- Gebruik afzonderlijke codeblokken per bestand.
- Zet op de eerste regel een commentaar met het relatieve bestandspad.
- Geef geen losse patches of uitsluitend gewijzigde fragmenten.
- Behoud bestaande functionaliteit.
- Houd de code eenvoudig en begrijpelijk.
- Voeg zinvolle comments toe bij belangrijke logica en ontwerpkeuzes.
- Vermijd comments die alleen letterlijk herhalen wat een eenvoudige regel doet.
- Vermeld eventuele aanpassingen aan `module.yaml` volledig.
- Leg uit welke bestanden nieuw zijn en welke worden vervangen.

## Stap 3 — Code-uitleg

Leg uit:

- Welke verantwoordelijkheid iedere klasse heeft.
- Hoe objecten samenwerken.
- Welke Kotlin-concepten zijn toegepast.
- Waarom deze implementatie is gekozen.
- Welke alternatieven mogelijk waren.
- Welke fouten of valkuilen belangrijk zijn.

## Stap 4 — Tests

Lever passende tests bij de implementatie.

Geef aan:

- Wat iedere test controleert.
- Hoe ik de tests uitvoer.
- Welke resultaten we verwachten.
- Welke resultaten daadwerkelijk bevestigd zijn.

**Verzin nooit testresultaten.**

Als ik nog geen testoutput heb gegeven, vermeld dan dat de tests nog niet bevestigd zijn.

## Stap 5 — Git

Geef na een betekenisvolle wijziging een voorgestelde commit message.

Bijvoorbeeld:

`feat: implement monster damage calculation`

Vermeld kort welke wijzigingen bij die commit horen.

Ga er nooit vanuit dat ik de commit al heb gemaakt.

## Stap 6 — Multiplechoicevragen

Stel na de uitleg ongeveer vijf multiplechoicevragen.

Gebruik:

- Vier antwoordmogelijkheden per vraag.
- Eén correct antwoord, tenzij expliciet anders aangegeven.
- Een combinatie van theorie, codebegrip en ontwerpkeuzes.
- Korte feedback na mijn antwoorden.
- Een totaalscore.
- Een korte indicatie welke onderwerpen ik moet herhalen.

Gebruik de vragen als snelle kennispeiling, niet als formeel bewijs dat ik alles beheers.

## Stap 7 — Markdown-overdracht voor de website-AI

**Dit is een verplicht onderdeel van iedere afgeronde les.**

Wanneer de implementatie, uitleg, tests en quiz zijn afgerond, wordt automatisch een compleet Markdown-document opgesteld.

Dat document dient als overdracht aan een **tweede AI**, die verantwoordelijk is voor de documentatiewebsite.

De overdracht hoort standaard bij iedere afgeronde les.

---

# 6. Markdown-overdrachtsdocument

Na iedere afgeronde les wordt **één volledig Markdown-codeblok** geleverd.

Gebruik vier backticks voor het buitenste Markdown-codeblok, zodat interne Kotlin-codeblokken correct blijven werken.

Het document moet zonder aanvullende uitleg of toegang tot onze chat bruikbaar zijn voor de website-AI.

Gebruik deze structuur:

## Metadata

- Projectnaam.
- Fase.
- Lesnummer.
- Lestitel.
- Bijbehorende mijlpaal.
- Status.
- Datum, indien bekend.

## Leerdoelen

Wat moet ik na deze les kunnen uitleggen en herkennen?

## Theorie

Volledige maar begrijpelijke uitleg van de behandelde concepten.

## Praktijk in PocketBattleAPI

Welke functionaliteit hebben we gebouwd?

## Implementatie

Vermeld:

- Relevante bestandspaden.
- Klassen.
- Interfaces.
- Functies.
- Belangrijke properties.
- Codevoorbeelden.
- Hoe onderdelen samenwerken.

Gebruik de daadwerkelijk gebouwde code. Verzin geen implementaties.

## Ontwerpkeuzes

Beschrijf:

- Waarom deze oplossing is gekozen.
- Welke alternatieven er zijn.
- Voor- en nadelen.
- Eventuele vereenvoudigingen.

## Tests

Beschrijf:

- Toegevoegde tests.
- Geteste scenario's.
- Verwachte resultaten.
- Daadwerkelijk bevestigde resultaten.
- Nog niet uitgevoerde tests.

## Git

Beschrijf:

- Voorgestelde commit message.
- Bestanden of functionaliteiten die bij de commit horen.
- Of de commit daadwerkelijk is bevestigd.

## Quiz

Lever ongeveer vijf multiplechoicevragen in een consistente, machineleesbare structuur.

Iedere vraag bevat:

- Unieke vraag-ID.
- Vraagtekst.
- Vier antwoordopties.
- Correct antwoord.
- Uitleg bij het correcte antwoord.
- Korte feedback voor onjuiste antwoorden.
- Onderwerp of leerdoel.
- Moeilijkheidsgraad.

De website-AI moet hiermee zonder extra inhoudelijke keuzes een interactieve quiz kunnen bouwen.

## Reflectie

Beschrijf:

- Belangrijkste leerpunten.
- Lastige concepten.
- Problemen die zijn opgelost.
- Aandachtspunten voor herhaling.

Maak onderscheid tussen vastgestelde ervaringen en voorgestelde reflectiepunten. Verzin geen persoonlijke ervaringen namens mij.

## AI-verantwoording

Beschrijf transparant:

- Waarbij AI heeft geholpen.
- Welke code of uitleg door AI is voorgesteld.
- Welke controles daadwerkelijk door mij zijn uitgevoerd.
- Welke onderdelen nog niet zelfstandig zijn aangetoond.

## Voortgang

Vermeld:

- Afgeronde les.
- Huidige fase.
- Volgende les.
- Eventuele openstaande werkzaamheden.

---

# 7. Documentatiewebsite

Een tweede AI ontwikkelt een **interactieve documentatiewebsite**.

De ontwikkeling van de website valt buiten de scope van PocketBattleAPI, tenzij expliciet gevraagd.

Na iedere les wordt de volledige inhoud aangeleverd via het Markdown-overdrachtsdocument.

De website-AI moet daarmee een leerportfolio kunnen bouwen met:

- Een dashboard.
- Vier ontwikkelfasen.
- Alle 31 lessen.
- Theoriepagina's.
- Codevoorbeelden.
- Implementatie-uitleg.
- Architectuurdocumentatie.
- UML-diagrammen.
- API-documentatie.
- Testoverzichten.
- Git-mijlpalen.
- Reflectie.
- AI-verantwoording.
- Interactieve multiplechoicequizzen.
- Scores en directe feedback.
- Voortgang per les en onderwerp.

De quizzen mogen volledig client-side werken. Er is geen extra backend nodig.

De website moet later ook bruikbaar zijn om zelfstandig de Kotlin-stof te herhalen.

**Belangrijk:** zorg dat je Markdown-overdracht alle noodzakelijke inhoud bevat. De website-AI mag niet afhankelijk zijn van onze gespreksgeschiedenis.

---

# 8. Documentatiekwaliteit

De documentatie moet geschikt zijn voor een HBO Informatica-portfolio.

Documenteer niet alleen *wat* de code doet, maar ook:

- Waarom een ontwerpkeuze is gemaakt.
- Welk OO- of FP-concept wordt toegepast.
- Welke verantwoordelijkheid een klasse heeft.
- Hoe de code is getest.
- Welke beperkingen de implementatie heeft.
- Wat een alternatief ontwerp zou zijn.
- Wat ik later moet kunnen uitleggen tijdens een assessment.

Gebruik begrijpelijke Nederlandse uitleg en correcte technische terminologie.

Zorg dat documentatie en daadwerkelijke implementatie met elkaar overeenkomen.

---

# 9. Git en stapsgewijze ontwikkeling

We werken in kleine, betekenisvolle stappen.

Maak niet meteen alle toekomstige bestanden aan.

Voeg klassen, services, routes en repositories pas toe wanneer ze nodig zijn.

Geef regelmatig logische commitmomenten.

De Git-geschiedenis moet later helpen om te begrijpen hoe het project is opgebouwd.

**Verzin geen Git-commits, testresultaten of ontwikkelgeschiedenis.**

Een voorgestelde commit is pas een uitgevoerde commit wanneer ik dat bevestig.

---

# 10. Prioriteiten en tijdsdruk

Ik wil voorkomen dat het project onnodig groot wordt.

Hanteer deze prioriteiten:

**Prioriteit 1 — Mijlpaal 1**

Een werkende battle simulator met duidelijke OO-concepten en tests.

**Prioriteit 2 — Mijlpaal 2**

Een werkende REST API met in-memory opslag, services, FP en integratietests.

**Prioriteit 3 — Mijlpaal 3**

H2-persistentie met Exposed en repositorycontracttests.

Documentatie wordt tijdens alle drie de mijlpalen opgebouwd.

Wanneer tijdsdruk ontstaat, beperken we uitbreidingen voordat we kernfunctionaliteit, tests of documentatie schrappen.

We bouwen geen extra functionaliteiten alleen omdat ze interessant lijken.

---

# 11. Samenwerkingsafspraken

1. **Werk les voor les en fase voor fase.**
2. **Respecteer mijn gekozen leerwijze: theorie → volledige code → uitleg → testen → Git → quiz → Markdown-overdracht.**
3. Schrijf volledige, direct kopieerbare Kotlin-bestanden.
4. Gebruik bovenaan ieder Kotlin-bestand een commentaar met het relatieve pad.
5. Houd rekening met mijn bestaande projectstructuur en bibliotheekversies.
6. Verander bestaande code niet onnodig.
7. Gebruik eenvoudige, onderhoudbare architectuur.
8. Voeg tests vroeg toe.
9. Maak geen ongefundeerde claims over werkende code of geslaagde tests.
10. Lever na iedere afgeronde les automatisch het Markdown-overdrachtsdocument.
11. Houd het overdrachtsdocument zelfstandig leesbaar voor de website-AI.
12. Gebruik de daadwerkelijk geïmplementeerde code en geen fictieve voorbeelden als bewijs.
13. Wees transparant over AI-gebruik.
14. Houd rekening met mijn tijdsdruk.
15. Stel alleen verduidelijkende vragen wanneer essentiële informatie ontbreekt.

---

# 12. Definitie van gereed per les

Een les is afgerond wanneer:

- De theorie is uitgelegd.
- De benodigde implementatie is geleverd.
- De code is toegelicht.
- De relevante tests zijn aangeleverd.
- De teststatus duidelijk is.
- Een Git-commit is voorgesteld.
- De multiplechoicequiz is behandeld.
- Het complete Markdown-overdrachtsdocument voor de website-AI is geleverd.

Maak onderscheid tussen **inhoudelijk afgerond**, **technisch getest** en **door mij bevestigd**.

---


## 13. Projectstructuur (doelbeeld)

De mappen en bestanden worden **stapsgewijs** aangemaakt. Bestaande `Application.kt`, `Routing.kt` en `module.yaml` worden eerst gecontroleerd voordat ze worden aangepast.

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
├── module.yaml
├── kotlin
└── kotlin.bat
```

Testpaden en dependencies worden vastgesteld op basis van de daadwerkelijke Kotlin Toolchain-configuratie. H2-bestanden worden niet aan Git toegevoegd.

## 14. Definitie van gereed per mijlpaal

**Mijlpaal 1:** een reproduceerbare simulatie met een winnaar; HP-invarianten, type-effectiviteit en gevechtsverloop zijn getest.  
**Mijlpaal 2:** CRUD en gevechten werken via HTTP met correcte JSON, validatie en integratietests; opgeslagen monsters worden niet gewijzigd door simulaties.  
**Mijlpaal 3:** gegevens overleven het sluiten en opnieuw openen van file-based H2; beide repositories voldoen aan hetzelfde contract en de bestaande service- en routelogica blijven grotendeels intact.

**Belangrijk:** een afgevinkte les, een geslaagde test en een uitgevoerde Git-commit worden alleen geregistreerd als ze daadwerkelijk zijn bevestigd.
