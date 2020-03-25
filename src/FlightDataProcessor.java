import java.util.*;

/**
 * This class performs data analysis according to Assignment 5 questions
 * @author sarvesh
 *
 */

public class FlightDataProcessor {

	private ArrayList<FlightDetails> allFlightDetails = new ArrayList<FlightDetails>(); 

	/**
	 * Makes an ArrayList of FlightDetails from the lines input
	 * @param flightDataLines
	 */
	public FlightDataProcessor(ArrayList<String> flightDataLines) {
		for (String line: flightDataLines) {
			String[] ls = line.split(",");
			FlightDetails fd = new FlightDetails(ls[0],ls[1],ls[2],ls[3],ls[4],
					ls[5],ls[6],ls[7],ls[8],ls[9],ls[10],ls[11],ls[12],ls[13],ls[14],
					ls[15],ls[16],ls[17],ls[18],ls[19],ls[20],ls[21]);
			allFlightDetails.add(fd);
		}
	}

	/**
	 * Which carrier has the highest percentage of cancelled flights? 
	 * Outputs the 2-letter Carrier ID and the chance of a cancelled flight, 
	 * as a percentage (Example: AA,1.22%). 
	 * This percentage is defined as the number of canceled flights over the total number of flights.
	 * @return
	 */
	public String highestPercentageOfCancelledFlights() {
		HashMap<String,Integer> totalFlightsForCarrier = new HashMap<>();
		HashMap<String, Integer> totalCancellationsForCarrier = new HashMap<>();
		for (FlightDetails fd : allFlightDetails) {
			String carrier = fd.getCarrierID();
			String cancellation = fd.isCancelled(); 
			if (!fd.isDiverted().equals("1")) { //we ignore diverted flights in any case as per instructions
				if (!totalFlightsForCarrier.containsKey(carrier)) {
					totalFlightsForCarrier.put(carrier, 1);
				} else {
					totalFlightsForCarrier.put(carrier, totalFlightsForCarrier.get(carrier) + 1);
				}

				if (cancellation.equals("1")) {
					if (!totalCancellationsForCarrier.containsKey(carrier)) {
						totalCancellationsForCarrier.put(carrier, 1);
					} else {
						totalCancellationsForCarrier.put(carrier, totalCancellationsForCarrier.get(carrier) + 1);
					}
				}
			}
		}

		double highestCancellationRate = 0;;
		String mostCancelledCarrierID = "";
		for (String key : totalCancellationsForCarrier.keySet()) {
			int cancellations = totalCancellationsForCarrier.get(key);
			double totalFlights = totalFlightsForCarrier.get(key);
			double cancellationPercentage = cancellations/totalFlights;
			if (cancellationPercentage > highestCancellationRate) {
				highestCancellationRate = cancellationPercentage;
				mostCancelledCarrierID = key;
			}
		}

		return mostCancelledCarrierID + "," + highestCancellationRate*100;
	}

	/**
	 * What’s the most common cause of cancellations? Outputs the one-letter code.
	 * @return
	 */

	public String mostCommonCauseOfCancellation() {
		HashMap<String,Integer> cancellationCausesCount = new HashMap<>();

		for (FlightDetails fd : allFlightDetails) {
			String cancellationCode = fd.getCancellationCode();
			if (fd.isCancelled().equals("1") && !cancellationCode.isEmpty()) {
				if (!cancellationCausesCount.containsKey(cancellationCode)) {
					cancellationCausesCount.put(cancellationCode, 1);		
				} else {
					cancellationCausesCount.put(cancellationCode, cancellationCausesCount.get(cancellationCode) + 1);
				}
			}
		}

		int maxCancellationCount = 0;
		String maxCancellationCode = "";
		for (String key : cancellationCausesCount.keySet()) {
			int count = cancellationCausesCount.get(key);
			if (count > maxCancellationCount) {
				maxCancellationCount = count;
				maxCancellationCode = key;
			}
		}
		return maxCancellationCode;
	}

	/**
	 * Which plane (tail number) flew the furthest (most miles)? 
	 * Outputs the complete tailnumber (Example: N775AJ)
	 * @return
	 */

