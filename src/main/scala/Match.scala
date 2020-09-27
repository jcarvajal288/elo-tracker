case class Match(p1Name: String, p1Wins: Int, p2Name: String, p2Wins: Int) {

  def hasPlayer(playerName: String): Boolean = {
    p1Name == playerName || p2Name == playerName
  }

  def getOpponent(playerName: String): String = {
    playerName match {
      case `p1Name` => p2Name
      case `p2Name` => p1Name
      case _ => throw new RuntimeException(s"Player $playerName not in match!")
    }
  }

  def getPlayerWins(playerName: String): Int = {
    playerName match {
      case `p1Name` => p1Wins
      case `p2Name` => p2Wins
      case _ => 0
    }
  }
}

