# PocketBattleAPI — Projectplan, UML-first workflow en leerportfolio

| Onderdeel | Informatie |
|---|---|
| Project | PocketBattleAPI |
| Document | Projectplan en ontwikkelworkflow |
| Versie | 3.0 |
| Datum | 22 oktober 2026 |
| Status | Voorgesteld uitvoeringsplan |
| Programmeertaal | Kotlin |
| Backendframework | Ktor |
| Buildtool | JetBrains Amper |
| Buildconfiguratie | `module.yaml` |
| Database | H2 (file-based) |
| Databasebibliotheek | JetBrains Exposed |
| UML-tool | PlantUML |
| Ontwikkelmethode | Design-first, vertical slicing, incrementeel testen |
| Documentatieformaat | Markdown |
| Hoofddoel | Een werkende Kotlin-backend met aantoonbare leerontwikkeling |

---

# 1. Projectvisie

## 1.1 Wat is PocketBattleAPI?

PocketBattleAPI is een Kotlin/Ktor REST API waarmee gebruikers monsters kunnen beheren en volledig automatische, turn-based gevechten kunnen simuleren.

De applicatie is geïnspireerd op elementaire monstergevechten.

Een gebruiker kan:

1. Monsters aanmaken.
2. Monsters bekijken.
3. Monsters wijzigen.
4. Monsters verwijderen.
5. Monsters filteren op elementtype.
6. Twee monsters selecteren.
7. Een automatisch gevecht starten.
8. Het volledige gevechtsresultaat bekijken.

De simulator voert een compleet gevecht uit binnen één HTTP-request.

Het resultaat bevat:

- De winnaar.
- De verliezer.
- Het aantal uitgevoerde aanvallen.
- Een chronologische gevechtslog.
- De gebruikte aanvallen.
- De veroorzaakte schade.
- De resterende HP na iedere aanval.

## 1.2 Waarom bouwen we dit project?

PocketBattleAPI heeft twee doelen.

### Technisch doel

Een overzichtelijke, werkende en goed geteste backend ontwikkelen waarin de belangrijkste principes van Kotlin, OOP, FP, REST API's en databases worden toegepast.

### Educatief doel

Een HBO Informatica-leerportfolio ontwikkelen waarmee zichtbaar wordt:

- Welke concepten zijn behandeld.
- Hoe deze concepten werken.
- Hoe ze in Kotlin worden toegepast.
- Waarom bepaalde ontwerpkeuzes zijn gemaakt.
- Hoe UML helpt bij het ontwerpen.
- Welke tests de functionaliteit controleren.
- Welke onderdelen door AI zijn gegenereerd.
- Welke onderdelen daadwerkelijk zelfstandig zijn gecontroleerd.
- Welke onderwerpen verdere oefening vereisen.

## 1.3 Belangrijkste uitgangspunt

We bouwen niet een applicatie om zoveel mogelijk Kotlin-concepten kunstmatig te gebruiken.

We bouwen een goede applicatie en gebruiken die als praktisch bewijs voor relevante leerstof.

Concepten die geen natuurlijke toepassing hebben, behandelen we in afzonderlijke leerexperimenten.

---

# 2. De nieuwe ontwikkelfilosofie

## 2.1 Eerst nadenken, daarna coderen

We gebruiken een **design-first aanpak**.

Dat betekent:

1. Eerst bepalen wat een functionaliteit moet doen.
2. Daarna de belangrijkste onderdelen modelleren.
3. Vervolgens verantwoordelijkheden en relaties controleren.
4. Daarna de Kotlin-code implementeren.
5. Vervolgens de code testen.
6. Ten slotte de diagrammen en documentatie controleren en bijwerken.

De UML-diagrammen vormen het uitgangspunt voor de implementatie.

Ze zijn geen onveranderlijke blauwdruk.

Wanneer de implementatie een beter ontwerp oplevert, mag het ontwerp worden aangepast.

De wijziging moet wel worden uitgelegd.

## 2.2 Niet alle diagrammen vooraf

We maken vooraf alleen de diagrammen die nodig zijn om de volgende functionaliteit goed te begrijpen.

Voor de battle engine zijn bijvoorbeeld een Class Diagram, Activity Diagram en Sequence Diagram nuttig.

Een gedetailleerd ERD is pas nodig wanneer we de database gaan implementeren.

Daarom werken we volgens:

**Requirements → PlantUML → Implementatie → Tests → UML bijwerken → Bewijs vastleggen.**

## 2.3 Ontwikkelen in verticale slices

We bouwen PocketBattleAPI in vijf stappen:

- Slice 0 — Requirements en basisontwerp.
- Slice 1 — Werkende battle engine.
- Slice 2 — Monsterbeheer en REST API.
- Slice 3 — H2-persistentie.
- Slice 4 — Leerportfolio en documentatiewebsite.

Slice 0 levert een voorlopig ontwerp op.

Slices 1 tot en met 3 leveren werkende software op.

Slice 4 rondt de leerstofdekking en presentatie af.

Documentatie en tests worden tijdens alle slices bijgehouden.

## 2.4 Een ontwerp hoeft niet meteen perfect te zijn

We onderscheiden drie statussen:

**Voorgesteld**

Het ontwerp is gemaakt, maar nog niet geïmplementeerd.

**Geïmplementeerd**

De code is gebouwd volgens het ontwerp, eventueel met gedocumenteerde wijzigingen.

**Geverifieerd**

De relevante werking is gecontroleerd met daadwerkelijk uitgevoerde tests.

Een diagram mag niet als geverifieerd worden gepresenteerd wanneer de bijbehorende implementatie nog ontbreekt.

---

# 3. Technische uitgangspunten

We gebruiken:

- Kotlin.
- Ktor.
- JetBrains Amper.
- `module.yaml`.
- kotlinx.serialization.
- H2.
- Exposed.
- Kotlin Test.
- Ktor `testApplication`.
- Git.
- Markdown.
- PlantUML.

We respecteren de bestaande projectstructuur.

Er wordt niet onnodig overgeschakeld naar Gradle.

Bibliotheekversies en commando's worden afgestemd op het daadwerkelijke project.

De code wordt geschreven in het Engels.

De uitleg, reflecties en portfolio-inhoud worden geschreven in het Nederlands.

---

# 4. Functionele scope

## 4.1 Binnen scope

PocketBattleAPI ondersteunt:

- Monsterbeheer via CRUD.
- FIRE-, WATER- en GRASS-monsters.
- Level.
- Maximale HP.
- Defense.
- Speed.
- Twee aanvallen per monster.
- Een normale aanval.
- Een elementaanval.
- Type-effectiviteit.
- Deterministische schadeberekening.
- Automatische aanvalsselectie.
- Automatische beurtwisseling.
- Winnaarbepaling.
- Gevechtslogging.
- In-memory opslag.
- H2-persistentie.
- REST API met JSON.
- Validatie.
- Unit tests.
- Repositorytests.
- Integratietests.
- Technische documentatie.
- UML-diagrammen.

## 4.2 Buiten scope

We bouwen geen:

- Grafische game-interface.
- Gebruikersaccounts.
- Authenticatie.
- Autorisatie.
- Multiplayer.
- Online matchmaking.
- Interactieve gevechtssessies.
- Inventaris.
- Items.
- Experience points.
- Level-ups.
- Evoluties.
- Status-effecten.
- Kritieke aanvallen.
- Willekeurige schade.
- Permanente gevechtsgeschiedenis.

De leerwebsite is een afzonderlijk documentatieproject en geen onderdeel van de battle-backend.

## 4.3 Optionele functionaliteiten

Zoeken op naam, sorteren op level en statistische overzichten mogen worden gebruikt om functionele Kotlin-concepten te demonstreren.

Deze functies hoeven niet automatisch nieuwe REST-endpoints te krijgen.

---

# 5. Basisregels van het gevechtssysteem

## 5.1 Monsters

