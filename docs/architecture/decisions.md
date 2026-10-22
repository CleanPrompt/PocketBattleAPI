
<!-- docs/architecture/decisions.md -->

# PocketBattleAPI — Architecture Decision Records

**Versie:** 1.0  
**Datum:** 22 oktober 2026  
**Status:** Voorgesteld ontwerp  
**Projectfase:** Slice 0

## Doel van dit document

Dit document beschrijft de belangrijkste architectuur- en ontwerpbeslissingen van PocketBattleAPI.

De beslissingen zijn gebaseerd op de projectspecificatie en de vier UML-diagrammen.

Elke beslissing kan tijdens de implementatie worden herzien. Als dat gebeurt, leggen we vast waarom.

---

## ADR-001 — Kotlin en Ktor gebruiken

**Status:** Geaccepteerd als projectkeuze

### Context

PocketBattleAPI wordt ontwikkeld als een REST API.

Het project moet mogelijkheden bieden om Kotlin, HTTP-endpoints, JSON en backendarchitectuur te leren.

### Beslissing

We gebruiken Kotlin als programmeertaal en Ktor als webframework.

### Motivatie

- Kotlin ondersteunt objectgeoriënteerd en functioneel programmeren.
- Ktor is geschikt voor het bouwen van REST API's.
- We kunnen domeinlogica scheiden van HTTP-afhandeling.

### Gevolgen

- We moeten Kotlin- en Ktor-concepten leren toepassen.
- We schrijven afzonderlijke tests voor domeinlogica en HTTP-endpoints.
- De battle engine mag niet afhankelijk zijn van Ktor.

---

## ADR-002 — JetBrains Amper gebruiken

**Status:** Geaccepteerd als projectkeuze

### Context

Het bestaande project gebruikt JetBrains Amper.

De projectconfiguratie staat in `module.yaml`.

### Beslissing

We blijven JetBrains Amper gebruiken en stappen niet zonder noodzaak over naar Gradle.

### Motivatie

- De bestaande projectstructuur blijft behouden.
- We voorkomen onnodige configuratiewijzigingen.
- We kunnen ons richten op Kotlin en backendontwikkeling.

### Gevolgen

- Dependencies worden toegevoegd aan `module.yaml`.
- Build- en testcommando's moeten passen bij Amper.
- We controleren de exacte dependencyversies voordat we nieuwe libraries toevoegen.

---

## ADR-003 — Eén Monster-klasse gebruiken

**Status:** Geaccepteerd als ontwerpkeuze

### Context

PocketBattleAPI bevat FIRE-, WATER- en GRASS-monsters.

Deze monsters hebben dezelfde eigenschappen en volgen dezelfde gevechtsregels.

### Beslissing

We gebruiken één `Monster`-klasse met een `ElementType`-enum.

De enum bevat:

- `FIRE`
- `WATER`
- `GRASS`

### Motivatie

- We voorkomen dubbele code.
- Nieuwe monsters kunnen dezelfde klasse gebruiken.
- Het elementtype is een eigenschap van een monster, geen aparte klasse.

### Gevolgen

- We maken geen `FireMonster`, `WaterMonster` of `GrassMonster`.
- Type-effectiviteit wordt afzonderlijk berekend.
- Overerving wordt eventueel in een aparte leeroefening behandeld.

---

## ADR-004 — Domeinlogica scheiden van de REST API

**Status:** Geaccepteerd als ontwerpkeuze

### Context

Een automatisch gevecht bestaat uit regels voor aanvallen, schade en beurtwisseling.

Deze regels moeten onafhankelijk van HTTP-verzoeken kunnen worden getest.

### Beslissing

We scheiden de battle engine van Ktor-routes, DTO's en databasecode.

### Motivatie

- De battle engine is eenvoudiger te testen.
- HTTP-afhandeling en gevechtsregels hebben verschillende verantwoordelijkheden.
- Wijzigingen aan de API hoeven de gevechtslogica niet te veranderen.

### Gevolgen

- `BattleService` ontvangt domeinobjecten.
- `DamageCalculator` bevat geen HTTP- of databasecode.
- De routes vertalen verzoeken naar aanroepen van services.

---

## ADR-005 — Tijdelijke BattleMonster-objecten gebruiken

