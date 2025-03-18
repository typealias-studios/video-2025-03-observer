typealias Score = Pair<Int, Int>

class Game(
    private val scoreAnnouncer: ScoreAnnouncer,
    private val leadingTeamAnnouncer: LeadingTeamAnnouncer
) {
    private var score: Score = 0 to 0
        set(value) {
            field = value
            scoreAnnouncer.announceScore(value)
            leadingTeamAnnouncer.announceLeadingTeam(value)
        }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

fun main() {
    val game = Game(ScoreAnnouncer(), LeadingTeamAnnouncer())

    game.onFirstTeamScores()
    game.onSecondTeamScores()
    game.onSecondTeamScores()
}