Een monster heeft:

- `id`.
- `name`.
- `type`.
- `level`.
- `maxHp`.
- `defense`.
- `speed`.
- Twee aanvallen.

De toegestane monsterelementtypes zijn:

- `FIRE`.
- `WATER`.
- `GRASS`.

## 5.2 Domeinmodellering

We beginnen met één algemeen `Monster`-model.

We maken niet automatisch afzonderlijke `FireMonster`, `WaterMonster` en `GrassMonster`-subklassen.

De reden hiervoor is dat de drie elementtypes in eerste instantie dezelfde eigenschappen en dezelfde gedragingen hebben.

Hun verschillen worden bepaald door hun gegevens en de type-effectiviteit.

Wanneer verschillende vormen van gedrag later daadwerkelijk nodig blijken, kunnen we opnieuw overerving of polymorfisme overwegen.

Overerving en abstracte klassen blijven onderdeel van de leerstof, eventueel via een afzonderlijk Kotlin-leerexperiment.

## 5.3 Aanvallen

Ieder monster heeft precies twee aanvallen:

1. Een normale aanval.
2. Een elementaanval.

Een aanval heeft:

- Een naam.
- Een positieve basiskracht.
- Een optioneel elementtype.

Een normale aanval heeft geen elementtype en gebruikt altijd een neutrale typevermenigvuldiger.

Een elementaanval heeft hetzelfde elementtype als het monster.

## 5.4 Type-effectiviteit

| Aanvalstype | Tegen FIRE | Tegen WATER | Tegen GRASS |
|---|---:|---:|---:|
| FIRE | 1.0 | 0.75 | 1.5 |
| WATER | 1.5 | 1.0 | 0.75 |
| GRASS | 0.75 | 1.5 | 1.0 |
| Normaal | 1.0 | 1.0 | 1.0 |

Een sterke aanval veroorzaakt 1,5 keer de normale schade.

Een zwakke aanval veroorzaakt 0,75 keer de normale schade.

Een neutrale aanval gebruikt factor 1,0.

## 5.5 Schadeformule

De voorgestelde formule is:

```text
Damage = max(
    1,
    floor(
        (BasePower * (Level + 5) / (Defense + 10))
        * TypeMultiplier
    )
)
```

De schade is minimaal 1 HP.

De schadeberekening is deterministisch.

We gebruiken geen willekeurige schadefactor.

De `DamageCalculator` verandert zelf geen HP.

## 5.6 Aanvalsstrategie

De simulator selecteert automatisch de aanvallen.

Per monster geldt:

1. Eerste eigen aanval: normaal.
2. Tweede eigen aanval: elementair.
3. Derde eigen aanval: normaal.
4. Vierde eigen aanval: elementair.

Dit patroon herhaalt zich.

De aanvalsteller wordt per monster bijgehouden.

## 5.7 Startvolgorde

Het monster met de hoogste Speed begint.

Bij gelijke Speed begint het eerste monster uit de aanvraag.

Daarna vallen de monsters afwisselend aan.

## 5.8 Gevechtseinde

Een gevecht eindigt direct wanneer één monster 0 HP bereikt.

Een verslagen monster kan niet opnieuw aanvallen.

Iedere uitgevoerde aanval wordt geregistreerd.

## 5.9 Tijdelijke gevechtsstatus

Een gevecht mag opgeslagen monsters niet veranderen.

Daarom gebruiken we een apart battle-state-model, bijvoorbeeld `BattleMonster`.

Dit bevat:

- Een verwijzing naar of een onafhankelijke representatie van de relevante monstergegevens.
- Tijdelijke HP.
- Een eigen aanvalsteller.

Het ontwerp mag geen gedeelde veranderlijke toestand bevatten die de opgeslagen monsters onbedoeld wijzigt.

Een monster begint iedere nieuwe simulatie met volledige HP.

## 5.10 Battle API

Een gevecht wordt gestart met:

`POST /battles`

De request bevat twee verschillende monster-ID's.

De simulator voert het volledige gevecht uit.

Er zijn geen aparte aanvalsendpoints.

---

# 6. UML- en PlantUML-strategie

## 6.1 Waarom PlantUML?

PlantUML wordt gebruikt om softwareontwerpen in tekstvorm vast te leggen.

Voordelen:

- Diagrammen zijn versiebeheerbaar.
- Wijzigingen zijn zichtbaar in Git.
- UML kan naast de code worden bijgewerkt.
- Diagrammen kunnen opnieuw worden gegenereerd.
- Ontwerpkeuzes zijn eenvoudiger te bespreken.

PlantUML is geen vervanging voor tests.

Diagrammen beschrijven het ontwerp.

Tests controleren de implementatie.

## 6.2 Verplichte diagrammen

We maken de volgende diagrammen:

| Diagram | Doel | Wanneer? |
|---|---|---|
| Use Case Diagram | Gebruikersfunctionaliteit vastleggen | Slice 0 |
| Class Diagram | Domeinmodel en verantwoordelijkheden vastleggen | Slice 0, bijwerken per slice |
| Activity Diagram | Gevechtsverloop modelleren | Slice 0 |
| Battle Sequence Diagram | Samenwerking tijdens een gevecht modelleren | Slice 0 |
| Component Diagram | Technische architectuur modelleren | Vóór Slice 2 |
| CRUD Sequence Diagram | Requestverwerking modelleren | Vóór Slice 2 |
| Database-ERD | Tabellen en relaties ontwerpen | Vóór Slice 3 |
| Persistence Sequence Diagram | Opslagproces en repositories modelleren | Vóór Slice 3 |
| Deployment Diagram | Uitvoering en infrastructuur beschrijven | Bij afronding |

De diagrammen zijn bedoeld om ontwerpkeuzes te ondersteunen.

We maken geen extra diagrammen zonder duidelijk doel.

## 6.3 Diagrammen worden niet alleen achteraf gegenereerd

Ieder belangrijk diagram wordt eerst gebruikt om over het ontwerp na te denken.

Na implementatie wordt gecontroleerd:

- Kloppen de klassen nog?
- Kloppen de relaties?
- Kloppen de afhankelijkheden?
- Kloppen de methoden?
- Komt de volgorde van interacties overeen met de code?
- Zijn verantwoordelijkheden verschoven?

Wanneer het ontwerp is veranderd, wordt het diagram aangepast.

## 6.4 PlantUML-bestandsstructuur

```text
docs/
└── diagrams/
    ├── 01-use-cases.puml
    ├── 02-domain-class-diagram.puml
    ├── 03-battle-activity.puml
    ├── 04-battle-sequence.puml
    ├── 05-component-diagram.puml
    ├── 06-monster-crud-sequence.puml
    ├── 07-database-erd.puml
    ├── 08-persistence-sequence.puml
    └── 09-deployment.puml
```

## 6.5 Regels voor PlantUML

De AI levert:

1. Volledige `.puml`-bestanden.
2. Een duidelijk relatief bestandspad.
3. Geldige PlantUML-syntax.
4. Een begrijpelijke toelichting.
5. De belangrijkste ontwerpkeuzes.
6. Eventuele openstaande ontwerpvragen.

Diagrammen worden waar mogelijk gerenderd en visueel gecontroleerd.

Een diagram wordt niet als technisch geverifieerd aangemerkt zonder daadwerkelijk uitgevoerde controle.

## 6.6 UML moet betekenisvol blijven

Diagrammen moeten aansluiten op hun doel.

### Use Case Diagram

Beschrijft functionaliteit vanuit de gebruiker.

Geen interne Kotlin-functies als use cases.

### Class Diagram

Beschrijft klassen, eigenschappen, methoden en relevante relaties.

Niet iedere getter of interne hulpvariabele hoeft zichtbaar te zijn.

### Activity Diagram

Beschrijft processtappen, beslissingen en herhalingen.

Vooral geschikt voor het gevechtsverloop.

