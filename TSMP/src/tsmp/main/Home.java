package tsmp.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  {
		//UIManager.setLookAndFeel("com.jtatoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mainclass()
	{
		
	
	Thread clock=new Thread()
			{
					public void run()
					{
						try
						{
							while(true)
							{
						Calendar cal=new GregorianCalendar();
						int day=cal.get(Calendar.DATE);
						int month=cal.get(Calendar.MONTH);
						int year=cal.get(Calendar.YEAR);
						int second=cal.get(Calendar.SECOND);
						int minute=cal.get(Calendar.MINUTE);
						int hour=cal.get(Calendar.HOUR);
					lblNewLabel_1.setText("Time "+hour+":"+minute+":"+second+" Date  "+day+"/"+month+"/"+year);
					
							sleep(1000);
						}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			
			};
	clock.start();
	
	}
	
	/**
	 * Create the frame.
	 */
	public Home() {
	
		setFont(new Font("Dialog", Font.BOLD, 16));
		setTitle("DAIRY MANAGEMENT SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1383, 752);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		 lblNewLabel_1 = new JLabel("New label");
		 lblNewLabel_1.setBounds(1065, 11, 292, 48);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Member Master");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberCreation m=new MemberCreation();
				m.setVisible(true);
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(10, 86, 179, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Milk Parameters");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 153, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fatrange f=new fatrange();
				f.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(10, 172, 179, 54);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Milk Acquirement");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(0, 153, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procurement p=new Procurement();
				p.setVisible(true);
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(10, 265, 179, 54);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Feed Issue");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(new Color(0, 153, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedIssue f= new FeedIssue();
				f.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(10, 361, 179, 54);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Advance Payment");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(new Color(0, 153, 255));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdvancePayment ap=new AdvancePayment();
				ap.setVisible(true);
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(10, 457, 179, 60);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Reports");
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(new Color(0, 153, 255));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report r =new report();
				r.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(10, 559, 179, 54);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Logout");
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setBackground(new Color(0, 153, 255));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmValue = JOptionPane.showConfirmDialog(null, "Do you really want to Logout?");
				if(confirmValue == 0)
					System.exit(0);
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_6.setBounds(10, 648, 179, 54);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/resources/output (10).png")));
		lblNewLabel.setBounds(199, 70, 1168, 643);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("MILK PRODUCER'S CO-OPERATIVE SOCIETY");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setBounds(344, 5, 711, 54);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Home.class.getResource("/resources/resize-15840150081082620365hh.jpg")));
		lblNewLabel_3.setBounds(0, 0, 1367, 68);
		contentPane.add(lblNewLabel_3);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 mainclass();

		
	}
	}





