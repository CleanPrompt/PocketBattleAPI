# PocketBattleAPI — Projectspecificatie

**Versie:** 1.0  
**Datum:** 22 oktober 2026  
**Status:** Voorgesteld ontwerp  
**Projectfase:** Slice 0

## 1. Projectdoel

PocketBattleAPI is een REST API ontwikkeld met Kotlin en Ktor waarmee gebruikers monsters kunnen beheren en automatische, turn-based gevechten kunnen simuleren.

De applicatie heeft twee doelen:

1. Een werkende, overzichtelijke en geteste backend ontwikkelen.
2. Aantonen hoe Kotlin, objectgeoriënteerd programmeren, functioneel programmeren, REST API's, databases en UML worden toegepast.

## 2. Functionele requirements

| ID | Requirement |
|---|---|
| FR-01 | De gebruiker kan een monster aanmaken. |
| FR-02 | De gebruiker kan alle opgeslagen monsters bekijken. |
| FR-03 | De gebruiker kan één monster op basis van zijn ID bekijken. |
| FR-04 | De gebruiker kan een bestaand monster wijzigen. |
| FR-05 | De gebruiker kan een monster verwijderen. |
| FR-06 | De gebruiker kan monsters filteren op elementtype. |
| FR-07 | De gebruiker kan een automatisch gevecht starten tussen twee verschillende bestaande monsters. |
| FR-08 | De applicatie bepaalt automatisch de aanvalsvolgorde en selecteert aanvallen. |
| FR-09 | De applicatie berekent schade volgens de vastgestelde gevechtsregels. |
| FR-10 | De applicatie retourneert de winnaar, verliezer en een chronologisch gevechtsverslag. |
| FR-11 | De applicatie bewaart monstergegevens permanent in een H2-database. |

## 3. Niet-functionele requirements

| ID | Requirement |
|---|---|
| NFR-01 | De applicatie wordt ontwikkeld met Kotlin en Ktor. |
| NFR-02 | Het project gebruikt JetBrains Amper en `module.yaml`. |
| NFR-03 | De REST API communiceert via HTTP en gebruikt JSON voor de monster- en gevechtsgegevens. |
| NFR-04 | De gevechtsberekening is deterministisch: dezelfde invoer levert hetzelfde resultaat op. |
| NFR-05 | De battle engine kan onafhankelijk van Ktor en de database worden getest. |
| NFR-06 | De applicatie bevat geautomatiseerde unit-, repository- en integratietests. |
| NFR-07 | Belangrijke ontwerpbeslissingen en UML-diagrammen worden bijgehouden. |
| NFR-08 | De code is Engelstalig en de uitleg en leerportfolio-inhoud zijn Nederlandstalig. |

## 4. Business rules

### 4.1 Monsters

**BR-01:** Een monster heeft een ID, naam, elementtype, level, maximale HP, defense en speed.

**BR-02:** Een monster heeft precies één van de elementtypes `FIRE`, `WATER` of `GRASS`.

**BR-03:** Elk monster heeft precies twee aanvallen: één normale aanval en één elementaanval.

**BR-04:** Een aanval heeft een naam en een positieve basiskracht.

**BR-05:** Een normale aanval heeft geen elementtype. Een elementaanval heeft hetzelfde elementtype als het monster.

### 4.2 Type-effectiviteit

**BR-06:** De type-effectiviteit volgt deze tabel:

| Aanvalstype | Tegen FIRE | Tegen WATER | Tegen GRASS |
|---|---:|---:|---:|
| FIRE | 1.0 | 0.75 | 1.5 |
| WATER | 1.5 | 1.0 | 0.75 |
| GRASS | 0.75 | 1.5 | 1.0 |
| Normaal | 1.0 | 1.0 | 1.0 |

### 4.3 Schadeberekening

**BR-07:** Schade wordt berekend met de formule:

```text
Damage = max(
    1,
    floor(
        (BasePower * (Level + 5) / (Defense + 10))
        * TypeMultiplier
    )
)
```

Hierbij is `Level` het level van de aanvaller en `Defense` de defense van de verdediger.

**BR-08:** Schade bedraagt minimaal 1 HP en bevat geen willekeurige factor.

### 4.4 Gevechtsverloop

**BR-09:** Het monster met de hoogste speed begint. Bij gelijke speed begint het eerste monster uit de aanvraag.

**BR-10:** Monsters vallen om de beurt aan.