### Sequence Diagram

Beschrijft de samenwerking tussen objecten of componenten in tijdsvolgorde.

### Component Diagram

Beschrijft grotere softwareonderdelen en hun afhankelijkheden.

### ERD

Beschrijft database-entiteiten, sleutels en relaties.

Een ERD is geen vervanging voor het Kotlin Class Diagram.

---

# 7. Slice 0 — Requirements en UML-ontwerp

## 7.1 Doel

Voordat we beginnen met de implementatie, ontwerpen we de belangrijkste onderdelen van PocketBattleAPI.

We leggen vast:

- Wat de applicatie moet doen.
- Welke regels gelden.
- Welke klassen waarschijnlijk nodig zijn.
- Hoe gevechten verlopen.
- Hoe objecten samenwerken.
- Welke acceptatiecriteria bij de eerste mijlpaal horen.

Slice 0 mag niet uitgroeien tot een maandenlange ontwerpfase.

Het doel is voldoende inzicht om verantwoord te beginnen met programmeren.

## 7.2 Stap 0.1 — Bestaande projectomgeving controleren

Controleer:

- De bestaande directorystructuur.
- `module.yaml`.
- Kotlin-versie.
- Ktor-versie.
- Dependencies.
- Bestaande code.
- Testconfiguratie.
- Startcommando's.
- Testcommando's.

We passen bestaande werkende bestanden alleen aan wanneer dat nodig is.

## 7.3 Stap 0.2 — Requirements vastleggen

Maak:

`docs/project/specification.md`

Dit document bevat:

- Projectdoel.
- Scope.
- Functionele requirements.
- Niet-functionele requirements.
- Business rules.
- Acceptatiecriteria.
- Belangrijkste beperkingen.

Requirements krijgen unieke ID's, bijvoorbeeld:

- FR-01.
- FR-02.
- NFR-01.
- BR-01.

Een requirement moet duidelijk en controleerbaar zijn.

Bijvoorbeeld:

**FR — Battle simuleren**

De gebruiker kan twee verschillende bestaande monster-ID's opgeven, waarna de applicatie een volledig gevecht simuleert en een resultaat teruggeeft.

**Acceptatiecriteria:**

- Beide monsters moeten bestaan.
- De monster-ID's moeten verschillend zijn.
- De simulator bepaalt automatisch de startvolgorde.
- Iedere aanval wordt geregistreerd.
- Het gevecht eindigt zodra een monster is verslagen.
- De opgeslagen monsters blijven ongewijzigd.

## 7.4 Stap 0.3 — Use Case Diagram

Maak:

`docs/diagrams/01-use-cases.puml`

Beschrijf de functionaliteit vanuit de API-gebruiker.

Minimaal:

- Monster aanmaken.
- Monsters bekijken.
- Monster wijzigen.
- Monster verwijderen.
- Monsters filteren.
- Gevecht simuleren.

De API-gebruiker is een externe actor.

## 7.5 Stap 0.4 — Class Diagram

Maak:

`docs/diagrams/02-domain-class-diagram.puml`

Het eerste model bevat naar verwachting:

- Monster.
- Attack.
- ElementType.
- BattleMonster of vergelijkbare battle-state.
- DamageCalculator.
- BattleService.
- BattleResult.
- BattleTurn.

Controleer:

- Welke properties ieder model nodig heeft.
- Welke functies bij welke klasse horen.
- Welke objecten samenwerken.
- Waar mutable state is toegestaan.
- Welke gegevens immutable kunnen blijven.
- Of er onnodige afhankelijkheden ontstaan.

De definitieve vorm van het model volgt uit de ontwerpafweging.

We gebruiken geen overerving uitsluitend om het Class Diagram ingewikkelder te maken.

## 7.6 Stap 0.5 — Activity Diagram

Maak:

`docs/diagrams/03-battle-activity.puml`

Modelleer het volledige gevechtsproces:

1. Twee monsters ontvangen.
2. Invoer controleren.
3. Tijdelijke battle-state maken.
4. Startvolgorde bepalen.
5. Aanvaller selecteren.
6. Aanval selecteren.
7. Schade berekenen.
8. HP verminderen.
9. Gebeurtenis registreren.
10. Controleren of een monster is verslagen.
11. Zo niet, aanvaller wisselen.
12. Herhalen.
13. Winnaar bepalen.
14. BattleResult retourneren.

Gebruik beslissingsknopen en een lus waar nodig.

## 7.7 Stap 0.6 — Battle Sequence Diagram

Maak:

`docs/diagrams/04-battle-sequence.puml`

Modelleer de samenwerking tussen:

- Aanroeper.
- BattleService.
- Battle-state-objecten.
- DamageCalculator.
- BattleResult.

Het diagram moet duidelijk maken:

- Wie verantwoordelijk is voor aanvalsselectie.
- Wie verantwoordelijk is voor schadeberekening.
- Wie HP wijzigt.
- Wie de gevechtslog opbouwt.
- Wanneer het gevecht stopt.

Voor Slice 0 hoeft nog geen HTTP-route aanwezig te zijn.

Daarom mag de aanroeper een test of andere applicatiecomponent zijn.

## 7.8 Stap 0.7 — Ontwerpcontrole

Controleer:

- Zijn verantwoordelijkheden logisch verdeeld?
- Is de schadeberekening onafhankelijk?
- Worden opgeslagen monsters beschermd?
- Is een volledig gevecht mogelijk zonder HTTP?
- Zijn de belangrijkste business rules modelleerbaar?
- Kunnen we het ontwerp eenvoudig testen?

## 7.9 Stap 0.8 — Ontwerpbeslissingen registreren

Maak:

`docs/architecture/decisions.md`

Leg belangrijke beslissingen vast.

Gebruik per beslissing:

- ID.
- Onderwerp.
- Context.
- Overwogen alternatieven.
- Gekozen oplossing.
- Reden.
- Consequenties.
- Status.

Bijvoorbeeld:

**ADR-001 — Eén Monster-model in plaats van drie subklassen**

Context:

FIRE-, WATER- en GRASS-monsters hebben dezelfde basisgegevens en grotendeels hetzelfde gedrag.

Keuze:

Eén Monster-model met een ElementType-property.

Reden:

Minder duplicatie en een eenvoudiger domeinmodel.

Consequentie:

Overerving wordt niet geforceerd in het productiemodel.

Status:

Voorgesteld totdat de implementatie is beoordeeld.

## 7.10 Resultaat van Slice 0

Slice 0 levert:

- Een compacte projectspecificatie.
- Controleerbare requirements.
- Een Use Case Diagram.
- Een voorlopig Class Diagram.
- Een Activity Diagram.
- Een Battle Sequence Diagram.
- Een overzicht van ontwerpbeslissingen.
- Acceptatiecriteria voor de battle engine.

**Definition of Done voor Slice 0:**

- [ ] De scope is duidelijk.
- [ ] De belangrijkste requirements zijn vastgelegd.
- [ ] De gevechtsregels zijn beschreven.
- [ ] De vier basisdiagrammen zijn gemaakt.
- [ ] De PlantUML-syntax is gecontroleerd.
- [ ] Het ontwerp is begrijpelijk toegelicht.
- [ ] Belangrijke ontwerpkeuzes zijn vastgelegd.
- [ ] Openstaande vragen zijn zichtbaar.
- [ ] Slice 1 kan beginnen zonder grote onduidelijkheden.

---

# 8. Slice 1 — Werkende battle engine

## 8.1 Doel

We bouwen een complete battle engine die zonder Ktor of database kan worden uitgevoerd en getest.

De implementatie wordt gebaseerd op het voorlopige ontwerp uit Slice 0.

## 8.2 Stap 1.1 — Domeinmodellen implementeren

Implementeer:

- ElementType.
- Attack.
- Monster.

Voeg validatie toe voor relevante invarianten.

Maak onmiddellijk bijbehorende tests.

