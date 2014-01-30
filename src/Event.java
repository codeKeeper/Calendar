import java.util.Calendar;

public class Event{
	private String startTimeDisplay;
	private String endTimeDisplay;
	private int startTime;
	private int endTime;
	private String description;
	// private int dayCode;

	//Special number if != 0 this event has a sibling
	//This number is the sum of the start and end of the initial submitted event that was split into two
	private int siblingNumber;
	
	/*private Calendar start;
	private Calendar end;
	private String description;*/
	
	public Event (int st, int et, String des){
		startTime = st;
		endTime = et;
		description = des;
		String timeString = Integer.toString(startTime);
		System.out.println(st);
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

	/*public Event(Calendar start, Calendar end, String description){
		this.setStart(start);
		this.setEnd(end);
		this.setDescription(description);*/
	}
		

	/*public int getDayCode(){
		return dayCode;
	}*/

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

	/*public int hashCode(){
		String fh = Integer.toString(month);
		String result = fh + day;
		int ret = Integer.parseInt(result);
		return ret;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		startTime = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setDescription(String adescription) {
		description = adescription;
	}*/
}