	public String tailNumWithFurthestMiles() {
		HashMap<String,Integer> totalMilesFlowByPlane = new HashMap<>();

		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd)) {
				String tailNumber = fd.getTailNumber();
				String distanceString = fd.getDistance();
				int distance = 	Integer.parseInt(distanceString);
				if (!totalMilesFlowByPlane.containsKey(tailNumber)) {
					totalMilesFlowByPlane.put(tailNumber, distance);
				} else {
					totalMilesFlowByPlane.put(tailNumber, totalMilesFlowByPlane.get(tailNumber) + distance);
				}
			}
		}

		int furthestMiles = 0;
		String furthestMileTailNumber = "";
		for (String key : totalMilesFlowByPlane.keySet()) {
			int milesFlown = totalMilesFlowByPlane.get(key);
			if (milesFlown > furthestMiles) {
				furthestMiles = milesFlown;
				furthestMileTailNumber = key;
			}
		}

		return furthestMileTailNumber;
	}

	/**
	 * Which airport is the busiest by total number of flights in and out? 
	 * Outputs the number OriginAirportID (Example: 12478)
	 * @return
	 */

	public String busiestAirportInAndOut() {
		HashMap<String, Integer> airportInAndOutTrafficMap = new HashMap<>();

		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd)) {
				String originID = fd.getOriginAirportID();
				String destID = fd.getDestAirportID();
				if (!airportInAndOutTrafficMap.containsKey(originID)) {
					airportInAndOutTrafficMap.put(originID, 1);
				} else {
					airportInAndOutTrafficMap.put(originID, airportInAndOutTrafficMap.get(originID) + 1);
				}
				if (!airportInAndOutTrafficMap.containsKey(destID)) {
					airportInAndOutTrafficMap.put(destID, 1);
				} else {
					airportInAndOutTrafficMap.put(destID, airportInAndOutTrafficMap.get(destID) + 1);
				}
			}
		}


		int mostTrafficCount = 0;
		String mostTrafficID = "";
		for (String key : airportInAndOutTrafficMap.keySet()) {
			int count = airportInAndOutTrafficMap.get(key);
			if (count > mostTrafficCount) {
				mostTrafficCount = count;
				mostTrafficID = key;
			}
		}
		return mostTrafficID;
	}

	/**
	 * Which airport is the biggest “source” of airplanes? 
	 * Use the difference between arrivals and departures to compute this value. 
	 * Outputs the OriginAirportID (Example: 12478) 
	 * Biggest source means find the greatest difference between the number of 
	 * departing flights and number of arriving flights. 
	 * 
	 * Which airport is the biggest “sink” of airplanes? 
	 * Biggest sink means find the greatest difference 
	 * between the number of arriving flights and number of departing flights.
	 * 
	 * (Does not consider cancelled flights in this calculation.)
	 * @return
	 */
	public String[] biggestSourceAndSinkAirport() {
		String[] output = new String[2]; //1 = biggest source airport, 2 = biggest sink airport
		HashMap<String, Integer>  originIDFlightsMap = new HashMap<>();
		HashMap<String, Integer>  destIDFlightsMap = new HashMap<>();

		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd)) {
				String originID = fd.getOriginAirportID();
				String destID = fd.getDestAirportID();
				if (!originIDFlightsMap.containsKey(originID)) {
					originIDFlightsMap.put(originID, 1);
				} else {
					originIDFlightsMap.put(originID, originIDFlightsMap.get(originID) + 1);
				}
				if (!destIDFlightsMap.containsKey(destID)) {
					destIDFlightsMap.put(destID, 1);
				} else {
					destIDFlightsMap.put(destID, destIDFlightsMap.get(destID) + 1);
				}
			}
		}

		int biggestSourceCount = 0;
		String biggestSourceID = "";
		int biggestSinkCount = 0;
		String biggestSinkID = "";

		for (String key : originIDFlightsMap.keySet()) {
			int sourceDifference = 0;
			if (destIDFlightsMap.containsKey(key)) {
				sourceDifference = originIDFlightsMap.get(key) - destIDFlightsMap.get(key);
			} else {
				sourceDifference = originIDFlightsMap.get(key);
			}
			if (sourceDifference > biggestSourceCount) {
				biggestSourceCount = sourceDifference;
				biggestSourceID = key;
			}
		}

		for (String key : destIDFlightsMap.keySet()) {
			int sinkDifference = 0;
			if (originIDFlightsMap.containsKey(key)) {
				sinkDifference = destIDFlightsMap.get(key) - originIDFlightsMap.get(key);
			} else {
				sinkDifference = destIDFlightsMap.get(key);
			}
			if (sinkDifference > biggestSinkCount) {
				biggestSinkCount = sinkDifference;
				biggestSinkID = key;
			}
		}

		output[0] = biggestSourceID;
		output[1] = biggestSinkID;
		return output;
	}

	/**
	 * How many American Airlines (Unique Carrier ID ‘AA’) flights were delayed by 60 minutes or more? 
	 * If a flight was delayed departing and arriving, only count that as 1. Outputs an integer.
	 * @return
	 */
	public int AAFlightsDelayedMoreThan60Minutes() {
		int delayCount = 0;
		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd) && fd.getCarrierID().equals("AA")) {
				int depDelay = Integer.parseInt(fd.getDepDelay());
				int arrDelay = Integer.parseInt(fd.getArrivalDelay());
				
				if (depDelay >= 60 || arrDelay >= 60) {
					delayCount++;
				}
			}
		}		
		return delayCount;
	}

	/**
	 * What was the largest delay that was made up (arrived early/on time)? 
	 * Outputs the Day of Month (the number), departure delay (as a number), 
	 * and the tail-number. Example: (10,30,N947JB). 
	 * The largest delay made up is equal to the largest DepDelay where ArrDelay <= 0; 
	 * it is not netted against arrival delay. 
	 * For example, a 10 min delayed departure (DepDelay = 10) with 5 minute early 
	 * arrival (ArrDelay = -5) does not equal 15 minutes made up, but rather only the 
	 * 10 minute departure delay made up 
	 * @return
	 */

	public String largestDelayMadeUp() {

		int largestDelayMadeUp = 0;
		String tailNumberOfFlight = "";
		String dayOfMonthOfFlight = "";
		int depDelayOfFlight = 0;

		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd)) {
				String dayOfMonth = fd.getDayOfMonth();
				String tailNumber = fd.getTailNumber();
				String depDelay = fd.getDepDelay();
				String arrDelay = fd.getArrivalDelay();
				int depDelayInt = Integer.parseInt(depDelay);
				int arrDelayInt = Integer.parseInt(arrDelay);
				if (arrDelayInt <= 0) {
					int difference = depDelayInt;	
					if (difference > largestDelayMadeUp) {
						largestDelayMadeUp = difference;
						tailNumberOfFlight = tailNumber;
						dayOfMonthOfFlight = dayOfMonth;
						depDelayOfFlight = depDelayInt;
					}
				}
			}
		}
		return (dayOfMonthOfFlight + "," + depDelayOfFlight + "," + tailNumberOfFlight);
	}

	/**
	 * Outputs the average speed in miles per hour using air time and distance traveled
	 * @return
	 */

	public String averageSpeedOfFlights() {
		double totalAirTime = 0;
		double totalDistance = 0;
		for (FlightDetails fd : allFlightDetails) {
			if (!hasBlankCellsOrDivertedOrCancelledFlight(fd))	{
				String airTimeString = fd.getAirTime();
				String distanceString = fd.getDistance();
				double airTime = Double.parseDouble(airTimeString);
				double distance = Double.parseDouble(distanceString);
				totalAirTime += airTime;
				totalDistance += distance;
			}
		}
		double totalAirTimeInHours = totalAirTime/60;
		double averageSpeed = totalDistance/totalAirTimeInHours;
		return Double.toString(averageSpeed);
	}

	//returns true if the row contains blank fields other than cancellation code or if flight is diverted or cancelled
	private boolean hasBlankCellsOrDivertedOrCancelledFlight(FlightDetails fd) {
		if (fd.getDayOfMonth().isEmpty() || fd.getDayOfWeek().isEmpty() || fd.getFlightDate().isEmpty()|| 
				fd.getCarrierID().isEmpty() || fd.getTailNumber().isEmpty() || fd.getOriginAirportID().isEmpty() || fd.getOrigin().isEmpty() 
				|| fd.getOriginStateName().isEmpty()|| fd.getDestAirportID().isEmpty() || fd.getDest().isEmpty() 
				|| fd.getDestStateName().isEmpty() || fd.getDepartureTime().isEmpty() 
				|| fd.getDepDelay().isEmpty() || fd.getWheelsOff().isEmpty() || fd.getWheelsOn().isEmpty()
				|| fd.getArrivalTime().isEmpty() || fd.getArrivalDelay().isEmpty() || fd.isCancelled().isEmpty() || fd.isDiverted().equals("1")
				|| fd.isCancelled().equals("1") || fd.getAirTime().isEmpty() || fd.getDistance().isEmpty()) {
			return true;
		}
		return false;			
	}


//	public static void main(String[] args) {
//		CSVReader cr = new CSVReader("flights_small.csv");
//		FlightDataProcessor fp = new FlightDataProcessor(cr.getAllLines());
//		System.out.println("Total lines: " + cr.getAllLines().size());
//		System.out.println("Question 1: " + fp.highestPercentageOfCancelledFlights());
//		System.out.println("Question 2: " + fp.mostCommonCauseOfCancellation());
//		System.out.println("Question 3: " + fp.tailNumWithFurthestMiles());
//		System.out.println("Question 4: " + fp.busiestAirportInAndOut());
//		System.out.println("Question 5: " + fp.biggestSourceAndSinkAirport()[0]);
//		System.out.println("Question 6: " + fp.biggestSourceAndSinkAirport()[1]);
//		System.out.println("Question 7: " + fp.AAFlightsDelayedMoreThan60Minutes());
//		System.out.println("Question 8: " + fp.largestDelayMadeUp());
//		System.out.println("Question 9: " + fp.averageSpeedOfFlights());	
//	}
}