## 8.3 Stap 1.2 — DamageCalculator implementeren

Implementeer:

- Type-effectiviteit.
- De schadeformule.
- Minimale schade.
- Correcte afronding.
- Deterministisch gedrag.

Maak tests voor alle relevante scenario's.

## 8.4 Stap 1.3 — Battle-state implementeren

Maak een model voor tijdelijke gevechtsstatus.

Zorg dat:

- Beide monsters met volledige HP starten.
- HP niet negatief wordt.
- Aanvalstellers onafhankelijk zijn.
- Opgeslagen monsters ongewijzigd blijven.

## 8.5 Stap 1.4 — BattleService implementeren

Implementeer:

- Gevechtsstart.
- Startvolgorde.
- Aanvalsselectie.
- Schade toepassen.
- Beurtwisseling.
- Gevechtslog.
- Gevechtseinde.
- Winnaarbepaling.

## 8.6 Stap 1.5 — Unit tests

Test minimaal:

- Geldige monsters.
- Ongeldige monsters.
- Geldige aanvallen.
- Ongeldige aanvallen.
- Sterke elementaanvallen.
- Zwakke elementaanvallen.
- Neutrale aanvallen.
- Normale aanvallen.
- Minimale schade.
- Correcte afronding.
- Speed-volgorde.
- Gelijke Speed.
- Afwisselende aanvallen.
- Gevechtseinde.
- Correcte winnaar.
- Correcte gevechtslog.
- Onafhankelijke gevechtsstatus.
- Reproduceerbare gevechten.

## 8.7 Stap 1.6 — UML vergelijken met code

Controleer het bestaande Class Diagram, Activity Diagram en Battle Sequence Diagram.

Pas diagrammen aan wanneer de uiteindelijke implementatie afwijkt.

Beschrijf belangrijke verschillen tussen het oorspronkelijke ontwerp en de code.

## 8.8 Stap 1.7 — Bewijsdossier

Maak:

`docs/evidence/battle-engine.md`

Bevat:

- Acceptatiecriteria.
- Relevante klassen.
- Ontwerpkeuzes.
- Tests.
- Daadwerkelijke resultaten.
- Kotlin- en OO-concepten.
- Begripscontrole.
- AI-verantwoording.
- Git-commits.
- Reflectie.

## 8.9 Mijlpaal 1

Mijlpaal 1 is bereikt wanneer:

- [ ] De battle engine werkt zonder HTTP.
- [ ] De gevechtsregels correct zijn geïmplementeerd.
- [ ] De relevante tests daadwerkelijk slagen.
- [ ] Tijdelijke gevechtsstatus wordt gebruikt.
- [ ] Opgeslagen monstergegevens niet worden gemuteerd.
- [ ] De UML overeenkomt met de implementatie.
- [ ] Het bewijsdossier beschikbaar is.

---

# 9. Slice 2 — Monsterbeheer en REST API

## 9.1 Doel

We stellen het monsterbeheer en de battle engine beschikbaar via Ktor.

We gebruiken voorlopig in-memory opslag.

## 9.2 Stap 2.1 — Architectuur ontwerpen

Maak:

`docs/diagrams/05-component-diagram.puml`

Het diagram toont:

- Ktor.
- MonsterRoutes.
- BattleRoutes.
- MonsterService.
- BattleService.
- MonsterRepository.
- InMemoryMonsterRepository.
- Domeinmodellen.
- DTO's.

Leg de afhankelijkheden vast.

Routes mogen niet rechtstreeks ingewikkelde businesslogica bevatten.

## 9.3 Stap 2.2 — CRUD Sequence Diagram

Maak:

`docs/diagrams/06-monster-crud-sequence.puml`

Modelleer ten minste één representatieve CRUD-operatie.

Bijvoorbeeld:

1. Client verstuurt POST /monsters.
2. MonsterRoutes ontvangt de request.
3. JSON wordt verwerkt.
4. MonsterService valideert gegevens.
5. MonsterRepository slaat het monster op.
6. De service retourneert het resultaat.
7. De route retourneert een HTTP-response.

Alternatieve scenario's, zoals ongeldige invoer, worden waar relevant weergegeven.

## 9.4 Stap 2.3 — Repositoryinterface

Implementeer:

`MonsterRepository`

Definieer de noodzakelijke CRUD-operaties.

## 9.5 Stap 2.4 — In-memory repository

Implementeer:

`InMemoryMonsterRepository`

Voeg tests toe voor:

- Opslaan.
- Ophalen.
- Wijzigen.
- Verwijderen.
- Filteren.

## 9.6 Stap 2.5 — MonsterService

Implementeer:

`MonsterService`

Gebruik constructor injection.

De service werkt tegen de repositoryinterface.

## 9.7 Stap 2.6 — Ktor-configuratie

Configureer:

- Routing.
- JSON.
- ContentNegotiation.
- Dependency wiring.
- Centrale foutafhandeling.

Behoud de bestaande Amper-configuratie waar mogelijk.

## 9.8 Stap 2.7 — DTO's

Maak aparte request- en response-DTO's.

Gebruik kotlinx.serialization.

## 9.9 Stap 2.8 — MonsterRoutes

Implementeer:

| Methode | Endpoint | Doel |
|---|---|---|
| GET | `/monsters` | Alle monsters |
| GET | `/monsters/{id}` | Monster ophalen |
| POST | `/monsters` | Monster aanmaken |
| PUT | `/monsters/{id}` | Monster wijzigen |
| DELETE | `/monsters/{id}` | Monster verwijderen |
| GET | `/monsters?type=FIRE` | Filteren |

## 9.10 Stap 2.9 — BattleRoutes

Implementeer:

`POST /battles`

De route gebruikt de bestaande BattleService.

De gevechtslogica wordt niet opnieuw geïmplementeerd in de HTTP-laag.

## 9.11 Stap 2.10 — HTTP-foutafhandeling

Gebruik passende statuscodes:

- 200.
- 201.
- 204.
- 400.
- 404.
- 409.
- 500.

Maak consistente foutresponses.

## 9.12 Stap 2.11 — Integratietests

Test met Ktor `testApplication`:

- Monster CRUD.
- JSON.
- Filtering.
- Ongeldige requests.
- Niet-bestaande monsters.
- Battle-endpoint.
- Foutafhandeling.

## 9.13 Stap 2.12 — UML bijwerken

Controleer:

- Component Diagram.
- CRUD Sequence Diagram.
- Class Diagram.
- Battle Sequence Diagram waar relevant.

Werk afwijkingen bij.

## 9.14 Stap 2.13 — Bewijsdossier

Maak:

`docs/evidence/rest-api.md`

## 9.15 Mijlpaal 2

Mijlpaal 2 is bereikt wanneer:

- [ ] Monster CRUD werkt.
- [ ] Het battle-endpoint werkt.
- [ ] De API JSON gebruikt.
- [ ] Validatie en fouten correct worden verwerkt.
- [ ] De relevante integratietests daadwerkelijk slagen.
- [ ] De UML overeenkomt met de implementatie.
- [ ] Het bewijsdossier beschikbaar is.

---

# 10. Slice 3 — H2-persistentie

## 10.1 Doel

We vervangen in-memory opslag door persistente H2-opslag zonder de bestaande services en routes fundamenteel te veranderen.

## 10.2 Stap 3.1 — Databaseontwerp

Maak:

`docs/diagrams/07-database-erd.puml`

Beschrijf:

- Tabellen.
- Primary keys.
- Foreign keys.
- Relaties.
- Constraints.
- Opslag van monsters.
- Opslag van aanvallen.

Controleer hoe de databasegegevens naar Kotlin-objecten worden gemapt.

## 10.3 Stap 3.2 — Persistence Sequence Diagram

Maak:

`docs/diagrams/08-persistence-sequence.puml`

Modelleer:

1. Een aanvraag om een monster op te slaan.
2. MonsterService.
3. MonsterRepository.
4. H2MonsterRepository.
5. Exposed.
6. H2-database.
7. Terugkoppeling van het resultaat.

Dit diagram maakt duidelijk hoe de service onafhankelijk blijft van de concrete databaseimplementatie.

## 10.4 Stap 3.3 — Databaseconfiguratie

Configureer:

- H2.
- Exposed.
- DatabaseFactory.
- File-based opslag.
- Transacties.

## 10.5 Stap 3.4 — Tabellen en mapping

Maak:

- Exposed-tabellen.
- Databaseconstraints.
- Mappingfuncties.
- Transactieafhandeling.

## 10.6 Stap 3.5 — H2MonsterRepository

Implementeer:

`H2MonsterRepository`

Deze implementatie volgt hetzelfde contract als de in-memory repository.

## 10.7 Stap 3.6 — Repositoryimplementaties verwisselen

Zorg dat services niet afhankelijk zijn van een concrete repositoryklasse.

De keuze voor in-memory of H2 gebeurt bij het samenstellen van de applicatie.

## 10.8 Stap 3.7 — Repositorycontracttests

Controleer dat beide repositories contractueel hetzelfde gedrag hebben.

## 10.9 Stap 3.8 — Persistentietests

Test:

- Opslaan.
- Ophalen.
- Wijzigen.
- Verwijderen.
- Filteren.
- Opslag van aanvallen.
- Verschillende monstertypes.
- Gegevens opnieuw openen.
- Onafhankelijke gevechtsstatus.

## 10.10 Stap 3.9 — UML bijwerken

Controleer:

- ERD.
- Persistence Sequence Diagram.
- Component Diagram.
- Class Diagram.

## 10.11 Stap 3.10 — Bewijsdossier

Maak:

`docs/evidence/persistence.md`

## 10.12 Mijlpaal 3

Mijlpaal 3 is bereikt wanneer:

- [ ] H2-persistentie werkt.
- [ ] De repositoryimplementaties verwisselbaar zijn.
- [ ] Databasegegevens opnieuw kunnen worden gelezen.
- [ ] Contracttests daadwerkelijk slagen.
- [ ] De bestaande API blijft werken.
- [ ] UML en databaseontwerp overeenkomen met de implementatie.
- [ ] Het bewijsdossier beschikbaar is.

---

# 11. Slice 4 — Leerstof en portfolio

## 11.1 Doel

We gebruiken de gebouwde applicatie om programmeerkennis, ontwerpvaardigheid en technische kwaliteit aantoonbaar te maken.

De leerinhoud wordt tijdens het ontwikkelen verzameld.

Slice 4 is bedoeld om ontbrekende onderdelen gericht aan te vullen en het portfolio af te ronden.

## 11.2 Stap 4.1 — Leerstofmatrix invullen

Controleer alle Kotlin-, OO-, FP-, backend- en databaseconcepten.

Per concept registreren we:

- Theorie behandeld?
- In productiecode toegepast?
- In leerexperiment toegepast?
- Tests of ander bewijs aanwezig?
- Begrip gecontroleerd?
- Verdere oefening nodig?

## 11.3 Stap 4.2 — Officiële leerdoelen vergelijken

Vergelijk de leerstofmatrix met:

- De beoordelingsrubric.
- De officiële periode-1-leerdoelen.
- Verplichte Hyperskill-onderdelen.
- Eventuele andere verplichte individuele opdrachten.

PocketBattleAPI is aanvullend bewijs en vervangt niet automatisch verplichte studieonderdelen.

## 11.4 Stap 4.3 — Ontbrekende leerstof oefenen

Maak kleine Kotlin-leerexperimenten voor concepten die niet natuurlijk in de applicatie voorkomen.

Voorbeelden:

- Abstracte klassen.
- Overerving.
- Scope functions.
- Extension properties.
- Delegated properties.
- Variance.
- Sealed classes.
- Inline functions.

Deze oefeningen worden duidelijk gescheiden van de productiecode.

## 11.5 Stap 4.4 — UML controleren

Controleer of alle diagrammen nog overeenkomen met de implementatie.

Werk waar nodig bij:

- Use Case Diagram.
- Class Diagram.
- Activity Diagram.
- Sequence Diagrams.
- Component Diagram.
- ERD.
- Deployment Diagram.

Maak:

`docs/diagrams/09-deployment.puml`

Het Deployment Diagram beschrijft de werkelijke uitvoeromgeving.

## 11.6 Stap 4.5 — Technische documentatie afronden

Controleer:

- Projectspecificatie.
- Architectuurdocumentatie.
- UML.
- API-documentatie.
- Installatie-instructies.
- Testverslag.
- Bewijsdossiers.
- Leerdoelen.
- AI-verantwoording.

## 11.7 Stap 4.6 — Begripscontroles

Gebruik:

- Multiplechoicevragen.
- Code-uitleg.
- Kleine codewijzigingen.
- Fouten herkennen.
- Testcases bedenken.
- Ontwerpkeuzes verdedigen.

## 11.8 Stap 4.7 — Documentatiewebsite

Bouw een lichte documentatiewebsite op basis van de bestaande Markdown-bestanden.

De website mag geen afzonderlijke tweede bron van waarheid worden.

## 11.9 Resultaat van Slice 4

Een leerportfolio waarin zichtbaar is:

- Wat is gebouwd.
- Hoe het vooraf is ontworpen.
- Hoe het ontwerp is aangepast.
- Hoe de werking is getest.
- Welke leerstof is toegepast.
- Wat de student daadwerkelijk heeft gecontroleerd.
- Hoe AI is gebruikt.

---

# 12. De standaardworkflow per functionaliteit

Vanaf Slice 1 volgen we per functionaliteit dezelfde werkwijze.

## Stap A — Doel en acceptatiecriteria

Beschrijf kort:

- Wat bouwen we?
- Waarom bouwen we het?
- Wat moet werken?
- Wat valt buiten deze stap?
- Hoe gaan we het controleren?

## Stap B — PlantUML controleren

Bekijk de relevante diagrammen.

Wanneer het ontwerp ontbreekt, maak het eerst.

Wanneer het ontwerp niet meer klopt, bespreek en documenteer de noodzakelijke wijziging.

## Stap C — Relevante theorie

Leg de Kotlin- en softwareconcepten uit die voor de implementatie nodig zijn.

Vermijd uitgebreide theorie over concepten die op dat moment niet relevant zijn.

## Stap D — Complete code

AI levert volledige Kotlin-bestanden.

Regels:

1. Volledige, kopieerbare bestanden.
2. Eerste regel bevat het relatieve bestandspad als Kotlin-commentaar.
3. Geen onvolledige snippets ter vervanging van benodigde bestanden.
4. Zinvolle comments.
5. Begrijpelijke code.
6. Geen onnodige abstracties.
7. Behoud van bestaande functionaliteit.
8. Vermelding van nieuwe en gewijzigde bestanden.

## Stap E — Tests

Lever passende tests.

Vermeld:

- Wat wordt getest.
- Hoe tests worden uitgevoerd.
- Welke uitvoer verwacht wordt.
- Welke resultaten daadwerkelijk bevestigd zijn.

AI mag geen testresultaten verzinnen.

## Stap F — Implementatie begrijpen

Leg uit:

- Welke klassen samenwerken.
- Waar verantwoordelijkheden liggen.
- Welke OO- en FP-concepten zijn toegepast.
- Waarom deze oplossing is gekozen.
- Welke alternatieven bestaan.

## Stap G — Begripscontrole

Stel een aantal gerichte vragen.

Combineer waar relevant:

- Multiplechoice.
- Code voorspellen.
- Code uitleggen.
- Een fout herkennen.
- Een kleine wijziging uitvoeren.
- Een extra testcase bedenken.

## Stap H — UML bijwerken

Controleer de diagrammen tegen de uiteindelijke code.

