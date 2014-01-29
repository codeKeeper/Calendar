import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class EventPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel eventPanel;
	private String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novermber", "December"};
	protected String[] dayName = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private int date;
	private Calendar cal;
	private String suffix;
	
	public EventPanel(Calendar cal){
		this.cal = cal;
		this.date = cal.get(Calendar.DATE);
		this.add(createGUI());
	}
	
	// Create the month view of the calendar
	public JPanel createGUI(){
		eventPanel = new JPanel();
		eventPanel.setLayout(new BorderLayout());
		eventPanel.add(createTitleGUI(), BorderLayout.NORTH);	
		return eventPanel;
	}
	
	// Create the suffix for the Title
	public void createSuffix(int date){
		if (date == 1 || date == 21 || date == 31){
			suffix = "st";
		} else if (date == 2 || date == 22) {
			suffix = "nd";
		} else if (date == 3 || date == 23) {
			suffix = "rd";
		} else if (date >= 4 && date <= 20 || date >= 24 && date <= 30){
			suffix = "th";
		}
	}

	// Create the title (day, month and date) of the month view
	public JPanel createTitleGUI(){
		JPanel titlePanel = new JPanel();
		JButton addButton = new JButton();
		createSuffix(date);
		JLabel eventTitle = new JLabel(dayName[cal.get(Calendar.DAY_OF_WEEK)] + ", " + monthName[cal.get(Calendar.MONTH)] + " " + Integer.toString(date) + suffix);
		eventTitle.setFont(new Font("Helvetica", Font.BOLD, 18)); 
		
		addButton.setBorder(BorderFactory.createEmptyBorder());
		addButton.setContentAreaFilled(false);
		try {
			Image img1 = ImageIO.read(getClass().getResource("plus.png"));
			addButton.setIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}
		Listeners k = new Listeners();
		addButton.addActionListener(k);
		addButton.setActionCommand("Add Event");
		
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setSize(400, 100);
		
		eventTitle.setPreferredSize(new Dimension(400,100));

		titlePanel.add(eventTitle);
		titlePanel.add(addButton);

		return titlePanel;
	}

	public JPanel createEventsLayout(){
		JPanel eventsGrid = new JPanel(true);
		eventsGrid.setLayout(new BoxLayout(eventsGrid, BoxLayout.Y_AXIS));
			
		return eventsGrid;
	}
	
	public static void createNewEvent(){
		JPanel newEvent = new JPanel();
		newEvent.setSize(500, 500);
		//JPanel a = new JPanel();
	//	JPanel b = new JPanel();
	//	JPanel c = new JPanel();
		JLabel start = new JLabel("Start Time: ");
		JTextField startTime = new JTextField(20);
		JLabel end = new JLabel("End Time: ");
		JTextField endTime = new JTextField(20);
		JLabel descript = new JLabel("Description: ");
		JTextArea description = new JTextArea();

		description.setText("");
		description.setEditable(true);
		description.setRows(10);
		description.setColumns(20); 
		JScrollPane scroll = new JScrollPane(description);

		SpringLayout layout = new SpringLayout();
		newEvent.setLayout(layout);
		//a.setLayout(new SpringLayout());
		layout.putConstraint(SpringLayout.WEST, start, 5, SpringLayout.WEST, newEvent);
		layout.putConstraint(SpringLayout.EAST, startTime, 5, SpringLayout.EAST, newEvent);	
		layout.putConstraint(SpringLayout.WEST, startTime, 5, SpringLayout.EAST, start);
		newEvent.add(start);
		newEvent.add(startTime);
		layout.putConstraint(SpringLayout.NORTH, end, 5, SpringLayout.SOUTH, startTime);
		layout.putConstraint(SpringLayout.WEST, end, 5, SpringLayout.WEST, newEvent);
		newEvent.add(end);
		layout.putConstraint(SpringLayout.NORTH, endTime, 5, SpringLayout.SOUTH, startTime);
		layout.putConstraint(SpringLayout.EAST, endTime, 5, SpringLayout.EAST, newEvent);
		layout.putConstraint(SpringLayout.WEST, endTime, 10, SpringLayout.EAST, end);
		newEvent.add(endTime);
		layout.putConstraint(SpringLayout.NORTH, descript, 5, SpringLayout.SOUTH, endTime);
		layout.putConstraint(SpringLayout.WEST, descript, 5, SpringLayout.WEST, newEvent);
		newEvent.add(descript);
		
		//layout.putConstraint(SpringLayout.WEST, end, 5, SpringLayout.NORTH, newEvent);
		//layout.putConstraint(SpringLayout.NORTH, startTime, 5, SpringLayout.NORTH, startTime);
		//newEvent.add(endTime);
		

		//description.setSize(200, 200);
		//newEvent.add(scroll, SpringLayout.EAST);
		
		
		
	//	Calendar startT = Calendar.getInstance();
	//	Calendar endT = Calendar.getInstance();
		
	//	String[] st = startTime.getText().split(":");
	//	String[] en = endTime.getText().split(":");
	
	//	startT.set(Calendar.HOUR, Integer.parseInt(st[0]));
	//	startT.set(Calendar.MINUTE, Integer.parseInt(st[1]));
	//	endT.set(Calendar.HOUR, Integer.parseInt(en[0]));
	//	endT.set(Calendar.MINUTE, Integer.parseInt(en[1]));
	//	Event event = new Event(startT, endT, description.getText());
		
		
		JOptionPane.showConfirmDialog(null, newEvent, "New Event", JOptionPane.OK_CANCEL_OPTION);
		
	}
	
}
