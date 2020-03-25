/**
 * This class is template that has fields for the flight details as described in the data set provided for Assignment 5 
 * @author sarvesh
 *
 */

public class FlightDetails {
	
	private String dayOfMonth;
	private String dayOfWeek;
	private String flightDate;
	private String carrierID;
	private String tailNumber;
	private String originAirportID;
	private String origin;
	private String originStateName;
	private String destAirportID;
	private String dest;
	private String destStateName;	
	private String departureTime;
	private String depDelay;
	private String wheelsOff;
	private String wheelsOn;
	private String arrivalTime;
	private String arrivalDelay;
	private String isCancelled;
	private String cancellationCode;
	private String isDiverted;
	private String airTime;
	private String distance;

	public FlightDetails(String dayOfMonth, String dayOfWeek, String flightDate, String carrierID, String tailNumber,
			String originAirportID, String origin, String originStateName, String destAirportID, String dest,
			String destStateName, String departureTime, String depDelay, String wheelsOff, String wheelsOn,
			String arrivalTime, String arrivalDelay, String isCancelled, String cancellationCode, String isDiverted,
			String airTime, String distance) {
		super();
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		this.flightDate = flightDate;
		this.carrierID = carrierID;
		this.tailNumber = tailNumber;
		this.originAirportID = originAirportID;
		this.origin = origin;
		this.originStateName = originStateName;
		this.destAirportID = destAirportID;
		this.dest = dest;
		this.destStateName = destStateName;
		this.departureTime = departureTime;
		this.depDelay = depDelay;
		this.wheelsOff = wheelsOff;
		this.wheelsOn = wheelsOn;
		this.arrivalTime = arrivalTime;
		this.arrivalDelay = arrivalDelay;
		this.isCancelled = isCancelled;
		this.cancellationCode = cancellationCode;
		this.isDiverted = isDiverted;
		this.airTime = airTime;
		this.distance = distance;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public String getCarrierID() {
		return carrierID;
	}

	public String getTailNumber() {
		return tailNumber;
	}

	public String getOriginAirportID() {
		return originAirportID;
	}

	public String getDestAirportID() {
		return destAirportID;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getDepDelay() {
		return depDelay;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getArrivalDelay() {
		return arrivalDelay;
	}

	public String isCancelled() {
		return isCancelled;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public String getOrigin() {
		return origin;
	}

	public String getOriginStateName() {
		return originStateName;
	}

	public String getDest() {
		return dest;
	}

	public String getDestStateName() {
		return destStateName;
	}

	public String getWheelsOff() {
		return wheelsOff;
	}

	public String getWheelsOn() {
		return wheelsOn;
	}

	public String getCancellationCode() {
		return cancellationCode;
	}
	
	public String isDiverted() {
		return isDiverted;
	}
	
	public String getAirTime() {
		return airTime;
	}

	public String getDistance() {
		return distance;
	}
	
	


}