Wanneer er verschillen zijn:

- Beschrijf ze.
- Leg de reden uit.
- Werk het diagram bij.

## Stap I — Git

Stel een logische commit message voor.

Een commit wordt alleen als uitgevoerd geregistreerd wanneer dit is bevestigd.

## Stap J — Bewijsdossier

Leg vast:

- Functionaliteit.
- Acceptatiecriteria.
- Implementatie.
- UML.
- Tests.
- Leerstof.
- Begripscontrole.
- Git.
- AI-verantwoording.
- Reflectie.

## Stap K — Volgende functionaliteit

Ga verder wanneer:

- De relevante code beschikbaar is.
- Kritieke fouten zijn opgelost.
- De teststatus bekend is.
- De belangrijkste ontwerpwijzigingen zijn vastgelegd.

Niet iedere quiz hoeft perfect gemaakt te zijn voordat de technische ontwikkeling doorgaat.

---

# 13. Bewijsvoering en HBO-verantwoording

## 13.1 Waarom bewijsdossiers?

Een uitgebreide documentatiecollectie bewijst niet automatisch dat een student de applicatie begrijpt.

Daarom gebruiken we bewijsdossiers.

Per belangrijke functionaliteit leggen we vast:

1. Wat is gebouwd.
2. Welke requirements worden vervuld.
3. Hoe het ontwerp eruitzag.
4. Hoe de implementatie uiteindelijk werkt.
5. Welke tests zijn uitgevoerd.
6. Welke leerstof is toegepast.
7. Welke begripscontroles zijn uitgevoerd.
8. Hoe AI heeft geholpen.

## 13.2 Bewijs is concreet

Voorbeelden van sterk bewijs:

- Een Git-commit die daadwerkelijk bestaat.
- Een geslaagde test met bevestigde uitvoer.
- Een PlantUML-diagram dat overeenkomt met de code.
- Een gedocumenteerde ontwerpwijziging.
- Een door de student uitgevoerde kleine codewijziging.
- Een correct uitgelegde technische keuze.

Voorbeelden van onvoldoende bewijs:

- Alleen een AI-gegenereerde uitleg.
- Een voorgestelde maar niet uitgevoerde test.
- Een niet-gemaakte commit.
- Een reflectie die niet gebaseerd is op echte ervaringen.
- Een UML-diagram dat niet overeenkomt met de code.

## 13.3 Reflectie

Reflectie gaat over daadwerkelijke ervaringen.

Wanneer de student nog geen persoonlijke reflectie heeft gegeven, levert AI reflectievragen in plaats van verzonnen ervaringen.

---

# 14. Leerstofdekkingsmatrix

De oorspronkelijke 31 lessen blijven bestaan als onderwerpenlijst.

Ze bepalen niet langer de ontwikkelvolgorde.

## 14.1 Kotlin-fundamenten

- [ ] `fun main()`
- [ ] Functies
- [ ] Parameters
- [ ] Returntypes
- [ ] `val` en `var`
- [ ] Basistypes
- [ ] Type inference
- [ ] String templates
- [ ] Packages en imports
- [ ] `if`
- [ ] `when`
- [ ] `for`
- [ ] `while`
- [ ] Ranges
- [ ] Expressions en statements
- [ ] `Unit`
- [ ] `Any`
- [ ] `Nothing`

## 14.2 OOP

- [ ] Klassen
- [ ] Objecten
- [ ] Properties
- [ ] Primary constructors
- [ ] Secondary constructors
- [ ] `init`
- [ ] Default parameters
- [ ] Member functions
- [ ] Encapsulatie
- [ ] Visibility modifiers
- [ ] Getters en setters
- [ ] Invarianten
- [ ] Overerving
- [ ] Abstracte klassen
- [ ] Interfaces
- [ ] Polymorfisme
- [ ] Compositie
- [ ] Data classes
- [ ] Enums
- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `copy()`
- [ ] Destructuring
- [ ] Function overloading

## 14.3 Functioneel programmeren

- [ ] Pure functions
- [ ] Immutability
- [ ] Lambdas
- [ ] Higher-order functions
- [ ] Function types
- [ ] Function references
- [ ] Closures
- [ ] `filter`
- [ ] `map`
- [ ] `find`
- [ ] `firstOrNull`
- [ ] `any`
- [ ] `all`
- [ ] `none`
- [ ] `count`
- [ ] `sortedBy`
- [ ] `groupBy`
- [ ] `associateBy`
- [ ] `fold`
- [ ] `sumOf`

## 14.4 Overige Kotlin-concepten

- [ ] Null safety
- [ ] Nullable types
- [ ] Safe calls
- [ ] Elvis operator
- [ ] Smart casts
- [ ] `let`
- [ ] `run`
- [ ] `with`
- [ ] `apply`
- [ ] `also`
- [ ] Extension functions
- [ ] Extension properties
- [ ] Generics
- [ ] Variance
- [ ] `object`
- [ ] Companion objects
- [ ] Delegated properties
- [ ] `lazy`
- [ ] Sealed classes
- [ ] Sealed interfaces
- [ ] Inline functions
- [ ] Exceptions
- [ ] `try` en `catch`
- [ ] `require()`
- [ ] `check()`

## 14.5 Softwarearchitectuur

- [ ] Separation of concerns
- [ ] Single Responsibility Principle
- [ ] Dependency inversion
- [ ] Dependency injection
- [ ] Repository pattern
- [ ] Service layer
- [ ] Interface-based design
- [ ] Cohesie
- [ ] Koppeling
- [ ] Domeinmodellen
- [ ] DTO's
- [ ] Mapping
- [ ] Ontwerpverantwoording
- [ ] Design decisions
- [ ] UML

## 14.6 REST en Ktor

- [ ] HTTP
- [ ] REST
- [ ] HTTP-methoden
- [ ] Statuscodes
- [ ] Path parameters
- [ ] Query parameters
- [ ] JSON
- [ ] Ktor routing
- [ ] Ktor plugins
- [ ] Serialisatie
- [ ] Deserialisatie
- [ ] Validatie
- [ ] Foutafhandeling
- [ ] CRUD
- [ ] Ktor-integratietests

## 14.7 Databases

- [ ] Relationele databases
- [ ] SQL
- [ ] Tabellen
- [ ] Primary keys
- [ ] Foreign keys
- [ ] Constraints
- [ ] Relaties
- [ ] H2
- [ ] Exposed
- [ ] Databaseverbindingen
- [ ] Transacties
- [ ] Database-mapping
- [ ] Repositorycontracttests
- [ ] Persistentietests

## 14.8 Testing

- [ ] Unit tests
- [ ] Integratietests
- [ ] Arrange, Act, Assert
- [ ] Assertions
- [ ] Happy paths
- [ ] Edge cases
- [ ] Boundary values
- [ ] Testisolatie
- [ ] Deterministische tests
- [ ] Repositorytests
- [ ] Testverslagen

## 14.9 UML

- [ ] Use Case Diagram
- [ ] Class Diagram
- [ ] Activity Diagram
- [ ] Sequence Diagram
- [ ] Component Diagram
- [ ] ERD
- [ ] Deployment Diagram
- [ ] PlantUML-syntax
- [ ] Diagrammen controleren
- [ ] UML actualiseren na implementatie
- [ ] Ontwerpwijzigingen verantwoorden

---

# 15. Officiële leerdoelen

De leerstofdekkingsmatrix is gebaseerd op de projectdoelen.

Deze lijst is niet automatisch gelijk aan de officiële beoordelingscriteria van de opleiding.

Daarom vergelijken we de matrix met:

- De beoordelingsrubric.
- De periode-1-leerdoelen.
- De verplichte Hyperskill-onderdelen.
- Eventuele andere individuele opdrachten.

Per leerdoel leggen we vast:

- Leerdoel.
- Bron.
- Bewijs.
- Status.
- Openstaande actie.

