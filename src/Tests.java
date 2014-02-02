public class Tests{


	public static void main(String[] args){
		CalStruct testStruct = new CalStruct();
		addTest(testStruct);
		fileIOTests(testStruct);
		//removeTest();
	}

	private static void fileIOTests(CalStruct cal){
		cal.writeData();
	}


	//Tests the functionallity of the add method and the sortList method
	private static void addTest(CalStruct cal){
		Event tempevent = new Event(1200,1300,"Beginning crime!");
		cal.addEvent(101, tempevent);
		tempevent = new Event(400,700,"Earlier");
		cal.addEvent(101, tempevent);
		tempevent = new Event(1500,1900,"Later");
		cal.addEvent(101, tempevent);
		tempevent = new Event(1200,700,"Stopping crime!");
		cal.addEvent(121, tempevent);
		cal.removeEvent(121, tempevent);
	}

	//Tests the functionallity of the remove method.
	private static void removeTest(CalStruct cal){

	}


}