import kotlin.properties.Delegates.observable

typealias Score = Pair<Int, Int>

class Game(private val observers: List<(Score) -> Unit>) {
    var score: Score by observable(0 to 0) { _, _, new ->
        observers.forEach { update -> update(new) }
    }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

fun main() {
    val announcer1 = ScoreAnnouncer()
    val announcer2 = LeadingTeamAnnouncer()
    val game = Game(listOf(announcer1::update, announcer2::update))

    game.onFirstTeamScores()
    game.onSecondTeamScores()
    game.onSecondTeamScores()
}