PocketBattleAPI vervangt niet automatisch verplichte studieonderdelen.

---

# 16. AI-gebruik

## 16.1 AI als ontwikkelhulpmiddel

AI mag volledige code schrijven.

AI mag daarnaast helpen met:

- Requirements.
- PlantUML.
- Architectuur.
- Tests.
- Refactoring.
- Documentatie.
- Quizzen.
- Code-uitleg.
- Foutanalyse.

## 16.2 Transparantie

We maken onderscheid tussen:

- AI-gegenereerde code.
- Door de student gecontroleerde code.
- Door tests bevestigde code.
- Zelfstandig gemaakte wijzigingen.
- Begrip dat is gecontroleerd.
- Openstaande kennis.

## 16.3 Geen fictieve bewijsvoering

We verzinnen geen:

- Testresultaten.
- Git-commits.
- Persoonlijke ervaringen.
- Zelfstandige bijdragen.
- Werkuren.
- Ontwerpbeslissingen die niet daadwerkelijk zijn genomen.

---

# 17. Documentatiestructuur

De documentatie heeft vier hoofdonderdelen.

## 17.1 Project Specification

`docs/project/specification.md`

Bevat:

- Scope.
- Requirements.
- Business rules.
- Acceptatiecriteria.
- API-functionaliteit.

## 17.2 Architecture & Decisions

`docs/architecture/architecture.md`

Aanvullend:

`docs/architecture/decisions.md`

Bevat:

- Architectuur.
- Verantwoordelijkheden.
- PlantUML-diagrammen.
- Ontwerpkeuzes.
- Alternatieven.
- Ontwerpwijzigingen.

## 17.3 Learning Coverage Matrix

`docs/learning/coverage.md`

Bevat:

- Officiële leerdoelen.
- Kotlin.
- OOP.
- FP.
- REST.
- Databases.
- UML.
- Bewijsverwijzingen.
- Voortgang.

## 17.4 Evidence & AI Log

`docs/evidence/README.md`

Met:

- `battle-engine.md`.
- `rest-api.md`.
- `persistence.md`.
- Testresultaten.
- Begripscontroles.
- AI-verantwoording.
- Reflectie.

---

# 18. Documentatiewebsite

## 18.1 Doel

De website maakt de leerstof en documentatie toegankelijk.

De website is geen tweede afzonderlijke bron van waarheid.

## 18.2 Inhoud

De website toont:

- Projectdashboard.
- Ontwikkelslices.
- UML-diagrammen.
- Architectuur.
- Kotlin-theorie.
- OO-theorie.
- FP-theorie.
- Backendtheorie.
- Codevoorbeelden.
- API-documentatie.
- Tests.
- AI-verantwoording.
- Reflectie.
- Quizzen.
- Voortgang.

## 18.3 Interactieve quizzen

Quizvragen bevatten:

- Unieke ID.
- Vraagtekst.
- Vier antwoordmogelijkheden.
- Correct antwoord.
- Feedback.
- Onderwerp.
- Leerdoel.
- Moeilijkheidsgraad.

De website ondersteunt:

- Antwoordselectie.
- Directe feedback.
- Score.
- Voortgang.
- Herhaling.

De quizzen mogen client-side werken.

## 18.4 Taakverdeling

### Backend-AI

Verantwoordelijk voor:

- Kotlin.
- Ktor.
- H2.
- Exposed.
- Tests.
- PlantUML.
- Technische documentatie.
- Leerinhoud.
- Bewijsdossiers.
- Quizvragen.

### Website-AI

Verantwoordelijk voor:

- Websitestructuur.
- Vormgeving.
- Navigatie.
- Markdown-weergave.
- UML-weergave.
- Quizcomponenten.
- Scoreberekening.
- Voortgangsregistratie.

## 18.5 Overdracht

Wanneer beide AI-systemen toegang hebben tot dezelfde projectbestanden, gebruiken zij die bestanden.

Wanneer gedeelde toegang ontbreekt, levert de backend-AI de benodigde Markdown-, JSON- of PlantUML-bestanden volledig aan als kopieerbare codeblokken.

De website-AI hoeft geen technische feiten over de backend te verzinnen.

---

# 19. Git-workflow

We maken betekenisvolle commits.

Voorbeelden:

```text
docs: define project requirements
docs: add initial plantuml diagrams
feat: implement monster domain models
test: add monster validation tests
feat: implement damage calculator
test: verify elemental damage
feat: implement battle service
docs: align battle uml with implementation
feat: implement in-memory repository
feat: expose monster CRUD endpoints
test: add ktor integration tests
feat: add h2 persistence
docs: finalize architecture and evidence
```

Een voorgestelde commit wordt pas als uitgevoerd geregistreerd wanneer dit bevestigd is.

---

# 20. Definition of Done

## 20.1 Ontwerp afgerond

- [ ] De requirements zijn duidelijk.
- [ ] Relevante diagrammen zijn gemaakt.
- [ ] De belangrijkste relaties en verantwoordelijkheden zijn bepaald.
- [ ] Ontwerpkeuzes zijn toegelicht.
- [ ] Openstaande vragen zijn vastgelegd.

## 20.2 Functionaliteit afgerond

- [ ] De code is geïmplementeerd.
- [ ] De code compileert.
- [ ] Relevante tests zijn uitgevoerd.
- [ ] Testresultaten zijn bevestigd.
- [ ] Kritieke fouten zijn opgelost.
- [ ] De relevante UML is bijgewerkt.
- [ ] Het bewijsdossier is aangevuld.

## 20.3 Leerbewijs afgerond

- [ ] Het concept is behandeld.
- [ ] De toepassing is gedocumenteerd.
- [ ] Er is passend bewijs.
- [ ] Een begripscontrole is uitgevoerd of als openstaand geregistreerd.
- [ ] AI-gebruik is transparant beschreven.
- [ ] Onbevestigde claims zijn vermeden.

---

# 21. Prioriteiten bij tijdsdruk

## Prioriteit 1 — Verplichte schoolonderdelen

Officiële beoordelingscriteria en deadlines krijgen voorrang boven optionele projectuitbreidingen.

## Prioriteit 2 — Slice 0 en battle engine

Maak eerst een compact ontwerp en vervolgens een werkende battle engine.

## Prioriteit 3 — REST API

Maak monsterbeheer en gevechten bereikbaar via HTTP.

## Prioriteit 4 — Persistentie

Voeg H2 en Exposed toe.

## Prioriteit 5 — Portfolioafronding

Controleer ontbrekende leerstof, UML, tests en AI-verantwoording.

## Prioriteit 6 — Website

Investeer pas uitgebreid in de website wanneer de noodzakelijke technische en onderwijsinhoud op orde is.

De website mag geen blokkade worden voor de backend.

---

# 22. Definitieve uitvoeringsvolgorde

## Slice 0 — Voorbereiden en ontwerpen

1. Bestaande projectstructuur bekijken.
2. Amper-configuratie controleren.
3. Requirements vastleggen.
4. Acceptatiecriteria opstellen.
5. Use Case Diagram maken.
6. Class Diagram maken.
7. Battle Activity Diagram maken.
8. Battle Sequence Diagram maken.
9. Ontwerpkeuzes registreren.
10. Ontwerp controleren.

## Slice 1 — Battle engine

11. ElementType implementeren.
12. Attack implementeren.
13. Monster implementeren.
14. Validatietests schrijven.
15. DamageCalculator implementeren.
16. DamageCalculator testen.
17. Battle-state implementeren.
18. BattleService implementeren.
19. Gevechtslog implementeren.
20. Battletests uitvoeren.
21. UML bijwerken.
22. Mijlpaal 1 controleren.
23. Bewijsdossier vastleggen.

## Slice 2 — REST API