**BR-11:** Elk monster wisselt zijn eigen aanvallen af: normaal, elementair, normaal, elementair, enzovoort.

**BR-12:** Een gevecht eindigt zodra één monster 0 HP bereikt.

**BR-13:** Iedere uitgevoerde aanval wordt vastgelegd in het gevechtsverslag.

**BR-14:** Gevechten gebruiken tijdelijke HP en aanvalstellers. Opgeslagen monstergegevens blijven ongewijzigd.

**BR-15:** Een nieuw gevecht begint voor beide monsters met volledige HP.

**BR-16:** Een gevecht kan alleen worden gestart met twee verschillende, bestaande monster-ID's.

## 5. REST API-endpoints

| Methode | Endpoint | Functionaliteit |
|---|---|---|
| GET | `/monsters` | Alle monsters bekijken |
| GET | `/monsters/{id}` | Eén monster bekijken |
| POST | `/monsters` | Monster aanmaken |
| PUT | `/monsters/{id}` | Monster wijzigen |
| DELETE | `/monsters/{id}` | Monster verwijderen |
| GET | `/monsters?type=FIRE` | Monsters filteren |
| POST | `/battles` | Automatisch gevecht simuleren |

De bestaande hoofdroute `GET /` blijft beschikbaar als eenvoudige welkomstroute.

## 6. Acceptatiecriteria

### AC-01 — Monster aanmaken

- De API accepteert geldige monstergegevens.
- Het monster krijgt een unieke ID.
- Ongeldige monstergegevens worden afgewezen.
- Het aangemaakte monster kan opnieuw worden opgevraagd.

### AC-02 — Monsterbeheer

- Bestaande monsters kunnen worden bekeken en gewijzigd.
- Monsters kunnen worden verwijderd.
- Filtering op `FIRE`, `WATER` en `GRASS` levert alleen monsters van het gekozen type op.
- Een niet-bestaand monster wordt correct afgehandeld.

### AC-03 — Automatisch gevecht

- De aanvraag bevat twee verschillende bestaande monster-ID's.
- Beide monsters starten met volledige HP.
- Het monster met de hoogste speed begint.
- Aanvallen wisselen per monster correct af.
- Schade volgt de vastgestelde formule en type-effectiviteit.
- Het gevecht stopt zodra één monster is verslagen.
- Het resultaat bevat winnaar, verliezer, aantal aanvallen en een chronologische log.
- Opgeslagen monstergegevens veranderen niet door het gevecht.
- Dezelfde monsters leveren bij herhaling hetzelfde gevechtsresultaat op.

### AC-04 — Persistentie

- Monsters en hun aanvallen kunnen in H2 worden opgeslagen.
- Opgeslagen monsters kunnen na het opnieuw openen van de database worden teruggelezen.
- De battle engine blijft onafhankelijk van de opslagimplementatie.

### AC-05 — Testbaarheid

- De schadeberekening is afzonderlijk te testen.
- De battle engine is zonder HTTP-server te testen.
- Monsterbeheer en REST-endpoints worden met passende tests gecontroleerd.
- Testresultaten worden pas als geslaagd geregistreerd nadat de tests daadwerkelijk zijn uitgevoerd.

## 7. Buiten scope

PocketBattleAPI bevat geen grafische game-interface, gebruikersaccounts, authenticatie, multiplayer, interactieve gevechtsbeurten, items, experience points, level-ups, kritieke aanvallen, willekeurige schade of permanente gevechtsgeschiedenis.

## 8. Ontwikkelvolgorde

- **Slice 0:** Requirements en UML-ontwerp.
- **Slice 1:** Domeinmodellen en battle engine.
- **Slice 2:** Monsterbeheer en REST API met in-memory opslag.
- **Slice 3:** H2-persistentie.
- **Slice 4:** Leerportfolio en documentatieafronding.

## 9. Status en verificatie

Dit document beschrijft de beoogde functionaliteit. Het betekent niet dat alle requirements al geïmplementeerd of getest zijn.

De bestaande Ktor-hoofdroute is handmatig gecontroleerd via `http://127.0.0.1:8080/`.

De monsterfunctionaliteit, battle engine, database en bijbehorende geautomatiseerde tests moeten nog worden ontwikkeld.

## 10. AI-verantwoording

Deze eerste projectspecificatie is met hulp van AI opgesteld op basis van Projectplan v3.0.

De requirements en ontwerpkeuzes moeten tijdens de ontwikkeling worden gecontroleerd en waar nodig bijgewerkt.
