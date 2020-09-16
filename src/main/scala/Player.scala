case class Player(
              name: String,
              initialRating: Double,
              //private val eloCalculator: EloCalculator,
              wins: List[Double],
              losses: List[Double]
            ) {
  //val finalRating: Double = eloCalculator.calculateRating(initialRating, wins, losses)


}
