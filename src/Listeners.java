import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class Listeners implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	    String cmd = ae.getActionCommand();
	    String[] eventKey = cmd.split(" ");
		Calendar cal = Calendar.getInstance();
		
		if (eventKey[0].equals("Add")){
			// create popup to accept new event
			EventPanel.createNewEvent();
			// add to events and resort list
			// repaint the eventsPanel
		} else if (eventKey.length == 3) {	
	    	cal.set(Integer.parseInt(eventKey[0]), Integer.parseInt(eventKey[1]), Integer.parseInt(eventKey[2]));
	    	CalendarFrame.refreshEvents(cal);	
	    } else {
	    	cal.set(Calendar.YEAR, Integer.parseInt(eventKey[0]));
	    	cal.set(Calendar.MONTH, Integer.parseInt(eventKey[1]));
	    	CalendarFrame.refreshMonth(cal);
	    }
	}
	
	
}
