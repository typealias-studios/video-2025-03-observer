import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

typealias Score = Pair<Int, Int>

class Game {
    private val _score = MutableStateFlow(0 to 0)
    val score = _score.asStateFlow()

    suspend fun onFirstTeamScores() {
        _score.getAndUpdate { it.copy(first = it.first + 1) }
        yield()
    }

    suspend fun onSecondTeamScores() {
        _score.getAndUpdate { it.copy(second = it.second + 1) }
        yield()
    }
}

fun main() = runBlocking<Unit> {
    val game = Game()

    launch { game.score.collect(::announceScore) }
    launch { game.score.collect(::announceLeadingTeam) }

    launch {
        game.onFirstTeamScores()
        game.onSecondTeamScores()

        game.onSecondTeamScores()
    }
}
