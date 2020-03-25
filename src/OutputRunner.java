/**
 * The runner class 
 * @author sarvesh
 *
 */

public class OutputRunner {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		CSVReader cr = new CSVReader("flights.csv");
		FlightDataProcessor fp = new FlightDataProcessor(cr.getAllLines());
		FormattedOutput fo = new FormattedOutput();
		fo.addAnswer(1, fp.highestPercentageOfCancelledFlights());
		fo.addAnswer(2, fp.mostCommonCauseOfCancellation());
		fo.addAnswer(3, fp.tailNumWithFurthestMiles());
		fo.addAnswer(4, fp.busiestAirportInAndOut());
		fo.addAnswer(5, fp.biggestSourceAndSinkAirport()[0]);
		fo.addAnswer(6, fp.biggestSourceAndSinkAirport()[1]);
		fo.addAnswer(7, fp.AAFlightsDelayedMoreThan60Minutes());
		fo.addAnswer(8, fp.largestDelayMadeUp());
		fo.addAnswer(9, fp.averageSpeedOfFlights()); //average speed of flight in miles/h
		fo.writeAnswers();
		long endTime = System.nanoTime();
		System.out.println("Output complete! Time taken: " + (endTime - startTime)/1000000 + "ms");
	}
}