24. Component Diagram maken.
25. CRUD Sequence Diagram maken.
26. MonsterRepository ontwerpen.
27. InMemoryMonsterRepository implementeren.
28. MonsterService implementeren.
29. Repository- en servicetests uitvoeren.
30. Ktor configureren.
31. DTO's implementeren.
32. MonsterRoutes implementeren.
33. BattleRoutes implementeren.
34. Validatie en foutafhandeling implementeren.
35. Ktor-integratietests uitvoeren.
36. UML bijwerken.
37. Mijlpaal 2 controleren.
38. Bewijsdossier vastleggen.

## Slice 3 — Persistentie

39. ERD maken.
40. Persistence Sequence Diagram maken.
41. H2 configureren.
42. Exposed configureren.
43. Tabellen implementeren.
44. Mapping implementeren.
45. H2MonsterRepository implementeren.
46. Repositoryimplementaties verwisselbaar maken.
47. Repositorycontracttests uitvoeren.
48. Persistentietests uitvoeren.
49. UML bijwerken.
50. Mijlpaal 3 controleren.
51. Bewijsdossier vastleggen.

## Slice 4 — Leerportfolio

52. Leerstofdekkingsmatrix invullen.
53. Officiële leerdoelen vergelijken.
54. Ontbrekende leerstof identificeren.
55. Kleine Kotlin-leerexperimenten maken.
56. Begripscontroles uitvoeren.
57. Architectuurdocumentatie actualiseren.
58. UML-diagrammen controleren.
59. Deployment Diagram maken.
60. API-documentatie afronden.
61. Installatie-instructies afronden.
62. Testverslag afronden.
63. AI-verantwoording afronden.
64. Portfolio-inhoud controleren.
65. Documentatiewebsite bouwen.
66. Eindcontrole uitvoeren.

Deze stappen vormen de standaardvolgorde.

Kleine aanpassingen zijn toegestaan wanneer daarvoor een duidelijke technische reden bestaat.

Tests en documentatie worden tijdens iedere slice bijgehouden.

---

# 23. Opdracht aan de uitvoerende AI

Je bent mijn:

- Kotlin-docent.
- Softwarearchitect.
- Backendontwikkelaar.
- PlantUML-ontwerppartner.
- Code-reviewer.
- Testpartner.
- Technisch documentatieschrijver.

Gebruik dit document als het leidende projectplan.

## Jouw belangrijkste regels

1. Werk volgens Slice 0 tot en met Slice 4.
2. Begin met requirements en relevante UML.
3. Maak diagrammen voordat de bijbehorende functionaliteit wordt geïmplementeerd.
4. Houd UML en code tijdens ontwikkeling consistent.
5. Geef duidelijke ontwerpverantwoording.
6. Lever complete, kopieerbare Kotlin-bestanden.
7. Zet boven ieder Kotlin-bestand het relatieve bestandspad als commentaar.
8. Schrijf zinvolle inline comments.
9. Voeg passende tests toe.
10. Verzin nooit testresultaten.
11. Respecteer de bestaande Amper-projectstructuur.
12. Vermijd onnodige abstracties.
13. Gebruik Kotlin-concepten wanneer ze nuttig zijn.
14. Behandel ontbrekende leerstof via kleine oefeningen.
15. Stel logische Git-commits voor.
16. Houd bewijsdossiers bij.
17. Registreer AI-gebruik transparant.
18. Stel gerichte begripsvragen.
19. Houd rekening met tijdsdruk.
20. Lever documentatie en PlantUML als volledige bestanden in kopieerbare codeblokken.

## Werkvolgorde bij ontwerpen

Wanneer we een ontwerpstap uitvoeren, lever je:

1. Het doel van het diagram.
2. De belangrijkste ontwerpbeslissingen.
3. Het volledige `.puml`-bestand.
4. Uitleg van de relaties en verantwoordelijkheden.
5. Eventuele aandachtspunten.
6. Een voorgestelde Git-commit.

## Werkvolgorde bij implementeren

Wanneer we code ontwikkelen, lever je:

1. Doel en acceptatiecriteria.
2. Relevante theorie.
3. Koppeling met UML.
4. Volledige Kotlin-bestanden.
5. Volledige testbestanden.
6. Uitleg van de implementatie.
7. Testinstructies.
8. Begripsvragen.
9. Eventuele UML-aanpassingen.
10. Git-commitvoorstel.
11. Benodigde bewijsdocumentatie.

## Samenwerkingsafspraak

AI mag complete code genereren.

Dit is een bewuste keuze vanwege tijdsdruk.

De leerwaarde wordt bewaakt met:

- Code-uitleg.
- Tests.
- UML.
- Kleine praktijkopdrachten.
- Leerstofmapping.
- Reflectie.
- Transparante AI-verantwoording.

Ga niet telkens opnieuw in discussie over deze gekozen werkwijze.

---

# 24. Eerste opdracht

We beginnen met:

**Slice 0 — Requirements en UML-ontwerp.**

Voer eerst een korte inventarisatie uit van het bestaande Kotlin/Amper-project.

Controleer:

- `module.yaml`.
- Projectstructuur.
- Bestaande code.
- Testconfiguratie.
- Kotlin- en Ktor-versies.

Gebruik de bestaande informatie over PocketBattleAPI als uitgangspunt.

Maak daarna, in deze volgorde:

1. De projectspecificatie met requirements en acceptatiecriteria.
2. Het Use Case Diagram in PlantUML.
3. Het voorlopige Class Diagram in PlantUML.
4. Het Battle Activity Diagram in PlantUML.
5. Het Battle Sequence Diagram in PlantUML.
6. Een compacte lijst met Architecture Decision Records.

Lever ieder document of diagram als een volledig, kopieerbaar bestand met het bijbehorende relatieve bestandspad.

Controleer dat alle diagrammen onderling consistent zijn.

Na afronding van Slice 0 beginnen we direct met Slice 1 en de eerste Kotlin-implementatie.

**Maak de ontwerpfase niet groter dan noodzakelijk. Het doel is een goed doordacht ontwerp dat snel kan worden omgezet in werkende software.**

---

# 25. Gewenst eindresultaat

Aan het einde beschikken we over drie samenhangende resultaten.

## A. Een werkende applicatie

- Kotlin/Ktor REST API.
- Monster CRUD.
- Automatische gevechtssimulatie.
- Schadeberekening.
- In-memory repository.
- H2-persistentie.
- Uitwisselbare opslagimplementaties.
- Geautomatiseerde tests.

## B. Een verantwoord softwareontwerp

- Requirements.
- Business rules.
- Acceptatiecriteria.
- PlantUML-diagrammen.
- Architectuurdocumentatie.
- Ontwerpbeslissingen.
- Gedocumenteerde ontwerpwijzigingen.
- API-documentatie.
- Testverslagen.

## C. Een aantoonbaar leerportfolio

- Kotlin-theorie.
- OO-theorie.
- FP-theorie.
- Ktor- en REST-theorie.
- Databasekennis.
- UML-kennis.
- Praktijkvoorbeelden.
- Kleine leerexperimenten.
- Begripscontroles.
- Quizzen.
- Reflecties.
- Transparante AI-verantwoording.
- Documentatiewebsite.

## Definitief succescriterium

PocketBattleAPI is geslaagd wanneer we kunnen aantonen:

1. **Wat** de applicatie moet doen.
2. **Hoe** de applicatie vooraf is ontworpen.
3. **Waarom** bepaalde ontwerpkeuzes zijn gemaakt.
4. **Hoe** deze keuzes in Kotlin zijn geïmplementeerd.
5. **Hoe** de werking daadwerkelijk is getest.
6. **Welke** leerstof hiermee is behandeld.
7. **Wat** de student zelf heeft gecontroleerd en begrepen.
8. **Welke** bijdrage AI heeft geleverd.

Het belangrijkste uitgangspunt blijft:

**Eerst begrijpelijk ontwerpen, vervolgens werkende software bouwen, daarna de resultaten controleren en transparant verantwoorden.**