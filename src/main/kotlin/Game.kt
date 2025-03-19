typealias Score = Pair<Int, Int>

abstract class Subject {
    private val observers: MutableList<Observer> = mutableListOf()
    fun attach(observer: Observer) = observers.add(observer)
    fun detach(observer: Observer) = observers.remove(observer)
    protected fun onUpdate() = observers.forEach { it.update() }
}

class Game : Subject() {
    var score: Score = 0 to 0
        private set(value) {
            field = value
            onUpdate()
        }

    fun onFirstTeamScores() {
        score = score.copy(first = score.first + 1)
    }

    fun onSecondTeamScores() {
        score = score.copy(second = score.second + 1)
    }
}

fun main() {
    val game = Game()
    val announcer1 = ScoreAnnouncer(game)
    val announcer2 = LeadingTeamAnnouncer(game)

    game.onFirstTeamScores()
    game.onSecondTeamScores()
    game.onSecondTeamScores()
}
