import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The frame for the monthPanel and eventsPanel of the calendar gui
 * @author eameans
 *
 */
public class CalendarFrame implements Runnable, WindowListener{

	private static JFrame frame;
	private static JPanel panel;
	private static JPanel monthPanel;
	private static JPanel eventPanel;
	
	public void run(){
		frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(1050, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      /**  frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        */
        
        frame.setVisible(true);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Calendar today = Calendar.getInstance();
        
        monthPanel = new MonthPanel(today);
        eventPanel = new EventPanel(today);
        panel.add(monthPanel, BorderLayout.WEST);
        panel.add(eventPanel, BorderLayout.EAST);
        
        frame.add(panel);
	}
	
	public static void refreshMonth(Calendar cal){
		panel.remove(monthPanel);
		JPanel jMonth = new MonthPanel(cal);
		monthPanel = jMonth;
		panel.add(monthPanel, BorderLayout.WEST);
		panel.revalidate();
		panel.repaint();
	}
	
	public static void refreshEvents(Calendar cal){
		panel.remove(eventPanel);
		JPanel jEvents = new EventPanel(cal);
		eventPanel = jEvents;
		panel.add(eventPanel, BorderLayout.EAST);
		panel.revalidate();
		panel.repaint();
	}
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new CalendarFrame());
		 CalStruct struct = new CalStruct();
	 }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
