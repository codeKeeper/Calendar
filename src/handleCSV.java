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
						currEvent = new Event(Integer.parseInt(events[1]), Integer.parseInt(events[2]), events[3], currDay.getFirst());
						currDay.addLast(currEvent);
						break;
					case '1':
						if (currDay != null){
							days.addLast(currDay);
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
		LinkedList<Event> day;
		String filePath = "/home/campus18/rlmcnall-adm/Desktop/exampledata.csv";
		try {
			FileWriter writer = new FileWriter(filePath);
		for (Object o : data.values()){
			day = (LinkedList<Event>) o;
			writer.append("1,");
			writer.append( Integer.toString(((Event)day.getFirst()).getDayCode()) );
			writer.append("\n");
			for(Event e : day){
				writer.append("0");
				writer.append( Integer.toString(e.getStartTime()) );
				writer.append( Integer.toString(e.getEndTime()) ) ;
				writer.append(e.getDescription());
				writer.append("\n");
			}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}