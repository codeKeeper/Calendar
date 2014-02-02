import java.util.*;

/*
	--TODO--
	removeEvent: Look into edge cases of removing events
	addEvent: Test the sort, split event into two if it spans a day.

*/

	//this is a test comment

public class CalStruct {
	private HashMap<Integer,LinkedList<Object>> days;

	public CalStruct(){
		days = new HashMap<Integer,LinkedList<Object>>();
		populateMap();
	}

	@SuppressWarnings("Everything in the motherlist has to be a LinkedList<Object>")
	private void populateMap(){
		LinkedList<Object> motherList = handleCSV.readCSV();
		LinkedList<Object> childList;
		for (Object o : motherList){
			int key;
			childList = (LinkedList<Object>) o;
			key = (Integer) childList.getFirst();
			days.put(key, childList);
		}
	}

	public void writeData(){
		handleCSV.writeCSV(days);
	}

	public LinkedList<Object> getDay(int key){
		return days.get(key);
	}

	public void removeEvent(int key, Object aEvent){
		LinkedList<Object> temp = days.remove(key);
		if (((Event)aEvent).getSib() != 0){
			LinkedList<Object> others = days.remove(getNextDay(key));
			int firstRun = 0;
			for (Object o : others){
				if (firstRun == 0){
					firstRun = 1;
					continue;
				}
				if (((Event)o).getSib() == ((Event)aEvent).getSib()){
					others.remove((Event)o);
					days.put(getNextDay(key),others);
					break;
				}
			}
			others = days.remove(getPrevDay(key));
			firstRun = 0;
			for (Object o : others){
				if (firstRun == 0){
					firstRun = 1;
					continue;
				}
				if (((Event)o).getSib() == ((Event)aEvent).getSib()){
					others.remove((Event)o);
					days.put(getPrevDay(key),others);
					break;
				}
			}
		}
		temp.remove(aEvent);
		days.put(key,temp);
	}

	private int getNextDay(int key){
		if (((key == 131) || (key == 228)) || (key == 331)){
				return ((key / 100) * 100) + 1;
		} else if (key == 430){
				return 430;
		} else {
				return key+1;
		}
	}

	private int getPrevDay(int key){
		switch(key){
			case 101:
				return 101;
			case 201:
				return 131;
			case 301:
				return 228;
			case 401:
				return 331;
			default:
				return (key - 1);
		}
	}

	private LinkedList<Object> sortList(LinkedList<Object> toSort){
		Event sortTemp;
		int saveCode = (int)toSort.removeFirst();
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
		toSort.addFirst(saveCode);
		return toSort;
	}
	
	public boolean addEvent(int key, Event aEvent){
		LinkedList<Object> tempList = days.remove(key);
		LinkedList<Object> sibList = null;
		int storeEnd = 0;
		if (aEvent.getStartTime() < 0 || aEvent.getEndTime() > 2400){
			return false;
		}
		if (aEvent.getStartTime() > aEvent.getEndTime()){
			storeEnd = aEvent.getEndTime();
			aEvent.setSib(aEvent.getStartTime() * aEvent.getEndTime());
			aEvent.setEnd(2400);
			Event nextDay = new Event(1, storeEnd, aEvent.getDescription());
			nextDay.setSib(aEvent.getSib());
			int newKey = getNextDay(key);
			sibList = days.remove(newKey);
			sibList.addLast(nextDay);
			sibList = sortList(sibList);
			days.put(newKey, sibList);
		}
		tempList.addLast(aEvent);
		tempList = sortList(tempList);
		days.put(key,tempList);
		return true;
	}
}