**Status:** Geaccepteerd als ontwerpkeuze

### Context

Een monster verliest HP tijdens een gevecht.

Maar een nieuw gevecht moet opnieuw met volledige HP beginnen.

### Beslissing

We gebruiken `BattleMonster` voor tijdelijke gevechtsstatus.

`BattleMonster` bevat:

- Een verwijzing naar `Monster`.
- `currentHp`.
- `attackCount`.

### Motivatie

- Opgeslagen monstergegevens blijven ongewijzigd.
- Ieder gevecht heeft zijn eigen toestand.
- De aanvalsteller hoort bij het individuele monster.

### Gevolgen

- `BattleService` maakt per gevecht twee `BattleMonster`-objecten.
- `currentHp` begint op `maxHp`.
- `attackCount` begint op 0.
- Na het gevecht hoeven we de tijdelijke HP niet op te slaan.

---

## ADR-006 — Deterministische schadeberekening gebruiken

**Status:** Geaccepteerd als ontwerpkeuze

### Context

We willen gevechten voorspelbaar en automatisch testbaar maken.

Willekeurige schade zou het moeilijker maken om exacte testresultaten te controleren.

### Beslissing

We gebruiken deze vaste formule:

Damage = max(1, floor(
(BasePower * (Level + 5) / (Defense + 10))
* TypeMultiplier
))

Hierbij geldt:

- `BasePower`: basiskracht van de aanval.
- `Level`: level van de aanvaller.
- `Defense`: defense van de verdediger.
- `TypeMultiplier`: effectiviteit van het aanvalstype.

De type-effectiviteit is:

| Situatie | Multiplier |
|---|---:|
| Sterk | 1.5 |
| Neutraal | 1.0 |
| Zwak | 0.75 |
| Normale aanval | 1.0 |

De sterke relaties zijn:

- FIRE is sterk tegen GRASS.
- GRASS is sterk tegen WATER.
- WATER is sterk tegen FIRE.

### Motivatie

- De uitkomst is reproduceerbaar.
- We kunnen exacte unit-tests schrijven.
- De berekening blijft begrijpelijk.

### Gevolgen

- `DamageCalculator` bevat geen willekeurige factor.
- De minimale schade is 1.
- De berekening verandert geen monstergegevens.
- We testen sterke, zwakke en neutrale typecombinaties.

---

## ADR-007 — Aanvallen automatisch afwisselen

**Status:** Geaccepteerd als ontwerpkeuze

### Context

Elk monster heeft precies twee aanvallen:

1. Een normale aanval.
2. Een elementaanval.

De gebruiker kiest tijdens een gevecht geen aanvallen.

### Beslissing

`BattleMonster.selectNextAttack()` kiest de aanval op basis van de eigen `attackCount`.

- Even teller: normale aanval.
- Oneven teller: elementaanval.
- Na de keuze wordt de teller met 1 verhoogd.

### Motivatie

- De aanvalsvolgorde is voorspelbaar.
- Ieder monster houdt zijn eigen beurtgeschiedenis bij.
- `BattleService` hoeft de teller niet zelf te wijzigen.

### Gevolgen

- Beide monsters beginnen met hun normale aanval.
- Een monster wisselt daarna tussen normaal en elementair.
- We testen de aanvalsvolgorde afzonderlijk.

---

## ADR-008 — Speed bepaalt de eerste aanvaller

**Status:** Geaccepteerd als ontwerpkeuze

### Context

Bij een automatisch gevecht moet duidelijk zijn welk monster begint.

### Beslissing

Het monster met de hoogste `speed` begint.

Bij gelijke `speed` begint het eerste monster uit de aanvraag.

Na iedere aanval wisselen de rollen, zolang beide monsters leven.

### Motivatie

- De regel is eenvoudig en controleerbaar.
- Een gelijke snelheid levert geen willekeurige uitkomst op.
- Het gevecht blijft deterministisch.

### Gevolgen

- `BattleService` bepaalt de eerste aanvaller.
- We testen verschillende snelheden en gelijke snelheden.
- Er komt geen willekeurige beurtvolgorde.

---

## ADR-009 — Repository-interface gebruiken

**Status:** Voorgenomen voor Slice 2

### Context

We willen eerst de REST API ontwikkelen zonder direct afhankelijk te zijn van een database.

