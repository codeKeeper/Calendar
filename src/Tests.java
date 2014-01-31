public class Tests{


	public static void main(String[] args){
		CalStruct testStruct = new CalStruct();
		fileIOTests(testStruct);
		//addTest();
		//removeTest();
	}

	private static void fileIOTests(CalStruct cal){
		Event tempevent = new Event(1200,1300,"Beginning crime!");
		cal.addEvent(101, tempevent);
		tempevent = new Event(1200,1800,"Stopping crime!");
		cal.addEvent(121, tempevent);
		cal.writeData();
	}


	//Tests the functionallity of the add method and the sortList method
	private static void addTest(CalStruct cal){

	}

	//Tests the functionallity of the remove method.
	private static void removeTest(CalStruct cal){

	}


}