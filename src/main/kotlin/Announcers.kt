interface Observer {
    fun update()
}

class ScoreAnnouncer(private val game: Game) : Observer {
    init { game.attach(this) }

    override fun update() {
        val (first, second) = game.score
        println("The score is currently $first to $second.")
    }
}

class LeadingTeamAnnouncer(private val game: Game) : Observer {
    init { game.attach(this) }

    override fun update() {
        val (first, second) = game.score
        val announcement = when {
            first > second -> "The first team is in the lead."
            second > first -> "The second team is in the lead."
            else           -> "The game is all tied up!"
        }
        println(announcement)
    }
}
