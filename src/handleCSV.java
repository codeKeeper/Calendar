import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;

/*
	--TODO--
	writeCSV: Implement it!!!!, Test it
	readCSV: Test it,  Error throwing (default branch of switch)


*/

public class handleCSV{

	public static LinkedList<Object> readCSV(){
		String filePath = "rundata.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		LinkedList<Object> days = new LinkedList<Object>();
		LinkedList<Object> currDay = null; //Take note, when returned the first element is a 
		Event currEvent = null;
		try {
			File f = new File(filePath);
			if (!f.exists()){
				filePath = "Clean_Slate.csv";
			}

			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] events = line.split(csvSplit);
				switch (events[0].charAt(0)){
					case '0':
						currEvent = new Event(Integer.parseInt(events[1]), Integer.parseInt(events[2]), events[3]);
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

	@SuppressWarnings("All memebers of the data are LinkedList<Object> Trivial")
	public static LinkedList<Object> writeCSV(HashMap<Integer,LinkedList<Object>> data){
		LinkedList<Object> day;
		String filePath = "rundata.csv";
		LinkedList<Object> temp;
		int firstDay;
		try {
			FileWriter writer = new FileWriter(filePath);
			Set<Integer> keys = data.keySet();
			for (Object o : data.values()){
				day = (LinkedList<Object>) o;
				firstDay = 0;
				for(Object e : day){
					if (firstDay == 0){
						writer.append("1,");
						writer.append(Integer.toString((int)e));
						writer.append("\n");
						firstDay = 1;
						continue;
					}
					writer.append("0,");
					writer.append( Integer.toString(((Event)e).getStartTime()) + "," );
					writer.append( Integer.toString(((Event)e).getEndTime()) + "," ) ;
					writer.append(((Event)e).getDescription());
					writer.append("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}