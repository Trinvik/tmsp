package tsmp.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class AdvancePayment extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Address;
	private JTextField adhar;
	private JTextField phone;
	private JTextField aa;
	private JTable table;
	private JComboBox comboBox ;
	private 	JDateChooser DOB ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvancePayment frame = new AdvancePayment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	Statement sta=null;

	/**
	 * Create the frame.
	 */
	public void dropdown()
	{

		try {
			String query="Select * from Member_Creation ";
			PreparedStatement sta=connection.prepareStatement(query);
			
			
			ResultSet rs=sta.executeQuery();
			while(rs.next())
			{
			comboBox.addItem(rs.getString("MemberId"));
			}
			//table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	public AdvancePayment() {
		setResizable(false);
		connection=Dbconnection.dbconnect();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 110, 878, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 366, 423, -17);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("MemberId:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(58, 96, 79, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(88, 139, 49, 19);
		contentPane.add(lblNewLabel_2);
		
		Name = new JTextField();
		Name.setBounds(134, 140, 122, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(75, 185, 62, 19);
		contentPane.add(lblNewLabel_3);
		
		Address = new JTextField();
		Address.setBounds(135, 186, 121, 20);
		contentPane.add(Address);
		Address.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("AadharNo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(62, 227, 71, 19);
		contentPane.add(lblNewLabel_4);
		
		adhar = new JTextField();
		adhar.setBounds(134, 228, 122, 20);
		contentPane.add(adhar);
		adhar.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("PhoneNo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(68, 270, 71, 19);
		contentPane.add(lblNewLabel_5);
		
		phone = new JTextField();
		phone.setBounds(132, 271, 124, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Select * from Member_Creation where MemberId=?";
					PreparedStatement sta=connection.prepareStatement(query);
					sta.setString(1,(String) comboBox .getSelectedItem());
					
					ResultSet rs=sta.executeQuery();
					//table.setModel(DbUtils.resultSetToTableModel(rs));
					while(rs.next())
					{
						Name.setText(rs.getString("Name"));
						Address.setText(rs.getString("Address"));
						adhar.setText(rs.getString("AdharCardNo"));
						phone.setText(rs.getString("PhoneNo"));
					}
								
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		comboBox.setBounds(134, 97, 122, 20);
		contentPane.add(comboBox);
		
		JLabel Advancelabel = new JLabel("AdvanceAmount:");
		Advancelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Advancelabel.setBounds(20, 345, 129, 19);
		contentPane.add(Advancelabel);
		
		aa = new JTextField();
		aa.setBounds(134, 344, 122, 20);
		contentPane.add(aa);
		aa.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 85, 478, 282);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 255, 250));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBounds(354, 77, 498, 298);
		contentPane.add(panel_3);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat  sdf= new SimpleDateFormat("yyyy-MM-dd");
				String Date=sdf.format(DOB.getDate());
				try
				{
					
					
					 String query = "INSERT INTO mps.advancepayment(MemberId,Name,Address,PhoneNumber,Date,Amount) values(?,?,?,?,?,?)";
					 PreparedStatement sta=connection.prepareStatement(query);
						String memberid=comboBox.getSelectedItem().toString();
						sta.setString(1,memberid);
						sta.setString(2, Name.getText());
						sta.setString(3, Address.getText());
						sta.setString(4,phone.getText());
						sta.setString(6,aa.getText());
						sta.setString(5,Date );
						
						sta.execute();
						JOptionPane.showConfirmDialog(null, "Saved Data");
						sta.close();
						Name.setText(" ");
						adhar.setText("");
						phone.setText(" ");
						aa.setText(" ");
						Address.setText(" ");
				}
				catch(SQLException E) {
					JOptionPane.showConfirmDialog(null, "Some thing went Wrong");
					E.printStackTrace();
							
				}
			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(117, 441, 124, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="Select *from mps.advancepayment";
					PreparedStatement sta=connection.prepareStatement(query);
					ResultSet rs=sta.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(318, 441, 115, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from mps.advancepayment where Name='"+Name.getText()+"'";
					PreparedStatement sta=connection.prepareStatement(query);
					sta.execute();
					JOptionPane.showConfirmDialog(null, " Data Deleted");
			
				
					sta.close();
					Name.setText(" ");
					adhar.setText("");
					phone.setText(" ");
					aa.setText(" ");
					Address.setText(" ");
				} catch (Exception E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2.setBounds(516, 441, 107, 36);
		contentPane.add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(245, 245, 245));
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBounds(10, 406, 840, 104);
		contentPane.add(panel_4);
		 DOB = new JDateChooser();
			DOB.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					//DOB.setDateFormatString("dd-MM-yyyy");
					
				}
			});
			DOB.addAncestorListener(new AncestorListener() {
				public void ancestorAdded(AncestorEvent arg0) {
					
				}
				public void ancestorMoved(AncestorEvent arg0) {
				}
				public void ancestorRemoved(AncestorEvent arg0) {
				}
			});
			DOB.setBounds(134, 302, 140, 20);
			contentPane.add(DOB);
		JLabel lblNewLabel_6 = new JLabel("Date:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(88, 301, 46, 19);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(10, 77, 333, 298);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Advance Payment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(268, 0, 320, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon(AdvancePayment.class.getResource("/resources/resize-15840255301587484903MilkImages02103.jpg")));
		lblNewLabel_7.setBounds(0, 0, 872, 531);
		contentPane.add(lblNewLabel_7);
		
		JLabel label = new JLabel("New label");
		label.setBounds(542, 0, 154, 58);
		contentPane.add(label);
		dropdown();
	}
}
