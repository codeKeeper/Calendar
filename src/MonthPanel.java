import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Calendar;

/**
 * This class will create the month panel of the calendar gui
 * @author eameans
 *
 */
public class MonthPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int month;
	private int year;
	private String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novermber", "December"};
	private String[] dayName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private JPanel monthPanel;
	
	// Constructor
	public MonthPanel(Calendar cal){
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
		this.add(createGUI());
	}
	
	// Create the month view of the calendar
	public JPanel createGUI(){
		monthPanel = new JPanel();
		monthPanel.setLayout(new BorderLayout());
		monthPanel.setBackground(Color.BLACK);
		monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
		monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);	
		return monthPanel;
	}
	
	// Create the title (month and year) of the month view
	public JPanel createTitleGUI(){
		JPanel titlePanel = new JPanel();
		JButton leftButton = new JButton();
		JButton rightButton = new JButton();
		JLabel titleMonth = new JLabel(monthName[month] + " " + year);
		titleMonth.setHorizontalAlignment(SwingConstants.CENTER);
	
		// Make the calendar navigation buttons transparent 
		leftButton.setBorder(BorderFactory.createEmptyBorder());
		leftButton.setContentAreaFilled(false);
		rightButton.setBorder(BorderFactory.createEmptyBorder());
		rightButton.setContentAreaFilled(false);
		
		// Get and apply the image for the left calendar button
		try {
			Image img1 = ImageIO.read(getClass().getResource("leftArrow.png"));
			leftButton.setIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}
		// Get and apply the image for the right calendar button
		try {
			Image img2 = ImageIO.read(getClass().getResource("rightArrow.png"));
			rightButton.setIcon(new ImageIcon(img2));
		} catch (IOException ex) {
		}
		// Add listeners to the calendar buttons
		Listeners k = new Listeners();
		leftButton.addActionListener(k);
		rightButton.addActionListener(k);
		leftButton.setActionCommand(Integer.toString(year) + " " + Integer.toString(month - 1));
		rightButton.setActionCommand(Integer.toString(year) + " " + Integer.toString(month + 1));
		
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setSize(800, 100);
		titleMonth.setPreferredSize(new Dimension(500,100));
		titleMonth.setFont(new Font("Helvetica", Font.BOLD, 36)); 

		titlePanel.add(leftButton);
		titlePanel.add(titleMonth);
		titlePanel.add(rightButton);

		return titlePanel;
	}
	
	// Add the dates of the month to the calendar
	protected JPanel createDaysGUI() {
        JPanel daysGrid = new JPanel(true);
        daysGrid.setLayout(new GridLayout(0, dayName.length));

        // What day is today in case we need to highlight it
        Calendar today = Calendar.getInstance();
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        // The start of the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Use this to go through all the days of the month
        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH,
                -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

        // The end of the month, so we don't display more than we should
        Calendar maximum = (Calendar) calendar.clone();
        maximum.add(Calendar.MONTH, +1);

        // Add the weekdays to the calendar
        for (int i = 0; i < dayName.length; i++) {
            JPanel dPanel = new JPanel(true);
            JLabel date = new JLabel(dayName[i]);
            dPanel.add(date);
            daysGrid.add(dPanel);
        }

        int count = 0;
        int limit = dayName.length * 6;

        // Create and add all buttons of the dates to the calendar
        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            int lMonth = iterator.get(Calendar.MONTH);
            int lYear = iterator.get(Calendar.YEAR);

            JPanel dPanel = new JPanel(true);
            JButton dayLabel = new JButton();
            dayLabel.setForeground(Color.BLACK);
            dayLabel.setContentAreaFilled(false);
            dayLabel.setBorderPainted(false);
            Listeners l = new Listeners();
            dayLabel.addActionListener(l);

            if ((lMonth == month) && (lYear == year)) {
                int lDay = iterator.get(Calendar.DAY_OF_MONTH);
                dayLabel.setText(Integer.toString(lDay));
                dayLabel.setActionCommand(Integer.toString(lYear) + " " + Integer.toString(lMonth) + " " + Integer.toString(lDay));
                if ((todayMonth == month) && (todayYear == year) && (todayDay == lDay)) {
                    dPanel.setBackground(Color.YELLOW);
                }
            } else {
                dayLabel.setText(" ");
            }
            dPanel.add(dayLabel);
            daysGrid.add(dPanel);
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            count++;
        }

        // Add blanks where needed
        for (int i = count; i < limit; i++) {
            JPanel dPanel = new JPanel(true);
            JLabel dayLabel = new JLabel();
            dayLabel.setText(" ");
            dPanel.add(dayLabel);
            daysGrid.add(dPanel);
        }
        return daysGrid;
    }
}
