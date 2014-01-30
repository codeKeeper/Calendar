public class Tests{

	public static void main(String[] args){
		CalStruct testStruct = new CalStruct();
		Event tempevent = new Event(1200,1300,"Stopping crime!");
		testStruct.addEvent(101, tempevent);
		tempevent = new Event(1200,1800,"Stopping crime!");
		testStruct.addEvent(121, tempevent);
		testStruct.writeData();
	}
}