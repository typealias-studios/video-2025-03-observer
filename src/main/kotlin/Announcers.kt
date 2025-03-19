class ScoreAnnouncer {
    fun update(score: Score) {
        val (first, second) = score
        println("The score is currently $first to $second.")
    }
}

class LeadingTeamAnnouncer {
    fun update(score: Score) {
        val (first, second) = score
        val announcement = when {
            first > second -> "The first team is in the lead."
            second > first -> "The second team is in the lead."
            else           -> "The game is all tied up!"
        }
        println(announcement)
    }
}