Later voegen we permanente opslag toe.

### Beslissing

We ontwerpen een repository-interface voor monstergegevens.

In Slice 2 gebruiken we een in-memory implementatie.

In Slice 3 voegen we een H2/Exposed-implementatie toe.

### Motivatie

- De opslagimplementatie kan veranderen.
- Services hoeven niet direct afhankelijk te zijn van H2.
- We kunnen repositorygedrag afzonderlijk testen.

### Gevolgen

- Monsterbeheer werkt via een repository-interface.
- De in-memory implementatie is tijdelijk.
- De H2-implementatie moet hetzelfde afgesproken gedrag ondersteunen.

---

## ADR-010 — H2 en Exposed gebruiken

**Status:** Voorgenomen voor Slice 3

### Context

Monsters moeten uiteindelijk permanent opgeslagen kunnen worden.

Het project heeft geen externe databaseserver nodig.

### Beslissing

We gebruiken een file-based H2-database en Exposed voor databasecommunicatie.

### Motivatie

- H2 is geschikt voor een lokaal leerproject.
- Een file-based database bewaart gegevens tussen applicatiestarts.
- Exposed sluit aan bij Kotlin.

### Gevolgen

- We voegen databaseconfiguratie en tabellen toe.
- We ontwerpen een ERD.
- We testen het opslaan en teruglezen van monsters.
- De battle engine blijft onafhankelijk van de database.

---

## ADR-011 — UML-first en iteratief ontwikkelen

**Status:** Geaccepteerd als werkwijze

### Context

Het project is zowel een backendproject als een leerportfolio.

We willen kunnen uitleggen hoe het ontwerp tot stand is gekomen.

### Beslissing

We werken volgens deze cyclus:

Requirements → UML → Implementatie → Tests → UML bijwerken → Bewijs vastleggen

### Motivatie

- Ontwerpkeuzes worden zichtbaar.
- De implementatie blijft gekoppeld aan requirements.
- Afwijkingen tussen ontwerp en code kunnen worden besproken.

### Gevolgen

- We ontwerpen vóór het programmeren.
- We werken UML-diagrammen bij wanneer de code verandert.
- We bewaren alleen werkelijk uitgevoerde testresultaten als bewijs.

---

## ADR-012 — Git-commits per ontwikkelfase

**Status:** Geaccepteerd als werkwijze

### Context

We willen de ontwikkeling overzichtelijk vastleggen.

Voor Slice 0 is afgesproken dat alle ontwerpdocumentatie in één commit wordt opgenomen.

### Beslissing

- Slice 0: één commit na volledige afronding en controle.
- Slice 1: stapsgewijs committen.
- Slice 2: stapsgewijs committen.
- Slice 3: stapsgewijs committen.
- Slice 4: stapsgewijs committen.

### Motivatie

- Slice 0 vormt één samenhangende ontwerpbasis.
- Tijdens de implementatie maken kleinere commits wijzigingen beter traceerbaar.

### Gevolgen

- We committen Slice 0 pas na de eindcontrole.
- In latere slices stellen we commits voor na afgeronde onderdelen.
- We claimen nooit dat een commit is uitgevoerd zonder bevestiging.

---

## Relatie met andere documenten

Deze ontwerpbeslissingen horen bij:

- `docs/project/projectplan.md`
- `docs/project/specification.md`
- `docs/diagrams/01-use-cases.puml`
- `docs/diagrams/02-domain-class-diagram.puml`
- `docs/diagrams/03-battle-activity.puml`
- `docs/diagrams/04-battle-sequence.puml`

## Verificatiestatus

Dit document legt de ontwerpkeuzes vast.

De volgende zaken zijn nog niet automatisch geverifieerd:

- De daadwerkelijke PlantUML-rendering van alle diagrammen.
- De implementatie van de battle engine.
- De unit-tests en integratietests.
- De H2-persistentie.

Deze controles volgen tijdens de bijbehorende ontwikkelfases.

## AI-verantwoording

Dit document is met AI-ondersteuning opgesteld op basis van het PocketBattleAPI-projectplan, de projectspecificatie en de besproken UML-diagrammen.

De student controleert de keuzes, implementeert de functionaliteit en legt eigen testresultaten en leerervaringen vast.
