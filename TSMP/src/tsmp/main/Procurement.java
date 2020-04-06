package tsmp.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class Procurement extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Litre;
	private JTextField FAT;
	private JTextField SNF;
	private JTable table;
	private JTextField TotalAmount;
	private JComboBox comboBox;
	private 	JDateChooser DOB ;
	/**
	 * Launch the application.
	 */
	
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procurement frame = new Procurement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	Statement sta=null;
	private JTextField AccountNo;
	private JTextField IFSCCode;
	private JTextField Litrep;
	private JTextField CLR;
	private JTextField textField;
	


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
	public Procurement() {
		setResizable(false);
		connection=Dbconnection.dbconnect();
		setFont(new Font("Dialog", Font.PLAIN, 15));
		setTitle("MILKMANAGEMENTSYSTEM");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 672);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MemberId:");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 52, 83, 14);
		contentPane.add(lblNewLabel);
		
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
						AccountNo.setText(rs.getString("AccountNO"));
						IFSCCode.setText(rs.getString("IFSCCode"));
					}
								
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//comboBox.addItem("SELECT......");
		comboBox.setBounds(95, 51, 131, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("NAME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(273, 52, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		Name = new JTextField();
		Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Name.setBounds(317, 51, 131, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SESSION:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(488, 52, 77, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		
		comboBox_1.addItem("EVENING");
		comboBox_1.addItem("MORNING");
		comboBox_1.setBounds(563, 51, 123, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Cattle Type:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(467, 86, 100, 32);
		contentPane.add(lblNewLabel_4);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		
		comboBox_2.addItem("COW");
		comboBox_2.addItem("BUFFALO");
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox_2.setBounds(563, 94, 123, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblNewLabel_5 = new JLabel("Litre:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(52, 209, 37, 14);
		contentPane.add(lblNewLabel_5);
		
		Litre = new JTextField();
		Litre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Litre.setBounds(95, 208, 131, 20);
		contentPane.add(Litre);
		Litre.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fat:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(63, 253, 34, 14);
		contentPane.add(lblNewLabel_7);
		
		FAT = new JTextField();
		FAT.setBounds(95, 247, 131, 20);
		contentPane.add(FAT);
		FAT.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("SNF:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(317, 248, 34, 14);
		contentPane.add(lblNewLabel_8);
		
		SNF = new JTextField();
		SNF.setBounds(370, 247, 112, 20);
		contentPane.add(SNF);
		SNF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		scrollPane.setBounds(0, 340, 879, 293);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_13 = new JLabel("TotalAmount:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(258, 291, 93, 14);
		contentPane.add(lblNewLabel_13);
		
		TotalAmount = new JTextField();
		TotalAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
		TotalAmount.setBounds(370, 290, 112, 20);
		contentPane.add(TotalAmount);
		TotalAmount.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("MILK COLLECTION");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_9.setBounds(307, 0, 297, 30);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_3 = new JLabel("AccountNo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 92, 83, 20);
		contentPane.add(lblNewLabel_3);
		
		AccountNo = new JTextField();
		AccountNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AccountNo.setBounds(95, 94, 131, 20);
		contentPane.add(AccountNo);
		AccountNo.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("IFSC Code:");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_19.setBounds(242, 97, 77, 14);
		contentPane.add(lblNewLabel_19);
		
		IFSCCode = new JTextField();
		IFSCCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		IFSCCode.setBounds(317, 94, 131, 20);
		contentPane.add(IFSCCode);
		IFSCCode.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Price/Litre");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_20.setBounds(273, 200, 93, 32);
		contentPane.add(lblNewLabel_20);
		
		Litrep = new JTextField();
		Litrep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		Litrep.setBounds(369, 208, 113, 20);
		contentPane.add(Litrep);
		Litrep.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("CLR:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(52, 291, 34, 14);
		contentPane.add(lblNewLabel_6);
		
		CLR = new JTextField();
		CLR.setBounds(95, 290, 131, 20);
		contentPane.add(CLR);
		CLR.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float L= Float.parseFloat(Litre.getText());
				float Lp = Float.parseFloat(Litrep.getText());
				float f = Float.parseFloat(FAT.getText());
				float s = Float.parseFloat(SNF.getText());
				float c = Float.parseFloat(CLR.getText());
				double snf=((c/4)+0.25*(f/100)+0.35);
				String a=String.format("%.2f",( L*Lp)+(f+snf+c));
				TotalAmount.setText(a);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(515, 205, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat  sdf= new SimpleDateFormat("yyyy-MM-dd");
				String Date=sdf.format(DOB.getDate());
				try
				{
					
					
					 String query = "INSERT INTO milk_collection(Date,MemberId,Name,AccountNo,IFSCCode,Session,Cattletype,SNF,FAT,CLR,NOOfLitre,TotalAmount) values(?,?,?,?,?,?,?,?,?,?,?,?)";
					 PreparedStatement sta=connection.prepareStatement(query);
				
						//sta.setString(12,Da);
					 	sta.setString(1,Date );
						String memberid=comboBox.getSelectedItem().toString();
						sta.setString(2,memberid);
						sta.setString(3, Name.getText());
						sta.setString(4, AccountNo.getText());
						sta.setString(5,IFSCCode.getText());
						String session=comboBox_1.getSelectedItem().toString();
						sta.setString(6,session);
						String cattle=comboBox_2.getSelectedItem().toString();
						sta.setString(7,cattle);
						sta.setString(8,SNF.getText());
						sta.setString(9,FAT.getText());
						sta.setString(10,CLR.getText());
						sta.setString(11,Litre .getText());
						sta.setString(12,TotalAmount.getText());
						//sta.setString(1,Date );
						sta.execute();
						JOptionPane.showConfirmDialog(null, "Saved Data");
						sta.close();
						Name.setText(" ");
						AccountNo.setText("");
						IFSCCode.setText(" ");
						SNF.setText(" ");
						FAT.setText(" ");
						CLR.setText(" ");
						Litrep.setText(" ");
						TotalAmount.setText("");
						
				}
				catch(SQLException E) {
					JOptionPane.showConfirmDialog(null, "Something went Wrong");
					E.printStackTrace();
							
				}
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(515, 246, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Select Sl_No,Date ,MemberId,Name,AccountNo,IFSCCode,Session,Cattletype,SNF,FAT,CLR,NOOfLitre,TotalAmount from milk_collection ";
					PreparedStatement sta=connection.prepareStatement(query);
					ResultSet rs=sta.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(515, 287, 89, 23);
		contentPane.add(btnNewButton_2);
		
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
			DOB.setBounds(95, 125, 140, 20);
			contentPane.add(DOB);
		
		JLabel lblNewLabel_10 = new JLabel("Collecton Details");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_10.setBounds(24, 155, 140, 14);
		contentPane.add(lblNewLabel_10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 172, 606, 157);
		contentPane.add(panel);
		
		JLabel lblNewLabel_12 = new JLabel("Date:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(52, 125, 46, 20);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setIcon(new ImageIcon(Procurement.class.getResource("/resources/resize-1584025695232559216MilkPics02106.jpg")));
		lblNewLabel_11.setBounds(0, 0, 889, 643);
		contentPane.add(lblNewLabel_11);
		
		
		
		dropdown();
	
	}
}
