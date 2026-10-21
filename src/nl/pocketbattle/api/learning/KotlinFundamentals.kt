// src/nl/pocketbattle/api/learning/KotlinFundamentals.kt
package nl.pocketbattle.api.learning

fun calculateRemainingHp(currentHp: Int, damage: Int): Int {
    require(currentHp >= 0) { "Current HP cannot be negative" }
    require(damage >= 0) { "Damage cannot be negative" }

    return (currentHp - damage).coerceAtLeast(0)
}

fun describeMonsterType(type: String): String =
    when (type.uppercase()) {
        "FIRE" -> "Fire monster"
        "WATER" -> "Water monster"
        "GRASS" -> "Grass monster"
        else -> "Unknown monster"
    }

fun runKotlinFundamentalsDemo(): String {
    val monsterName = "Ember"
    val monsterType = "FIRE"
    val maxHp = 40
    var currentHp = maxHp

    val output = mutableListOf<String>()

    output.add("Welcome to PocketBattleAPI!")
    output.add("Monster: $monsterName")
    output.add("Type: ${describeMonsterType(monsterType)}")

    val damages = listOf(8, 12, 25)

    for (damage in damages) {
        currentHp = calculateRemainingHp(currentHp, damage)
        output.add("$monsterName takes $damage damage. HP: $currentHp")

        if (currentHp == 0) {
            output.add("$monsterName has been defeated!")
            break
        }
    }

    val isAlive = currentHp > 0
    output.add("Is $monsterName alive? $isAlive")

    return output.joinToString("\n")
}