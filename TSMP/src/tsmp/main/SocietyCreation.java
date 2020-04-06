package tsmp.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class SocietyCreation extends JFrame {
	private JTextField Name;
	private JTextField Place;
	private JTextField Date;
	private JTextField Village;
	private JTextField Taluk;
	private JTextField Code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocietyCreation frame = new SocietyCreation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	/**
	 * Create the frame.
	 */
	public SocietyCreation() {
		connection=Dbconnection.dbconnect();
		setResizable(false);
		setTitle("MILKMANAGEMENTSYSTEM");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 505);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Society Creation");
		lblNewLabel.setBackground(new Color(204, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 0, 179, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Society Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 102, 95, 24);
		contentPane.add(lblNewLabel_1);
		
		Name = new JTextField();
		Name.setBounds(106, 106, 161, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlace.setBounds(62, 149, 43, 24);
		contentPane.add(lblPlace);
		
		Place = new JTextField();
		Place.setBounds(106, 153, 161, 20);
		contentPane.add(Place);
		Place.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Created Date:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 208, 95, 24);
		contentPane.add(lblNewLabel_2);
		
		Date = new JTextField();
		Date.setBounds(106, 208, 161, 20);
		contentPane.add(Date);
		Date.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Village:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(55, 258, 65, 24);
		contentPane.add(lblNewLabel_3);
		
		Village = new JTextField();
		Village.setBounds(106, 262, 161, 20);
		contentPane.add(Village);
		Village.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					 String query = "INSERT INTO mps.`society creation`(SocietyName,Place,CreatedDate,Village,Taluk,PinCode) values(?,?,?,?,?,?)";
					 PreparedStatement sta=connection.prepareStatement(query);
				
						sta.setString(1,Name.getText());
						sta.setString(2,Place.getText());
						sta.setString(3,Date.getText());
						sta.setString(4,Village.getText());
						sta.setString(5,Taluk.getText());
						sta.setString(6,Code.getText());
						sta.execute();
						JOptionPane.showConfirmDialog(null, "Saved Data");
						sta.close();
						Home h=new Home();
						h.setVisible(true);
				}
				catch(Exception E) {
					JOptionPane.showConfirmDialog(null, "Some thing went Wrong ...please check Date Format yyyy-mm-dd");
					E.printStackTrace();
							
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(139, 419, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Taluk:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(55, 307, 58, 24);
		contentPane.add(lblNewLabel_5);
		
		Taluk = new JTextField();
		Taluk.setBounds(106, 311, 161, 20);
		contentPane.add(Taluk);
		Taluk.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PinCode:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(40, 356, 65, 24);
		contentPane.add(lblNewLabel_4);
		
		Code = new JTextField();
		Code.setBounds(106, 360, 161, 20);
		contentPane.add(Code);
		Code.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(275, 0, 504, 465);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon(SocietyCreation.class.getResource("/resources/q.jpg")));
		lblNewLabel_7.setBounds(270, 0, 509, 465);
		contentPane.add(lblNewLabel_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 27, 179, 2);
		contentPane.add(separator);
	}
}
