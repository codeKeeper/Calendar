import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
	--TODO--
	writeCSV: Implement it!!!!, Test it
	readCSV: Test it,  Error throwing (default branch of switch)


*/

public class handleCSV{

	public static LinkedList<Object> readCSV(){
		String filePath = "exampledata.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		LinkedList<Object> days = new LinkedList<Object>();
		LinkedList<Object> currDay = null; //Take note, when returned the first element is a 
		Event currEvent = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] events = line.split(csvSplit);
				switch (events[0].charAt(0)){
					case '0':
						currEvent = new Event(events[1], events[2], Integer.parseInt(events[3])
							, Integer.parseInt(events[4]), events[5], Integer.parseInt(events[6]));
						currDay.addLast(currEvent);
						break;
					case '1':
						if (currDay != null){
							days.addLast(currDay);7
						}
						currDay = new LinkedList<Object>();
						currDay.addLast(Integer.parseInt(events[1]));
						break;
					default:
						//Invalid file throw errors here
						break;
				}
			}
			days.addLast(currDay);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return days;
	}

	public static LinkedList<Object> writeCSV(HashMap<Integer,LinkedList<Object>> data){
		LinkedList<Object> day;
		String filePath = "/home/campus18/rlmcnall-adm/Desktop/exampledata.csv";
		try {
			FileWriter writer = new FileWriter(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object o : data.values()){
			day = (LinkedList<Object>) o;
			writer.append("1,");
			writer.append( ((Event)day.getFirst()).hashCode() );
			writer.append("\n");
			for(Event e : day){
				writer.append("0");
				writer.append(e.startTimeDisplay);
				writer.append(e.endTimeDisplay);
				writer.append(e.startTime);
				writer.append(e.endTime);
				writer.append(e.day);
				writer.append(e.month);
				writer.append(e.description);
				writer.append("\n");
			}
		}
		return null;
	}
	
}