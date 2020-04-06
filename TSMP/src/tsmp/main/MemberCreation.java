package tsmp.main;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import tsmp.db.DbConnection;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class MemberCreation extends JFrame {
	private JPanel contentPane;
	private JTextField memberID;
	private JTextField address;
	private JPanel panel;
	private JTextField memberName;
	private JTextField bankName;
	private JTextField phoneNo;
	private JTextField accountNo;
	private DecimalFormat df;

	MemberCreation memberCreationFrame = this;
	private DbConnection db;
	private PreparedStatement pstmt;
	

	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private Vector<String> memberList;
	private Connection connection;
	private JLabel lblMemberid;
	private JLabel lblNomineename;
	private JLabel lblPanCardNo;
	private JLabel lblMemberName;
	private JLabel lblAddress;
	private JLabel lblPhoneNo;
	private JLabel lblBankName;
	private JLabel lblAccountno;
	private JLabel lblIfscCode;
	private JLabel lblAadharno;
	private JLabel lblGender;
	private JLabel lblDob;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JPanel panel_1;
	private JTextField ifsccode;
	private JTextField nomineeName;
	private JTextField adhaarNo;
	private JTextField panCardNo;
	private JButton btnClose;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSave;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberCreation frame = new MemberCreation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void insertmembercreation() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		db = new DbConnection();
		String sqlQuery = "INSERT INTO membercreation " +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"; 
		
		try {
			db.connect();
			pstmt = db.pstmtExeUpdate(sqlQuery);
			
			pstmt.setString(1, memberID.getText().toString());
			pstmt.setString(2, memberName.getText().toString());
			pstmt.setString(3, comboBox.getSelectedItem().toString());

			String DateOfBirth=d.format(dateChooser.getDate());
			
			pstmt.setString(4, DateOfBirth);
			pstmt.setString(5, address.getText().toString());
			pstmt.setString(6, phoneNo.getText().toString());
			pstmt.setString(7, bankName.getText().toString());
			pstmt.setString(8, accountNo.getText().toString());
			pstmt.setString(9, ifsccode.getText().toString());
			pstmt.setString(10, nomineeName.getText().toString());
			pstmt.setString(11, adhaarNo.getText().toString());
			pstmt.setString(12, panCardNo.getText().toString());
			
			int count = 0;
			//validation
			if(!(memberID.getText().toString().isEmpty()) || !(ifsccode.getText().toString().isEmpty()))
			{
				count = pstmt.executeUpdate();
				db.commitAll();
				db.close();
			}
			else{
				JOptionPane.showMessageDialog(contentPane,
						"Empty Bank ID or Bank Name!!!!", "Please check Fields",
						JOptionPane.INFORMATION_MESSAGE);
				if(memberID.getText().toString().isEmpty())
					memberID.requestFocus();
				if(ifsccode.getText().toString().isEmpty())
					memberName.requestFocus();
			}
			if(count>0)
			JOptionPane.showMessageDialog(contentPane,
					"Success!!!", "Done",
					JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(contentPane, "Already Entered!!!",
						"ERROR", JOptionPane.ERROR_MESSAGE);
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Something wrong!!!",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(contentPane, "UnSuccessfull!!!",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	private void resetAll() {
		memberID.setText("");
		memberName.setText("");
		address.setText("");
		phoneNo.setText("");
		bankName.setText("");
		accountNo.setText("");
		ifsccode.setText("");
		nomineeName.setText("");
		adhaarNo.setText("");
		panCardNo.setText("");
		memberID.requestFocus();
	}
	/**
	 * Create the frame.
	 */
	public MemberCreation() {
		setResizable(false);
		df = new DecimalFormat("###.##");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*setBounds(0, 0, getToolkit().getScreenSize().width,
                getToolkit().getScreenSize().height);*/
		setBounds(0,0,1188,722);
		
        setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Action enterAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KeyboardFocusManager manager = KeyboardFocusManager
						.getCurrentKeyboardFocusManager();
				manager.getFocusOwner().transferFocus();
			}
		};
		
		panel = new JPanel();
		panel.setBounds(4, 36, 438, 554);
		panel.setForeground(new Color(51, 0, 102));
		panel.setBackground(new Color(255, 102, 0));
		panel.setBorder(null);
		panel.setLayout(null);
		
		memberID = new JTextField();
		memberID.setBounds(204, 11, 228, 33);
		memberID.setBackground(new Color(255, 255, 255));
		memberID.setFont(new Font("Dialog", Font.BOLD, 18));
		memberID.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
            	if((memberID.getText().toString().isEmpty() == false))
				{
				DbConnection db = new DbConnection();
				String sqlQuery = "SELECT * from membercreation where memberID = '" + memberID.getText().toString() + "'";
				System.out.println(sqlQuery);
				try {
					db.connect();
					ResultSet rs =  db.exeSQL(sqlQuery);
					if(rs.next()){
						//memberID.setText("");
						memberName.setText(rs.getString(2));
						address.setText(rs.getString(5));
						phoneNo.setText(rs.getString(6));
						bankName.setText(rs.getString(7));
						accountNo.setText(rs.getString(8));
						ifsccode.setText(rs.getString(9));
						nomineeName.setText(rs.getString(10));
						adhaarNo.setText(rs.getString(11));
						panCardNo.setText(rs.getString(12));
					}
					else
					{
						memberName.setText("");
						address.setText("");
						phoneNo.setText("");
						bankName.setText("");
						accountNo.setText("");
						ifsccode.setText("");
						nomineeName.setText("");
						adhaarNo.setText("");
						panCardNo.setText("");
						memberID.requestFocus();
					}
					db.close();
				}					
				catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "No Data for Entered member Number",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "Something Wrong",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
				else
				{
					memberName.setText("");
					address.setText("");
					phoneNo.setText("");
					bankName.setText("");
					accountNo.setText("");
					ifsccode.setText("");
					nomineeName.setText("");
					adhaarNo.setText("");
					panCardNo.setText("");
					memberID.requestFocus();
				}
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	if((memberID.getText().toString().isEmpty() == false))
				{
				DbConnection db = new DbConnection();
				String sqlQuery = "SELECT * from membercreation where memberID = '" + memberID.getText().toString() + "'";
				System.out.println(sqlQuery);
				try {
					db.connect();
					ResultSet rs =  db.exeSQL(sqlQuery);
					if(rs.next()){
						//memberID.setText("");
						memberName.setText(rs.getString(2));
						address.setText(rs.getString(5));
						phoneNo.setText(rs.getString(6));
						bankName.setText(rs.getString(7));
						accountNo.setText(rs.getString(8));
						ifsccode.setText(rs.getString(9));
						nomineeName.setText(rs.getString(10));
						adhaarNo.setText(rs.getString(11));
						panCardNo.setText(rs.getString(12));
					}
					else
					{
						memberName.setText("");
						address.setText("");
						phoneNo.setText("");
						bankName.setText("");
						accountNo.setText("");
						ifsccode.setText("");
						nomineeName.setText("");
						adhaarNo.setText("");
						panCardNo.setText("");
						memberID.requestFocus();
					}
					db.close();
				}					
				catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "No Data for Entered member Number",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "Something Wrong",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
				else
				{
					memberName.setText("");
					address.setText("");
					phoneNo.setText("");
					bankName.setText("");
					accountNo.setText("");
					ifsccode.setText("");
					nomineeName.setText("");
					adhaarNo.setText("");
					panCardNo.setText("");
					memberID.requestFocus();
				}
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            	if((memberID.getText().toString().isEmpty() == false))
				{
				DbConnection db = new DbConnection();
				String sqlQuery = "SELECT * from membercreation where memberID = '" + memberID.getText().toString() + "'";
				System.out.println(sqlQuery);
				try {
					db.connect();
					ResultSet rs =  db.exeSQL(sqlQuery);
					if(rs.next()){
						//memberID.setText("");
						memberName.setText(rs.getString(2));
						address.setText(rs.getString(5));
						phoneNo.setText(rs.getString(6));
						bankName.setText(rs.getString(7));
						accountNo.setText(rs.getString(8));
						ifsccode.setText(rs.getString(9));
						nomineeName.setText(rs.getString(10));
						adhaarNo.setText(rs.getString(11));
						panCardNo.setText(rs.getString(12));
					}
					else
					{
						memberName.setText("");
						address.setText("");
						phoneNo.setText("");
						bankName.setText("");
						accountNo.setText("");
						ifsccode.setText("");
						nomineeName.setText("");
						adhaarNo.setText("");
						panCardNo.setText("");
						memberID.requestFocus();
					}
					db.close();
				}					
				catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "No Data for Entered member Number",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "Something Wrong",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
				else
				{
					memberName.setText("");
					address.setText("");
					phoneNo.setText("");
					bankName.setText("");
					accountNo.setText("");
					ifsccode.setText("");
					nomineeName.setText("");
					adhaarNo.setText("");
					panCardNo.setText("");
					memberID.requestFocus();
				}
            }
        });
		panel.add(memberID);
		memberID.setColumns(10);
		
		memberName = new JTextField();
		memberName.setFont(new Font("Dialog", Font.BOLD, 18));
		memberName.setBounds(204, 55, 228, 33);
		panel.add(memberName);
		
		address = new JTextField();
		address.setBounds(204, 188, 228, 33);
		address.setBackground(new Color(255, 255, 255));
		address.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(address);
		address.setColumns(10);
		
		phoneNo = new JTextField();
		phoneNo.setFont(new Font("Dialog", Font.BOLD, 18));
		phoneNo.setBounds(204, 232, 228, 33);
		phoneNo.setColumns(10);
		panel.add(phoneNo);
		
		bankName = new JTextField();
		bankName.setBounds(204, 278, 228, 33);
		bankName.setBackground(new Color(255, 255, 255));
		bankName.setFont(new Font("Dialog", Font.BOLD, 18));
		bankName.setColumns(10);
		panel.add(bankName);
		
		accountNo = new JTextField();
		accountNo.setBounds(204, 322, 228, 33);
		accountNo.setBackground(new Color(255, 255, 255));
		accountNo.setFont(new Font("Dialog", Font.BOLD, 18));
		accountNo.setColumns(10);
		panel.add(accountNo);
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		lblMemberid = new JLabel("MemberId");
		lblMemberid.setBounds(111, 10, 83, 33);
		panel.add(lblMemberid);
		lblMemberid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNomineename = new JLabel("NomineeName");
		lblNomineename.setBounds(93, 415, 101, 19);
		panel.add(lblNomineename);
		lblNomineename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblPanCardNo = new JLabel("PAN Card No");
		lblPanCardNo.setBounds(111, 503, 97, 19);
		panel.add(lblPanCardNo);
		lblPanCardNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMemberName = new JLabel("Member Name");
		lblMemberName.setBounds(85, 60, 109, 24);
		panel.add(lblMemberName);
		lblMemberName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(126, 193, 68, 24);
		panel.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setBounds(126, 242, 68, 14);
		panel.add(lblPhoneNo);
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblBankName = new JLabel("Bank Name");
		lblBankName.setBounds(105, 285, 89, 20);
		panel.add(lblBankName);
		lblBankName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblAccountno = new JLabel("AccountNo");
		lblAccountno.setBounds(105, 332, 89, 14);
		panel.add(lblAccountno);
		lblAccountno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblIfscCode = new JLabel("IFSC Code");
		lblIfscCode.setBounds(105, 375, 83, 17);
		panel.add(lblIfscCode);
		lblIfscCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblAadharno = new JLabel("AadharNo");
		lblAadharno.setBounds(125, 457, 68, 22);
		panel.add(lblAadharno);
		lblAadharno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(126, 105, 68, 25);
		panel.add(lblGender);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(204, 99, 228, 35);
		panel.add(comboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(204, 145, 228, 32);
		panel.add(dateChooser);
		
		lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDob.setBounds(126, 145, 74, 30);
		panel.add(lblDob);
		
		ifsccode = new JTextField();
		ifsccode.setFont(new Font("Dialog", Font.BOLD, 18));
		ifsccode.setColumns(10);
		ifsccode.setBackground(Color.WHITE);
		ifsccode.setBounds(204, 366, 228, 33);
		panel.add(ifsccode);
		
		nomineeName = new JTextField();
		nomineeName.setFont(new Font("Dialog", Font.BOLD, 18));
		nomineeName.setColumns(10);
		nomineeName.setBackground(Color.WHITE);
		nomineeName.setBounds(204, 410, 228, 33);
		panel.add(nomineeName);
		
		adhaarNo = new JTextField();
		adhaarNo.setFont(new Font("Dialog", Font.BOLD, 18));
		adhaarNo.setColumns(10);
		adhaarNo.setBackground(Color.WHITE);
		adhaarNo.setBounds(204, 451, 228, 33);
		panel.add(adhaarNo);
		
		panCardNo = new JTextField();
		panCardNo.setFont(new Font("Dialog", Font.BOLD, 18));
		panCardNo.setColumns(10);
		panCardNo.setBackground(Color.WHITE);
		panCardNo.setBounds(204, 495, 228, 33);
		panel.add(panCardNo);
		
		String[] columnNames = {"Sl No","Member Number","Member Name","Bank Name","Account No"};
		Object[][] data = null;	
		tableModel = new DefaultTableModel(data, columnNames);
		contentPane.setLayout(null);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setBackground(new Color(250, 250, 210));
		scrollPane = new JScrollPane();
		scrollPane.setBounds(452, 36, 724, 554);
		scrollPane.setViewportView(table);
		table.setRequestFocusEnabled(false);
		table.setRowHeight(24);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setAutoCreateRowSorter(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		 // right align 4th column
	     /* TableColumnModel columnModel = table.getColumnModel();
	      TableColumn column = columnModel.getColumn(4); 
	      DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	      renderer.setHorizontalAlignment(JLabel.RIGHT);
	      column.setCellRenderer(renderer);*/
	      
		scrollPane.setFont(new Font("Calibri", Font.BOLD, 20));
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane);
		
		JLabel label_3 = new JLabel("MEMBER MASTER");
		label_3.setForeground(new Color(255, 255, 153));
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		label_3.setBounds(351, 0, 212, 25);
		contentPane.add(label_3);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(178, 34, 34));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Member ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(4, 601, 1172, 64);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnSave = new JButton("SAVE");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try {
					insertmembercreation();
					displaymembercreation();
					resetAll();
				}
				catch(NumberFormatException ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,
							"Enter the Valid Details", "Wrong field!!!",
							JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertmembercreation();
					displaymembercreation();
					resetAll();
				}
				catch(NumberFormatException ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,
							"Enter the Valid Details", "Wrong field!!!",
							JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSave.setBounds(62, 16, 115, 41);
		panel_1.add(btnSave);
		
		btnUpdate = new JButton("CHANGE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if((memberID.getText().toString().isEmpty() == false))
					{
					int confirm = JOptionPane.showConfirmDialog(memberCreationFrame, "Are you sure you want to Change?", "Change", JOptionPane.YES_NO_OPTION);
					System.out.println("CONFIRM Value is : " + confirm);
					if(confirm==0)
					{
					
	            		DbConnection db = new DbConnection();
	    				String sqlQuery = "DELETE from membercreation where memberID = '" + memberID.getText().toString() + "'";
	    				System.out.println(sqlQuery);
	    				try {
	    					db.connect();
	    					db.exeUpdate(sqlQuery);
	    					insertmembercreation();
	    					displaymembercreation();
	    					db.close();
	    					resetAll();
	    					displaymembercreation();
					}					
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(contentPane, "No Data for Entered Member ID",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
					}
					else
					{
						JOptionPane.showMessageDialog(contentPane, "Please enter the Member ID",
								"No Value Found", JOptionPane.WARNING_MESSAGE);
						memberID.requestFocus();
					}
				}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnUpdate.setBounds(360, 16, 115, 41);
		panel_1.add(btnUpdate);
		
		btnDelete = new JButton("REMOVE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Delete the displayed record
				if((memberID.getText().toString().isEmpty() == false))
				{
				int confirm = JOptionPane.showConfirmDialog(memberCreationFrame, "Are you sure you want to remove?", "REMOVE", JOptionPane.YES_NO_OPTION);
				System.out.println("CONFIRM Value is : " + confirm);
				if(confirm==0)
				{
				
            		DbConnection db = new DbConnection();
    				String sqlQuery = "DELETE from membercreation where memberID = '" + memberID.getText().toString() + "'";
    				System.out.println(sqlQuery);
    				try {
    					db.connect();
    					db.exeUpdate(sqlQuery);
    					db.close();
    					JOptionPane.showMessageDialog(contentPane, "Deleted Successfully!!!",
    								"DONE", JOptionPane.PLAIN_MESSAGE);
    					resetAll();
    					displaymembercreation();
				}					
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, "No Data for Entered customer Number",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "Please enter the Member ID to delete!!!",
							"No Value Found", JOptionPane.WARNING_MESSAGE);
					memberID.requestFocus();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnDelete.setBounds(674, 16, 117, 41);
		panel_1.add(btnDelete);
		
		btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberCreationFrame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnClose.setBounds(963, 16, 115, 41);
		panel_1.add(btnClose);
		
		memberID.addActionListener(enterAction);
		memberName.addActionListener(enterAction);
		address.addActionListener(enterAction);
		phoneNo.addActionListener(enterAction);
		bankName.addActionListener(enterAction);
		accountNo.addActionListener(enterAction);
		ifsccode.addActionListener(enterAction);
		adhaarNo.addActionListener(enterAction);
		panCardNo.addActionListener(enterAction);
		nomineeName.addActionListener(enterAction);
		
		displaymembercreation();
	}

	private void displaymembercreation() {
		try {
			db = new DbConnection();
			db.connect();
			String chartDates = "SELECT memberID,membername,memberbankname,memberaccountno FROM membercreation ORDER BY CAST(memberid AS DECIMAL)";
			ResultSet rs = db.exeSQL(chartDates);
			int count = 1;
			tableModel.setRowCount(0);
			while (rs.next()) {
				memberList = new Vector<String>();
				memberList.addElement(count + "");
				memberList.addElement(rs.getString(1));
				memberList.addElement(rs.getString(2));
				memberList.addElement(rs.getString(3));
				memberList.addElement(rs.getString(4));
				tableModel.addRow(memberList);
				count++;
			}
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(tableModel.getRowCount()>0){
		// Select Last Row always
		int rowcount = table.convertRowIndexToView(tableModel.getRowCount() - 1);
		table.setRowSelectionInterval(rowcount, rowcount);

		// table.changeSelection(table.getColumnCount()-1, 0, false, false);
		table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0,true));
	}
	}
	public Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/mps?user=root&password=root");
		return connection;
	}
}
