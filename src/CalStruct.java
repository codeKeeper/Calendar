import java.util.*;

/*
	--TODO--
	removeEvent: Look into edge cases of removing events
	addEvent: Test the sort, split event into two if it spans a day.

*/

public class CalStruct {
	private HashMap<Integer,LinkedList> days;

	public CalStruct(){
		days = new HashMap<Integer,LinkedList>();
		populateMap();
	}

	private void populateMap(){
		LinkedList motherList = handleCSV.readCSV();
		LinkedList childList;
		for (Object o : motherList){
			int key;
			childList = (LinkedList) o;
			key = (Integer) childList.removeFirst();
			days.put(key, childList);
		}
	}

	public void writeData(){
		writeCSV(days);
	}

	public LinkedList<Object> getDay(int key){
		return days.get(key);
	}

	public void removeEvent(int key, Object aEvent){
		LinkedList temp = days.remove(key);
		temp.remove(aEvent);
		days.put(key,temp);
	}

	private LinkedList<Event> sortList(LinkedList<Event> toSort){
		Event sortTemp;
		for (int x = 0; x < toSort.size() - 1; x++){
			for (int y = 0; y < toSort.size() - 1; y++){
				Event curr = (Event) toSort.get(y);
				Event next = (Event) toSort.get(y+1);
				if (curr.getStartTime() > next.getStartTime()){
					sortTemp = next;
					toSort.set(y+1, curr);
					toSort.set(y, sortTemp);
				}
			}
		}
	}
	
	public void addEvent(int key, Event aEvent){
		LinkedList tempList = days.remove(key);
		int storeEnd;
		if (aEvent.getStartTime() < aEvent.getEndTime()){
			storeEnd = aEvent.getEndTime;
			aEvent.setEndTime(2400);
		}
		tempList.addLast(aEvent);
		tempList = sortList(tempList);
		days.put(key,tempList);
		if (aEvent.getStartTime() < aEvent.getEndTime()){
			Event nextDay = new Event(1, storeEnd, aEvent.getDescription);
			int newKey;
			if (((key == 131) || (key == 228)) || ((key == 331) || (key == 430)){
				newKey = ((key / 100) * 100) + 1;
				tempList = days.remove(newKey); 
			} else {
				newKey = key + 1;
				tempList = days.remove(newKey);
			}
			tempList.add(nextDay);
			tempList = sortList(tempList);
			days.put(newKey, tempList);
		}
	}
}