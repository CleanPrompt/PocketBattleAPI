# PocketBattleAPI — Projectinventarisatie en eerste servercontrole

## 1. Doel

Vastleggen dat de bestaande Kotlin/Ktor-projectomgeving is geïnventariseerd en dat de basisapplicatie lokaal bereikbaar is.

## 2. Projectomgeving

| Onderdeel | Waarde |
|---|---|
| Project | PocketBattleAPI |
| Programmeertaal | Kotlin |
| Framework | Ktor |
| Buildtool | JetBrains Amper |
| Buildconfiguratie | `module.yaml` |
| Serverengine | Netty |
| Geconfigureerde poort | 8080 |
| Datum controle | 22 oktober 2026 |

De exacte Kotlin- en Ktor-versies zijn nog niet vastgesteld.

## 3. Gecontroleerde bestanden

- `module.yaml` — buildconfiguratie inhoudelijk bekeken.
- `src/Application.kt` — startpunt en applicatiemodule bekeken.
- `src/Routing.kt` — bestaande routes bekeken; verwijdering van de oude leerroute voorgesteld.
- `resources/application.yaml` — moduleverwijzing en poort gecontroleerd.
- `resources/logback.xml` — aanwezig, inhoud nog niet gecontroleerd.

## 4. Handmatige servercontrole

**Test:** hoofdroute openen in de browser.

**URL:** `http://127.0.0.1:8080/`

**Verwachte uitvoer:**

```text
Welcome to PocketBattleAPI!
```

**Waargenomen uitvoer:**

```text
Welcome to PocketBattleAPI!
```

**Resultaat:** geslaagd volgens de handmatige terugkoppeling van de student.

## 5. Wat is hiermee aangetoond?

De applicatie was tijdens deze controle bereikbaar via de lokale HTTP-server en de hoofdroute retourneerde de verwachte tekst.

Dit is een handmatige functionele controle, geen geautomatiseerde unit- of integratietest.

## 6. Nog te controleren

- Exacte Kotlin-versie.
- Exacte Ktor-versie.
- Testconfiguratie en testcommando.
- Inhoud van `logback.xml`.
- Bevestigen dat de oude Kotlin Fundamentals-route daadwerkelijk uit `Routing.kt` is verwijderd.
- Geautomatiseerde tests toevoegen in de volgende ontwikkelfasen.

## 7. AI-verantwoording

AI heeft geholpen met het analyseren van de configuratie en het opstellen van dit verslag.

De serveruitvoer is door de student aangeleverd. AI heeft de server niet zelfstandig gestart of getest.
