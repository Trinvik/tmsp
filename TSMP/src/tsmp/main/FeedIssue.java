package tsmp.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class FeedIssue extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField AccountNo;
	private JTextField IFSCCode;
	private JTextField bag;
	private JTextField Feed;
	private JTextField TA;
	private JTable table;
	private JComboBox comb;
	private JComboBox PM ;
	private 	JDateChooser DOB ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedIssue frame = new FeedIssue();
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
	public void dropdowncase()
	{

		try {
			String query="Select * from Member_Creation ";
			PreparedStatement sta=connection.prepareStatement(query);
			ResultSet rs=sta.executeQuery();
			while(rs.next())
			{
			comb.addItem(rs.getString("MemberId"));
			}
			//table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public FeedIssue() {
		setResizable(false);
		
		connection=Dbconnection.dbconnect();
		setTitle("MILKMANAGEMENTSYSTEM");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 466);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("MemberId:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(33, 130, 75, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(62, 173, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		Name = new JTextField();
		Name.setBounds(114, 172, 132, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("AccountNo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(33, 215, 75, 14);
		contentPane.add(lblNewLabel_3);
		
		AccountNo = new JTextField();
		AccountNo.setBounds(114, 214, 132, 20);
		contentPane.add(AccountNo);
		AccountNo.setColumns(10);
		
		comb = new JComboBox();
		
		comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String query="Select * from Member_Creation where MemberId=?";
				PreparedStatement sta=connection.prepareStatement(query);
				sta.setString(1,(String) comb .getSelectedItem());
				
				ResultSet rs=sta.executeQuery();
				//table.setModel(DbUtils.resultSetToTableModel(rs));
				while(rs.next())
				{
					Name.setText(rs.getString("NAME"));
					AccountNo.setText(rs.getString("AccountNO"));
					IFSCCode.setText(rs.getString("IFSCCode"));
				}
							
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			}
		});
		comb.setBounds(114, 132, 132, 18);
		contentPane.add(comb);
		
		JLabel lblNewLabel_4 = new JLabel("IFSC Code:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(33, 254, 75, 19);
		contentPane.add(lblNewLabel_4);
		
		IFSCCode = new JTextField();
		IFSCCode.setBounds(114, 255, 132, 20);
		contentPane.add(IFSCCode);
		IFSCCode.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("No.of Bags:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(28, 298, 86, 19);
		contentPane.add(lblNewLabel_5);
		
		bag = new JTextField();
		bag.setBounds(114, 297, 132, 20);
		contentPane.add(bag);
		bag.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("FeedPrice:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(35, 330, 75, 19);
		contentPane.add(lblNewLabel_6);
		
		Feed = new JTextField();
		Feed.setBounds(114, 331, 132, 20);
		contentPane.add(Feed);
		Feed.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Total Amount:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(15, 362, 95, 26);
		contentPane.add(lblNewLabel_7);
		
		TA = new JTextField();
		TA.setBounds(114, 367, 132, 20);
		contentPane.add(TA);
		TA.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat  sdf= new SimpleDateFormat("yyyy-MM-dd");
				String Date=sdf.format(DOB.getDate());
				try
				{
					
					
					 String query = "INSERT INTO mps.feedissue(Date,MemberId,Name,AccountNo,IFSCCode,Packets,Feedprice,TotalAmount,Paymentmode) values(?,?,?,?,?,?,?,?,?)";
					 PreparedStatement sta=connection.prepareStatement(query);
				
						//sta.setString(12,Da);
					 	sta.setString(1,Date );
						String memberid=comb.getSelectedItem().toString();
						sta.setString(2,memberid);
						sta.setString(3, Name.getText());
						sta.setString(4, AccountNo.getText());
						sta.setString(5,IFSCCode.getText());
						sta.setString(6,bag.getText());
						sta.setString(7,Feed.getText());
						sta.setString(8,TA.getText());
						String Payment=PM.getSelectedItem().toString();
						sta.setString(9,Payment);
						
					
						sta.execute();
						JOptionPane.showConfirmDialog(null, "Saved Data");
						sta.close();
						Name.setText(" ");
						AccountNo.setText("");
						IFSCCode.setText(" ");
						bag.setText(" ");
						Feed.setText(" ");
						TA.setText(" ");
					
				}
				catch(Exception E) {
					JOptionPane.showConfirmDialog(null, "Some thing went Wrong");
					E.printStackTrace();
							
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(630, 371, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 88, 614, 272);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_8 = new JLabel("PaymentMode:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(10, 398, 105, 26);
		contentPane.add(lblNewLabel_8);
		
		PM = new JComboBox();
		PM.addItem("CASH");
		PM.addItem("LOAN");
		PM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PM.setBounds(114, 405, 132, 21);
		contentPane.add(PM);
		
		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Select *from mps.feedissue";
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
		btnNewButton_1.setBounds(472, 371, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double b= Double.parseDouble(bag.getText());
				Double f= Double.parseDouble(Feed.getText());
				
				String a=String.format("%.2f",(b*f ));
				TA.setText(a);
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2.setBounds(306, 371, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query="delete from mps.feedissue where Name='"+Name.getText()+"'";
					PreparedStatement sta=connection.prepareStatement(query);
					sta.execute();
					JOptionPane.showConfirmDialog(null, " Data Deleted");
					sta.close();
					Name.setText(" ");
					AccountNo.setText("");
					IFSCCode.setText(" ");
					bag.setText(" ");
					Feed.setText(" ");
					TA.setText(" ");
				} catch (Exception E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_3.setBounds(774, 371, 89, 23);
		contentPane.add(btnNewButton_3);
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
			DOB.setBounds(114, 88, 140, 20);
			contentPane.add(DOB);
		
		JLabel lblNewLabel_10 = new JLabel("Date:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(68, 88, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel = new JLabel("Feed Issue");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(449, 11, 171, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon(FeedIssue.class.getResource("/resources/resize-1584026781174196280MilkWallpapers02110.jpg")));
		lblNewLabel_9.setBounds(0, 0, 894, 437);
		contentPane.add(lblNewLabel_9);
		
		
		dropdowncase();
		
		
	
	}
}
