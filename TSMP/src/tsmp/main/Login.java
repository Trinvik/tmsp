package tsmp.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;

public class Login extends JFrame {

	private JFrame frmMps;
	private JTextField text1;
	private JPasswordField text2;

	
	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}*/
		splashscreen sh=new splashscreen();
		sh.setVisible(true);
		sh.setLocationRelativeTo(null);
		try
		{
			for(int i=0;i<100;i++)
			{
				Thread.sleep(30);
			}
			sh.setVisible(false);
			
		}
		catch(Exception e) {
			
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Login window = new Login();
					window.frmMps.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		
	
			}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMps = new JFrame();
		frmMps.setResizable(false);
		frmMps.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMps.setTitle("DAIRY MANAGEMENT SYSTEM");
		frmMps.setBounds(100, 100, 719, 364);
		frmMps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMps.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Username");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(103, 65, 161, 41);
		frmMps.getContentPane().add(lblNewLabel);
		
		text1 = new JTextField();
		text1.setFont(new Font("Tahoma", Font.BOLD, 18));
		text1.setBounds(258, 72, 280, 32);
		frmMps.getContentPane().add(text1);
		text1.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		//comboBox.addItem("..Select..");
		comboBox.addItem("Admin");
		comboBox.addItem("user");
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(258, 158, 280, 32);
		frmMps.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Password");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(103, 117, 207, 30);
		frmMps.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str=text1.getText().toString();
				String str1=text2.getText().toString();
				String str2=comboBox.getSelectedItem().toString();
				System.out.println(str2);
				if(str.equalsIgnoreCase("mps")&&str1.equals("user")&&str2.equalsIgnoreCase("user"))
				{
				JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULL!!!");
				Home h=new Home();
				h.setVisible(true);
				
				}	
				else if(str.equalsIgnoreCase("mps")&&str1.equals("admin")&&str2.equalsIgnoreCase("admin"))
				{
					SocietyCreation s=new SocietyCreation();
					s.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please check your name and Password","Invalid username/password",JOptionPane.WARNING_MESSAGE);
					text1.setText(" ");
					text2.setText("");
					text1.requestFocus();
				}
			}
			}
		);
		btnNewButton.setBounds(122, 228, 114, 52);
		frmMps.getContentPane().add(btnNewButton);
		
		text2 = new JPasswordField();
		text2.setFont(new Font("Tahoma", Font.BOLD, 18));
		text2.setBounds(258, 115, 280, 32);
		frmMps.getContentPane().add(text2);
		
		JLabel lblNewLabel_2 = new JLabel("SIGN IN");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(296, 0, 101, 28);
		frmMps.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmValue = JOptionPane.showConfirmDialog(null, "Do you really want to exit?");
				//System.out.println(confirmValue);
				if(confirmValue == 0)
				System.exit(0);
				if(confirmValue==1)
					text1.requestFocus();
				if(confirmValue == 2)
					text1.requestFocus();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(424, 228, 114, 52);
		frmMps.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("User Type");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_5.setBounds(103, 160, 145, 28);
		frmMps.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text1.setText(" ");
				text2.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(272, 228, 114, 52);
		frmMps.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/resources/output (8).png")));
		lblNewLabel_3.setBounds(0, 0, 713, 335);
		frmMps.getContentPane().add(lblNewLabel_3);
		
	}
}
