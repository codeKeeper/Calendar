import java.util.Calendar;

public class Event{
	private String startTimeDisplay;
	private String endTimeDisplay;
	private int startTime;
	private int endTime;
	private String description;
	
	//Special number if != 0 this event has a sibling
	private int siblingNumber = 0;
	
	public Event (int st, int et, String des){
		startTime = st;
		endTime = et;
		description = des;
		String timeString = Integer.toString(startTime);
		if (st >= 1000){
			startTimeDisplay = timeString.substring(0,2) + ":" + timeString.substring(2,4);
		} else if (st >= 100) {
			startTimeDisplay = timeString.substring(0,1) + ":" + timeString.substring(1,3);
		} else if (st >= 10) {
			startTimeDisplay = "00:" + st;
		} else {
			startTimeDisplay = "00:0" + st;
		}
		if (st >= 1000){
			endTimeDisplay = timeString.substring(0,2) + ":" + timeString.substring(2,4);
		} else if (st >= 100) {
			endTimeDisplay = timeString.substring(0,1) + ":" + timeString.substring(1,3);
		} else if (st >= 10) {
			endTimeDisplay = "00:" + st;
		} else {
			endTimeDisplay = "00:0" + st;
		}
	}

	public String getStartDisplay(){
		return startTimeDisplay;
	}

	public String getEndDisplay(){
		return endTimeDisplay;
	}


	public int getStartTime(){
		int copy = startTime;
		return copy;
	}

	public int getEndTime(){
		int copy = endTime;
		return copy;
	}

	public void setEnd(int aend) {
		endTime = aend;
	}

	public String getDescription() {
		return description;
	}

	public int getSib(){
		return siblingNumber;
	}

	public void setSib(int aNum){
		siblingNumber = aNum;
	}
}