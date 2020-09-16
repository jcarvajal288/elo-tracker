import scala.math

class EloCalculator(k_value: Int) {

  private val k = k_value

  def calculateRating(ourRating: Double, wins: List[Double], losses: List[Double]): Double = {
    0.0
  }

  /*
   * algorithm from https://metinmediamath.wordpress.com/2013/11/27/how-to-calculate-the-elo-rating-including-example/
   */
  def calculateSingleRoundChange(ourRating: Double, theirRating: Double, isWin: Boolean): Long = {
    val r1 = math.pow(10, (ourRating / 400))
    val r2 = math.pow(10, (theirRating / 400))
    val e1 = r1 / (r1 + r2)
    val s1 = if(isWin) { 1 } else { 0 }
    math.round(k * (s1 - e1))
  }

  /*
   * algorithm from https://en.wikipedia.org/wiki/Elo_rating_system
   */
  def freshRating(wins: List[Double], losses: List[Double]): Int = {
    val totalOfOpponentsRatings = (wins ++ losses).sum
    val totalGames = wins.size + losses.size
    ((totalOfOpponentsRatings + 400 * (wins.size - losses.size)) / totalGames).toInt
  }

}
