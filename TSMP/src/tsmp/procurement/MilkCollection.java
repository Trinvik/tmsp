package tsmp.procurement;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.JXDatePicker;

import tsmp.db.DbConnection;


public class MilkCollection extends JFrame {

	private JPanel contentPane;
	private JTextField txtMemberID;
	private JTextField textField_2;
	private static JTextField txtFat;
	private static JTextField txtSNF;
	private JTextField txtCLR;
	public static JTextField txtQuantity;
	private JTextField txtMemberName;
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JXDatePicker datePicker;
	//protected DbConnection db1;
	private JLabel lblMemberPhoto;
	
	//For Member Photo
	Image image = null;
	Image scaleImage = null;
	String photoPath = "";
	String imagePath = "";
	public static JButton btnSave;
	private JLabel lblTime;
	//protected DbConnection dbfat;
	//private DbConnection dbs;
	protected String newRate = null;
	public int serialNumber = 0;
	private SimpleDateFormat d;
	protected DbConnection db;
	protected Vector<String> rowInTable;
	static double cowCanNumber = 0;
	static double bufCanNumber = 0;
	float currentProgressOfCow,currentProgressOfBuf;
	private DecimalFormat decimalFormatForSingleDigit;
	protected BigDecimal bigDecimalForCowCan;
	protected BigDecimal bigDecimalForBufCan;
	
	private JButton btnClear;
	protected DecimalFormat decimalFormatForDoubleDigit;
	private Connection connection;
	String messageForPrinting = "";
	private JButton btnReport;
	private String senderNumber;
	private String senderName;
	private String apikey;
	private String smsHeading;
	
	public DbConnection dbConnection;
	protected String isBothAnimal = "NO";
	
	boolean flag_qty = false;
	double tempQty;
	private JTextField txtPricePerLitre;
	private JComboBox cattleComboBox;
	private JTextField txtTotalAmount;
	private JComboBox shiftComboBox;
	private JCheckBox chckbxSms;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MilkCollection frame = new MilkCollection();
					frame.setVisible(true);
					//frame.txtMemberID.requestFocus(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MilkCollection() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		/*SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceAquaTheme");
		SubstanceLookAndFeel
				.setCurrentButtonShaper("org.jvnet.substance.button.StandardButtonShaper");
		SubstanceLookAndFeel
				.setCurrentGradientPainter("SpecularGradientPainter");
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
*/		
		//Common Connection for Select Queries under the MilkCollection (updated on 11.08.2018)
		try {
			dbConnection = new DbConnection();
			dbConnection.connect();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		Action enterAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KeyboardFocusManager manager = KeyboardFocusManager
						.getCurrentKeyboardFocusManager();
				manager.getFocusOwner().transferFocus();
			}
		};
		
		d = new SimpleDateFormat("yyyy-MM-dd");
		decimalFormatForSingleDigit = new DecimalFormat("##.#");
		decimalFormatForDoubleDigit = new DecimalFormat("###.##");
		setBounds(0, 0, 1032, 723);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 36, 1001, 234);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Milk_Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 204, 0));
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(10, 23, 398, 182);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblzas = new JLabel("Member ID");
		lblzas.setBounds(27, 99, 116, 35);
		panel_1.add(lblzas);
		lblzas.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblzas.setHorizontalAlignment(SwingConstants.CENTER);
		lblzas.setForeground(new Color(0, 0, 205));
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(153, 3, 236, 35);
		panel_1.add(datePicker);
		datePicker.getEditor().setEditable(false);
		datePicker.getEditor().setForeground(Color.BLUE);
		datePicker.getEditor().setBackground(new Color(255, 245, 238));
		datePicker.setFont(new Font("Calibri", Font.BOLD, 20));
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		
		txtMemberID = new JTextField();
		txtMemberID.setBounds(153, 99, 236, 35);
		panel_1.add(txtMemberID);
		
		txtMemberID.getDocument().addDocumentListener(
				new DocumentListener() {
					private DbConnection dbDetails;
					@Override
					public void insertUpdate(DocumentEvent de) {
						try {
							if (txtMemberID.getText() == null
									|| txtMemberID.getText().equals("")) {
								txtMemberID.requestFocus();
								return;
							} else {
								String getMemberName = "SELECT COALESCE(membername,' ') FROM membercreation WHERE memberid="
										+ txtMemberID.getText().toString();
								//System.out.println(getMemberName);
								/*db1 = new DbConnection();
								db1.connect();*/
								ResultSet rs1 = dbConnection.exeSQL(getMemberName);
								if (rs1.next()) {
									txtMemberName.setText(rs1.getString(1));
									txtMemberName.setEditable(false);
								} else {
									txtMemberName.setText("");
									}
							}
						} catch (Exception e1) {
							e1.getMessage();
						}
						
						//Display the details from milkcollection for the entered member if the details already exists
						try{
						String str1 = "SELECT clr,snf,quantity,priceperlitre,total_amount,fat FROM milkcollection WHERE " +
								"member_ID = "
								+ txtMemberID.getText().toString()
								+ " and collection_date = '"
								+ d.format(datePicker.getDate())
								+ "' and cattle_name = '"
								+ cattleComboBox.getSelectedItem().toString()
								+ "' and shift = '"
								+ shiftComboBox.getSelectedItem().toString()
								+ "'";
						ResultSet rsDetails = dbConnection
								.exeSQL(str1);
						if (rsDetails.next()) {
							txtCLR.setText(rsDetails.getString(1));
							txtSNF.setText(rsDetails.getString(2));
							txtQuantity.setText(rsDetails.getString(3));
							txtPricePerLitre.setText(rsDetails.getString(4));
							txtTotalAmount.setText(rsDetails.getString(5));
							txtFat.setText(rsDetails.getString(6));
						}
						 else {
							 	txtCLR.setText("");
								txtSNF.setText("");
								txtQuantity.setText("");
								txtPricePerLitre.setText("");
								txtTotalAmount.setText("");
								txtFat.setText("");
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void removeUpdate(DocumentEvent de) {
						try {
							if (txtMemberID.getText() == null
									|| txtMemberID.getText().equals("")) {
								txtMemberID.requestFocus();
								return;
							} else {
								String getMemberName = "SELECT COALESCE(membername,' ') FROM membercreation WHERE memberid="
										+ txtMemberID.getText().toString();
								//System.out.println(getMemberName);
								/*db1 = new DbConnection();
								db1.connect();*/
								ResultSet rs1 = dbConnection.exeSQL(getMemberName);
								if (rs1.next()) {
									txtMemberName.setText(rs1.getString(1));
									txtMemberName.setEditable(false);
								} else {
									txtMemberName.setText("");
									}
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						//Display the details from milkcollection for the entered member if the details already exists
						try{
						String str1 = "SELECT clr,snf,quantity,priceperlitre,total_amount,fat FROM milkcollection WHERE " +
								"member_ID = "
								+ txtMemberID.getText().toString()
								+ " and collection_date = '"
								+ d.format(datePicker.getDate())
								+ "' and cattle_name = '"
								+ cattleComboBox.getSelectedItem().toString()
								+ "' and shift = '"
								+ shiftComboBox.getSelectedItem().toString()
								+ "'";
						ResultSet rsDetails = dbConnection
								.exeSQL(str1);
						if (rsDetails.next()) {
							txtCLR.setText(rsDetails.getString(1));
							txtSNF.setText(rsDetails.getString(2));
							txtQuantity.setText(rsDetails.getString(3));
							txtPricePerLitre.setText(rsDetails.getString(4));
							txtTotalAmount.setText(rsDetails.getString(5));
							txtFat.setText(rsDetails.getString(6));
						}
						 else {
							 	txtCLR.setText("");
								txtSNF.setText("");
								txtQuantity.setText("");
								txtPricePerLitre.setText("");
								txtTotalAmount.setText("");
								txtFat.setText("");
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void changedUpdate(DocumentEvent de) {
						try {
							if (txtMemberID.getText() == null
									|| txtMemberID.getText().equals("")) {
								txtMemberID.requestFocus();
								return;
							} else {
								String getMemberName = "SELECT COALESCE(membername,' ') FROM membercreation WHERE memberid="
										+ txtMemberID.getText().toString();
								//System.out.println(getMemberName);
								/*db1 = new DbConnection();
								db1.connect();*/
								ResultSet rs1 = dbConnection.exeSQL(getMemberName);
								if (rs1.next()) {
									txtMemberName.setText(rs1.getString(1));
									txtMemberName.setEditable(false);
								} else {
									txtMemberName.setText("");
									}
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						//Display the details from milkcollection for the entered member if the details already exists
						try{
						String str1 = "SELECT clr,snf,quantity,priceperlitre,total_amount,fat FROM milkcollection WHERE " +
								"member_ID = "
								+ txtMemberID.getText().toString()
								+ " and collection_date = '"
								+ d.format(datePicker.getDate())
								+ "' and cattle_name = '"
								+ cattleComboBox.getSelectedItem().toString()
								+ "' and shift = '"
								+ shiftComboBox.getSelectedItem().toString()
								+ "'";
						ResultSet rsDetails = dbConnection
								.exeSQL(str1);
						if (rsDetails.next()) {
							txtCLR.setText(rsDetails.getString(1));
							txtSNF.setText(rsDetails.getString(2));
							txtQuantity.setText(rsDetails.getString(3));
							txtPricePerLitre.setText(rsDetails.getString(4));
							txtTotalAmount.setText(rsDetails.getString(5));
							txtFat.setText(rsDetails.getString(6));
						}
						 else {
							 	txtCLR.setText("");
								txtSNF.setText("");
								txtQuantity.setText("");
								txtPricePerLitre.setText("");
								txtTotalAmount.setText("");
								txtFat.setText("");
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		
		txtMemberID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!txtMemberID.getText().toString().isEmpty() && !txtMemberName.getText().toString().isEmpty()) {
					// If Duplicate Entry Do not do anything and just pop a
					// message saying that Row Already exists
					String memberNo = txtMemberID.getText().toString();
					boolean isDuplicateEntry = false;
					try {
						String sqlQuery = "SELECT * FROM milkcollection where member_ID = "
								+ memberNo
								+ " and collection_date = '"
								+ d.format(datePicker.getDate())
								+ "' and cattle_name = '"
								+ cattleComboBox.getSelectedItem().toString()
								+ "' and shift = '"
								+ shiftComboBox.getSelectedItem().toString()
								+ "'";
						//System.out.println(sqlQuery);
						ResultSet rs = dbConnection.exeSQL(sqlQuery);
						while (rs.next()) {
							System.out.println("Duplicate Row found...");
							isDuplicateEntry = true;
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (isDuplicateEntry == true) {
						JOptionPane.showMessageDialog(contentPane,"MILK COLLECTION ALREADY DONE FOR MEMBER!!! Please enter MemberID","Duplicate Entry",JOptionPane.ERROR_MESSAGE);
						resetAll();
						txtMemberID.requestFocus(true);
						}
					} 
				}
		});
		txtMemberID.setForeground(new Color(0, 51, 0));
		txtMemberID.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtMemberID.setColumns(10);
		txtMemberID.setBackground(new Color(255, 250, 250));
		
		JLabel lblzgg = new JLabel("Member Name");
		lblzgg.setBounds(0, 145, 146, 35);
		panel_1.add(lblzgg);
		lblzgg.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblzgg.setHorizontalAlignment(SwingConstants.CENTER);
		lblzgg.setForeground(new Color(0, 0, 205));
		
		txtMemberName = new JTextField();
		txtMemberName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtFat.requestFocus();
			}
		});
		txtMemberName.setBounds(153, 145, 234, 37);
		panel_1.add(txtMemberName);
		txtMemberName.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtMemberName.setForeground(new Color(0, 51, 0));
		txtMemberName.setColumns(10);
		txtMemberName.setBackground(new Color(255, 250, 250));
		
		shiftComboBox = new JComboBox();
		shiftComboBox.setBounds(153, 49, 236, 35);
		panel_1.add(shiftComboBox);
		shiftComboBox.setModel(new DefaultComboBoxModel(new String[] {"Morning", "Evening"}));
		shiftComboBox.setSelectedIndex(0);
		shiftComboBox.setFont(new Font("Dialog", Font.BOLD, 22));
		shiftComboBox.setBackground(new Color(255, 239, 213));
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(65, 0, 78, 35);
		panel_1.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(new Color(0, 0, 205));
		lblDate.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		JLabel lblSession = new JLabel("Session");
		lblSession.setBounds(51, 50, 95, 35);
		panel_1.add(lblSession);
		lblSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblSession.setForeground(new Color(0, 0, 205));
		lblSession.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		btnSave = new JButton("ENTRY");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			/*
					float L= Float.parseFloat(Litre.getText());
					float Lp = Float.parseFloat(Litrep.getText());
					float f = Float.parseFloat(FAT.getText());
					float s = Float.parseFloat(SNF.getText());
					float c = Float.parseFloat(CLR.getText());
					double snf=((c/4)+0.25*(f/100)+0.35);
					String a=String.format("%.2f",( L*Lp)+(f+snf+c));
					TotalAmount.setText(a);
					*/
				Double d=Double.parseDouble( txtQuantity.getText());
				Double p=Double.parseDouble( txtPricePerLitre.getText());
				String a=String.format("%.2f",( d*p));
				txtTotalAmount.setText(a);
				
			}	
			});
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		btnSave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
		btnSave.addKeyListener(new KeyAdapter() {
			private DbConnection dbserial;
			private DbConnection db12;
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(Float.parseFloat((txtQuantity.getText().toString())) <= 0 || txtMemberName.getText().toString().isEmpty() || Float.parseFloat(txtTotalAmount.getText().toString()) <= 0 || Float.parseFloat(txtPricePerLitre.getText().toString()) <= 0){
					resetAll();
				}
				else
				{
				//Retrieve Serial Number automatically from MilkCollection
				try{
					String sqlQuerySerial="SELECT count(*) FROM milkcollection WHERE collection_date = '" + d.format(datePicker.getDate()) + "' AND shift = '" 
							+ shiftComboBox.getSelectedItem().toString() + "'";
					System.out.println(sqlQuerySerial);
		     		ResultSet rs_serial=dbConnection.exeSQL(sqlQuerySerial);
					if(rs_serial.next())
					{
						serialNumber = rs_serial.getInt(1) + 1;
					}
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
				rowInTable = new Vector<String>();  
				rowInTable.addElement(serialNumber + "");
				rowInTable.addElement(txtMemberID.getText().toString());
				rowInTable.addElement(txtMemberName.getText().toString());
				rowInTable.addElement(cattleComboBox.getSelectedItem().toString());
				rowInTable.addElement(txtQuantity.getText().toString());
				rowInTable.addElement(txtFat.getText().toString());
				if(!txtSNF.getText().toString().isEmpty())
					rowInTable.addElement(txtSNF.getText().toString());
				else
					rowInTable.addElement("0");
				if(!txtCLR.getText().toString().isEmpty())
					rowInTable.addElement(txtCLR.getText().toString());
				else
					rowInTable.addElement("0");
				rowInTable.addElement(txtPricePerLitre.getText().toString());
				rowInTable.addElement(txtTotalAmount.getText().toString());
				tableModel.addRow(rowInTable);
				//System.out.println("R is " + rowInTable);
				rowInTable = null; //Reset the Vector for new row
				//System.out.println("R is " + rowInTable);
				
				//INSERT THE ROWS TO THE MilkCollection TABLE
				insertRowsToMilkCollectionTable();
				
				String collection_date_print = d.format(datePicker.getDate());
				String member_no_print = txtMemberID.getText().toString();
				String shift_print = shiftComboBox.getSelectedItem().toString();
				String animal_name = cattleComboBox.getSelectedItem().toString();
				
				//English Print Slip 
				/*if(checkBox_Slip3_English.isSelected())
					printSlipInEnglish(txtMemberID.getText().toString(),txtCattleName.getText().toString(),txtQuantity.getText().toString(),txtFat.getText().toString(),
							txtSNF.getText().toString(),lblRate.getText().toString(),lblAmount.getText().toString());*/
				
				//Send SMS if SMS option is checked
				if(chckbxSms.isSelected()){
						String societyName = "Society";
						String shift = "AM";
						String memberName = "";
						String balanceAmountToBePaidToMember = "0.0";
						String totalQuantityOfMilk = "0.0";
						String memNo = txtMemberID.getText().toString();
						String qty = txtQuantity.getText().toString();
						String fat = txtFat.getText().toString();
						String total = txtTotalAmount.getText().toString();
						String rate = txtPricePerLitre.getText().toString();
						String smsMessage = "";
						String recipientNumber = "";
						String strQuery1 = "select COALESCE(societyname,'') from societycreation";
						DbConnection db101 = new DbConnection();
						try {
							//db101.connect();
							//System.out.println(strQuery1);
							ResultSet rs = dbConnection.exeSQL(strQuery1);
							if(rs.next()){
								societyName = rs.getString(1);
							}
						} catch (SQLException es) {
							es.printStackTrace();
						}
						
						SimpleDateFormat d1 = new SimpleDateFormat("dd-MM-yyyy");
						String collection_date = d1.format(datePicker.getDate());
						if(shiftComboBox.getSelectedItem().toString().equals("¨É½UÉÎ"))
						{
							shift = "MORNING";
						}
						else
						{
							shift = "EVENING";
						}

						String strQuery2 = "select customernameinenglish,customerphonenumber from membercreation where memberid = '"+ memNo + "'";
						//DbConnection db111 = new DbConnection();
						try {
							//db111.connect();
							//System.out.println(strQuery2);
							ResultSet rs = dbConnection.exeSQL(strQuery2);
							if(rs.next()){
								memberName = rs.getString(1);
								recipientNumber = rs.getString(2);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						final DateFormat timeFormat = new SimpleDateFormat("hh:mm",Locale.getDefault());   
						Date date = new Date();
						String time = timeFormat.format(date);
						
						//To Print Old Balance
						String strQuery3 = "SELECT COALESCE(SUM(total_amount),0),COALESCE(SUM(quantity),0) FROM milkcollection WHERE member_number = "+memNo+" AND collection_date BETWEEN " +
								"(SELECT oldbalancedate FROM oldbalancedateselection) AND '"+ d.format(datePicker.getDate())+"'";
								//DbConnection db12 = new DbConnection();
								try {
									//db12.connect();
									//System.out.println(strQuery3);
									ResultSet rs = dbConnection.exeSQL(strQuery3);
									if(rs.next()){
										balanceAmountToBePaidToMember = rs.getString(1);
										totalQuantityOfMilk = rs.getString(2);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
						
						smsMessage = collection_date + " " + shift +"\nMem No :" + memNo + "\nMilk :" + qty +" Fat:" + fat + "\nRate :"+rate + "/- Amt:" + total + "/-\nT. QTY :" + totalQuantityOfMilk + " T. Amt:" + balanceAmountToBePaidToMember + "/-\n"+ smsHeading + "\n" + messageForPrinting;
						System.out.println(smsMessage);
					try {
						//sendSMS(senderNumber, recipientNumber, memNo+"and"+qty+"and"+total);
						sendSMS(senderNumber, recipientNumber, smsMessage);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				
				resetAll();
		    	
		    	//Select Last Row always
				int rowcount = table.convertRowIndexToView(tableModel.getRowCount()-1);
				table.setRowSelectionInterval(rowcount,rowcount);
				
				//table.changeSelection(table.getColumnCount()-1, 0, false, false);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				
				txtMemberID.requestFocus(true);
			}
		}
		});
		//btnSave.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/cow_baby_95x80.png")));
		btnSave.setBackground(new Color(238, 232, 170));
		btnSave.setBounds(818, 158, 158, 47);
		panel.add(btnSave);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(412, 23, 223, 182);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFat = new JLabel("Fat");
		lblFat.setBounds(10, 49, 52, 35);
		panel_2.add(lblFat);
		lblFat.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblFat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFat.setForeground(new Color(0, 0, 205));
		
		txtFat = new JTextField();
		txtFat.setBounds(59, 49, 146, 35);
		panel_2.add(txtFat);
		txtFat.setForeground(new Color(0, 51, 0));
		txtFat.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtFat.setColumns(10);
		txtFat.setBackground(new Color(255, 250, 250));
		
		JLabel lblSNF = new JLabel("SNF");
		lblSNF.setBounds(10, 104, 52, 25);
		panel_2.add(lblSNF);
		lblSNF.setHorizontalAlignment(SwingConstants.CENTER);
		lblSNF.setForeground(new Color(0, 0, 205));
		lblSNF.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		txtSNF = new JTextField();
		txtSNF.setBounds(59, 147, 146, 35);
		panel_2.add(txtSNF);
		txtSNF.setForeground(new Color(0, 51, 0));
		txtSNF.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtSNF.setColumns(10);
		txtSNF.setBackground(new Color(255, 255, 255));
		
		txtCLR = new JTextField();
		txtCLR.setBounds(59, 99, 146, 35);
		panel_2.add(txtCLR);
		txtCLR.setForeground(new Color(0, 51, 0));
		txtCLR.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtCLR.setColumns(10);
		txtCLR.setBackground(new Color(255, 255, 255));
		
		JLabel lblClr = new JLabel("CLR");
		lblClr.setBounds(10, 150, 52, 25);
		panel_2.add(lblClr);
		lblClr.setHorizontalAlignment(SwingConstants.CENTER);
		lblClr.setForeground(new Color(0, 0, 205));
		lblClr.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		cattleComboBox = new JComboBox();
		cattleComboBox.setBounds(59, 3, 146, 35);
		panel_2.add(cattleComboBox);
		cattleComboBox.setModel(new DefaultComboBoxModel(new String[] {"COW", "BUFFALO"}));
		cattleComboBox.setSelectedIndex(0);
		cattleComboBox.setFont(new Font("Dialog", Font.BOLD, 22));
		cattleComboBox.setBackground(new Color(255, 239, 213));
		
		JLabel lblCattle = new JLabel("Cattle");
		lblCattle.setBounds(0, 0, 59, 37);
		panel_2.add(lblCattle);
		lblCattle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCattle.setForeground(new Color(0, 0, 205));
		lblCattle.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		panel_3.setBounds(645, 23, 349, 131);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(136, 0, 213, 35);
		panel_3.add(txtQuantity);
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setForeground(new Color(0, 51, 0));
		txtQuantity.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtQuantity.setColumns(10);
		txtQuantity.setBackground(new Color(255, 255, 255));
		
		JLabel lblit = new JLabel("Litres");
		lblit.setBounds(74, 3, 52, 29);
		panel_3.add(lblit);
		lblit.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		//lblit.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/Milk1_70x70.png")));
		lblit.setHorizontalAlignment(SwingConstants.LEFT);
		lblit.setForeground(new Color(0, 0, 205));
		
		JLabel lblvG = new JLabel("Total Amount");
		lblvG.setBounds(0, 96, 132, 35);
		panel_3.add(lblvG);
		lblvG.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblvG.setHorizontalAlignment(SwingConstants.CENTER);
		lblvG.setForeground(new Color(0, 0, 205));
		
		txtPricePerLitre = new JTextField();
		txtPricePerLitre.setBounds(136, 46, 213, 37);
		panel_3.add(txtPricePerLitre);
		txtPricePerLitre.setForeground(new Color(0, 51, 0));
		txtPricePerLitre.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtPricePerLitre.setColumns(10);
		txtPricePerLitre.setBackground(new Color(255, 250, 250));
		
		txtTotalAmount = new JTextField();
		txtTotalAmount.setBounds(136, 96, 213, 35);
		panel_3.add(txtTotalAmount);
		txtTotalAmount.setForeground(new Color(0, 51, 0));
		txtTotalAmount.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtTotalAmount.setColumns(10);
		txtTotalAmount.setBackground(new Color(255, 250, 250));
		
		JLabel lblPriceltr = new JLabel("Price/Ltr");
		lblPriceltr.setBounds(36, 46, 96, 39);
		panel_3.add(lblPriceltr);
		lblPriceltr.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceltr.setForeground(new Color(0, 0, 205));
		lblPriceltr.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		
		lblMemberPhoto = new JLabel("");
		lblMemberPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		//lblMemberPhoto.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/150  by 160.png")));
		lblMemberPhoto.setForeground(new Color(139, 0, 0));
		lblMemberPhoto.setFont(new Font("Calibri", Font.BOLD, 40));
		lblMemberPhoto.setBackground(new Color(230, 230, 250));
		lblMemberPhoto.setBounds(1152, 11, 158, 129);
		panel.add(lblMemberPhoto);
		
		chckbxSms = new JCheckBox("SMS");
		chckbxSms.setBackground(new Color(255, 204, 0));
		chckbxSms.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		chckbxSms.setBounds(703, 170, 78, 29);
		panel.add(chckbxSms);
		
		JLabel lblSocietyName = new JLabel("SOCIETY NAME");
		lblSocietyName.setBounds(5, 3, 634, 28);
		lblSocietyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocietyName.setForeground(new Color(253, 245, 230));
		lblSocietyName.setBackground(new Color(0, 191, 255));
		contentPane.add(lblSocietyName);
		
		lblTime = new JLabel("TIME");
		lblTime.setBounds(580, 5, 217, 29);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new Color(0, 0, 0));
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTime.setBackground(new Color(230, 230, 250));
		contentPane.add(lblTime);
		
		datePicker = new JXDatePicker();
		datePicker.getEditor().setEditable(false);
		datePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtMemberID.requestFocus(true);
			}
		});
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtMemberID.requestFocus(true);
			}
		});
		datePicker.getEditor().setForeground(Color.BLUE);
		datePicker.getEditor().setBackground(new Color(255, 245, 238));
		datePicker.setFont(new Font("Calibri", Font.BOLD, 20));
		datePicker.setBounds(66, 15, 167, 35);
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					DbConnection db6;
				   String deleteQuery = "DELETE FROM milkcollection WHERE collection_date = '"+d.format(datePicker.getDate())+
						   "' and shift = '"+ shiftComboBox.getSelectedItem().toString() +"'and member_ID = "+txtMemberID.getText().toString()+" " 
						   +"and cattle_name = '"+cattleComboBox.getSelectedItem().toString()+"'";
						System.out.println(deleteQuery);
					try {
							db6 = new DbConnection();
							db6.connect();
							db6.exeUpdate(deleteQuery);
							db6.commitAll();
							db6.close();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}catch (SQLException e2) {
						e2.printStackTrace();
					}
				
				   //Remove the row from JTabel Model
					  String memberNumber = txtMemberID.getText().toString();
					  String animalName = cattleComboBox.getSelectedItem().toString();

					    DefaultTableModel model = (DefaultTableModel)table.getModel();
					    for (int i = 0; i < model.getRowCount(); i++) {
					    	 if (((String)model.getValueAt(i, 2)).equals(memberNumber) && ((String)model.getValueAt(i, 4)).equals(animalName)) {
					            model.removeRow(i);
					            break;
					        }
					    }
					txtMemberID.requestFocus();
					resetAll();
					displayMilkCollectionDetailsForCurrentDateAndShift();
			}
		});
		btnClear.setBounds(501, 623, 150, 50);
		//btnClear.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/Delete_38x38_blue.png")));
		btnClear.setForeground(new Color(0, 0, 128));
		contentPane.add(btnClear);
		
		btnReport = new JButton("REPORT");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReport.setBounds(143, 623, 143, 50);
		//btnReport.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/Reort.png")));
		btnReport.setForeground(new Color(0, 0, 128));
		contentPane.add(btnReport);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(811, 623, 143, 50);
		//btnClose.setIcon(new ImageIcon(MilkCollection.class.getResource("/Images/Close2_38x38.png")));
		btnClose.setForeground(new Color(0, 0, 128));
		contentPane.add(btnClose);
		
		String[] columnNames = {"SlNo","Member ID","Member Name","Cattle Type","Qty","Fat","SNF","CLR","Price/Ltr","Amount"};
		Object[][] data = null;	
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		table.setForeground(new Color(102, 51, 0));
		table.setBackground(new Color(102, 204, 204));
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		table.setRequestFocusEnabled(false);
		table.setRowHeight(24);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setAutoCreateRowSorter(true);
		table.setFont(new Font("Lucida Console", Font.BOLD, 18));
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(5, 281, 1001, 328);
		contentPane.add(scrollPane);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(30);
		table.getColumnModel().getColumn(9).setPreferredWidth(30);

		table.changeSelection(0, 0, false, false);
		
		table.addMouseListener(new MouseAdapter() {
	          public void mouseClicked(MouseEvent e) {  
	              table.setColumnSelectionAllowed(false);
	              table.setRowSelectionAllowed(true);
	              table.requestFocus(true);
	          }  
	    });
		
		
		 // right align 9th and 10th column
	      TableColumnModel columnModel9 = table.getColumnModel();
	      TableColumn column9 = columnModel9.getColumn(9); 
	      DefaultTableCellRenderer renderer9 = new DefaultTableCellRenderer();
	      renderer9.setHorizontalAlignment(JLabel.RIGHT);
	      column9.setCellRenderer(renderer9);
	      
		scrollPane.setFont(new Font("Lucida Console", Font.BOLD, 20));
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane);
		
		// To set the date and time automatically
		 final DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss",Locale.getDefault());
/*		final DateFormat timeFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss", Locale.getDefault());
*/		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat.format(date);
				lblTime.setText(time);
			}
		};
		Timer timer = new Timer(1000, timerListener);
		// to make sure it doesn't wait one second at the start
		timer.setInitialDelay(0);
		timer.start();
		
		// Display Society Name
		try {
			/*dbs = new DbConnection();
			dbs.connect();*/
			String sqlQuery = "SELECT CONCAT(societyname,'  ',societyaddress) FROM societycreation";
			//System.out.println(sqlQuery);
			ResultSet rs11 = dbConnection.exeSQL(sqlQuery);
			while (rs11.next()) {
				lblSocietyName.setText(rs11.getString(1).toString());
			}
			//dbs.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//GET SMS CREDENTIALS
		try {
			/*dbs = new DbConnection();
			dbs.connect();*/
			String sqlQuery = "SELECT sendernumber,sendername,apikey FROM sms_credentials";
			//System.out.println(sqlQuery);
			ResultSet rs11 = dbConnection.exeSQL(sqlQuery);
			while (rs11.next()) {
				senderNumber = rs11.getString(1);
				senderName = rs11.getString(2);
				apikey = rs11.getString(3);
			}
			//dbs.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		//Display the shift automatically depending on time w r t AM/PM
		Date dateforShift = new Date();
		String strDateFormat = "a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		String shiftType = sdf.format(dateforShift);
		//if it is AM then
		if(shiftType.equals("AM"))
		{
			shiftComboBox.setModel(new DefaultComboBoxModel(new String[] {"MORNING", "EVENING"}));
		}
		//if it is PM then
		else
		{
			shiftComboBox.setModel(new DefaultComboBoxModel(new String[] {"EVENING","MORNING"}));
		}
		
		// Functional Keys to delete the entry from table
		final AbstractAction deleteSelectedRowFromTable = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					deleteSelectedRow();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		};

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "FUNCTION_2");
		getRootPane().getActionMap().put("FUNCTION_2", deleteSelectedRowFromTable);
		
		txtCLR.addActionListener(enterAction);
		txtSNF.addActionListener(enterAction);
		txtFat.addActionListener(enterAction);
		txtMemberName.addActionListener(enterAction);
		txtMemberID.addActionListener(enterAction);
		txtTotalAmount.addActionListener(enterAction);
		txtPricePerLitre.addActionListener(enterAction);
		txtQuantity.addActionListener(enterAction);
		
		/* TO Display Table Details ONLY */
		 displayTableDetailsOnly();
	}
	
	protected void printSlipInEnglish(String memNo, String animalName, String qty, String fat, String snf,String rate,String total) {
		String societyName = "Society";
		String shift = "AM";
		String memberName = "";
		String clr = "0.0";
		String balanceAmountToBePaidToMember = "0.0";
		String totalQuantityOfMilk = "0.0";
		//then print the slip
		String strQuery1 = "select COALESCE(societyname,'') from societycreation";
		//DbConnection db101 = new DbConnection();
		try {
			//db101.connect();
			//System.out.println(strQuery1);
			ResultSet rs = dbConnection.exeSQL(strQuery1);
			if(rs.next()){
				societyName = rs.getString(1);
			}
		} catch (SQLException es) {
			es.printStackTrace();
		}
		
		SimpleDateFormat d1 = new SimpleDateFormat("dd-MM-yyyy");
		String collection_date = d1.format(datePicker.getDate());
		if(shiftComboBox.getSelectedItem().toString().equals("¨É½UÉÎ"))
		{
			shift = "AM";
		}
		else
		{
			shift = "PM";
		}

		String strQuery2 = "select customernameinenglish from membercreation where memberid = "+ memNo;
		//DbConnection db111 = new DbConnection();
		try {
			//db111.connect();
			//System.out.println(strQuery2);
			ResultSet rs = dbConnection.exeSQL(strQuery2);
			if(rs.next()){
				memberName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(snf.equals(""))
			snf = "0.0";
		if(clr.equals(""))
			clr = "0.0";
		
		if(messageForPrinting.isEmpty())
			messageForPrinting = "THANKS,MPS";
		
		final DateFormat timeFormat = new SimpleDateFormat("hh:mm",Locale.getDefault());   
		Date date = new Date();
		String time = timeFormat.format(date);
		
		String printableSlip = " "+societyName + "    \nDate :" +collection_date +","+animalName+ "\nMem No:" + memNo+ " ,Shift:" +shift + "\nName:" + memberName +"" +
						"\nQty :" + qty +", Fat:"+ fat + "\nSNF :"+ snf +",CLR:"+ clr + "\nRate:"+rate + ",Amt:" + total 
						+ "\nTotal QTY:"+ totalQuantityOfMilk+",\nTotal Amt:"+balanceAmountToBePaidToMember+"\n"+ messageForPrinting+"\r\n\n";
		try {  
       	 String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
       	 //System.out.println("Default printer: " + defaultPrinter);
       	 PrintService service = PrintServiceLookup.lookupDefaultPrintService();
       	 InputStream is = new ByteArrayInputStream(printableSlip.getBytes("UTF8"));

       	 DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
       	 Doc doc = new SimpleDoc(is, flavor, null);
       	 
       	 DocPrintJob job = service.createPrintJob();

       	 job.print(doc, null);
       	 is.close();
        } catch (Exception e) {  
            System.out.println("Exception occurred: " + e);  
        }  
	
	}

	private Connection getConnection() throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/mps?user=root&password=root");
		return connection;
	
	}

	protected void deleteSelectedRow() throws ClassNotFoundException {

		//Remove the row from the TableModel and also delete the records from Tablemilkcollection
		//DB condition Should be based on the member No. and Current Date and also Animal Type
			//System.out.println("INSIDE SELECTED ROW DELETE");
		   int deleteRowNum = table.getSelectedRow();
		 
		   String deleteMemberId = (String)table.getValueAt(deleteRowNum, 2);
		   //System.out.println("THE MEMBER ID To be deleted is " + deleteMemberId);
	
	    	/* NEW CODE WITH DBCP to faster execution. Code updated on 20.1.2019 */
	    	try (Connection con = getConnection()) {
	             Statement stmt=con.createStatement();
	             String deleteQuery = "DELETE FROM milkcollection WHERE collection_date = '"+d.format(datePicker.getDate())+
	  				   "' and shift = '"+ shiftComboBox.getSelectedItem().toString() +"'and member_number = "+table.getValueAt(deleteRowNum, 2)+" " 
	  				   +"and animal_name = '"+table.getValueAt(deleteRowNum, 4)+"'";
	             stmt.executeUpdate(deleteQuery);
	          } catch (SQLException e) {
	             e.printStackTrace();
	          }
		   //Remove the row from JTabel Model
		   tableModel.removeRow(deleteRowNum);
		   resetAll();
	}

	void displayMilkCollectionDetailsForCurrentDateAndShift() {
		d = new SimpleDateFormat("yyyy-MM-dd");
		decimalFormatForSingleDigit = new DecimalFormat("##.#");
		//System.out.println("Date PICKER : (yyyy-MM-dd)" + d.format(datePicker.getDate()));
		//Select the Milk Collections Data w.r.t Date and Shift
		try{
	    	/* NEW CODE WITH DBCP to faster execution. Code updated on 20.1.2019 */
			try (Connection con = getConnection()) {
		         Statement stmt=con.createStatement();
		         
			String sqlQuery1 = "SELECT member_ID,member_name,cattle_name,quantity,fat,snf,clr,priceperlitre,total_amount " +
					"FROM milkcollection WHERE collection_date = '" + d.format(datePicker.getDate()) + "' AND shift = '" + shiftComboBox.getSelectedItem().toString() + "' order by serialnumber asc";
			//System.out.println(sqlQuery1);
			ResultSet rs1=stmt.executeQuery(sqlQuery1);
			tableModel.setRowCount(0);
			while(rs1.next())
			{
				rowInTable = new Vector<String>();  
				rowInTable.addElement(rs1.getString(1));
				rowInTable.addElement(rs1.getString(2));
				rowInTable.addElement(rs1.getString(3));
				rowInTable.addElement(rs1.getString(4));
				rowInTable.addElement(rs1.getString(5));
				rowInTable.addElement(rs1.getString(6));
				rowInTable.addElement(rs1.getString(7));
				rowInTable.addElement(rs1.getString(8));
				rowInTable.addElement(rs1.getString(9));
				rowInTable.addElement(rs1.getString(10));
				tableModel.addRow(rowInTable);
			}
		    } catch (SQLException e) {
				        e.printStackTrace();
				}
			//db.close();
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
	}

	protected void insertRowsToMilkCollectionTable() {
		float snfvalue = 0.0f;
		float clrvalue = 0.0f;
		//BigDecimal canNumber = null;
		//String totalQuantityOfMilk = "";
		try{
			if(!txtSNF.getText().equals(""))
			 snfvalue = Float.parseFloat(txtSNF.getText().toString());
			else
				snfvalue = 0.0f;
			if(!txtCLR.getText().equals(""))
				 clrvalue = Float.parseFloat(txtCLR.getText().toString());
			else
				clrvalue = 0.0f;
			//String milkType = "";
	/*	String insertQuery =  "Insert into milkcollection (member_ID,member_name,collection_date,clr,snf,cattle_name,"
				+ "quantity,fat,priceperlitre,total_amount)"+
				"values ('"+shiftComboBox.getSelectedItem().toString()+"',"+txtMemberID.getText().toString()+",'"+txtMemberName.getText().toString()+"','"+d.format(datePicker.getDate())+
				"',"+clrvalue+","+snfvalue+",'"+cattleComboBox.getSelectedItem().toString()+"',"+txtQuantity.getText().toString()+","+txtFat.getText().toString()+
				","+txtPricePerLitre.getText().toString()+","+txtTotalAmount.getText().toString()+")";
		System.out.println(insertQuery);
		db = new DbConnection();
		db.connect(); 
		int count = db.exeUpdate(insertQuery);
		//System.out.println("No. of Rows inserted = " + count);
		
		db.commitAll(); 
		db.close();*/
			String insertQuery =  "Insert into milkcollection (member_ID,member_name"
					+ "fat,total_amount)"+
					"values ('"+txtMemberID.getText().toString()+",'"+txtMemberName.getText().toString()+","+txtFat.getText().toString()+","+txtTotalAmount.getText().toString()+")";
				System.out.println(insertQuery);
				db = new DbConnection();
				db.connect(); 
				//int count = db.exeUpdate(insertQuery);
				//System.out.println("No. of Rows inserted = " + count);
				
				db.commitAll(); 
				db.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	protected void resetAll() {
		txtMemberID.setText("");
		txtMemberName.setText("");
		txtQuantity.setText("");
		txtFat.setText("");
		txtPricePerLitre.setText("");
		txtTotalAmount.setText("");
		txtCLR.setText("");
		txtSNF.setText("");
		txtMemberID.requestFocus(true);
	}
	
	//SEND SMS TO MEMBERS 
	public void sendSMS(String senderNumber,String recipientNumber,String smsMessage) throws UnsupportedEncodingException {
		try {
			// Construct data
			//String apiKey = "apikey=" + "E86UcRyn0ME-Kqr0HFknTFrDQnY0BJNbB5h3BGx8XM";
			String apiKey = "apikey=" + apikey;
			String message = "&message=" + smsMessage;
			String sender = "&sender=" + senderName;
			String numbers = "&numbers=" + recipientNumber;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			
			//System.out.println("URL LINK " + data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
		}
	}
	
	void displayTableDetailsOnly() {
		d = new SimpleDateFormat("yyyy-MM-dd");
		decimalFormatForSingleDigit = new DecimalFormat("##.#");
		//System.out.println("Date PICKER : (yyyy-MM-dd)" + d.format(datePicker.getDate()));
		//Select the Milk Collections Data w.r.t Date and Shift
		try{
	    	/* NEW CODE WITH DBCP to faster execution. Code updated on 20.1.2019 */
			try (Connection con = getConnection()) {
		         Statement stmt=con.createStatement();
		         
			String sqlQuery1 = "SELECT serialnumber,member_ID,member_name,cattle_name,quantity,fat,snf,clr,priceperlitre,total_amount " +
					"FROM milkcollection WHERE collection_date = '" + d.format(datePicker.getDate()) + "' AND shift = '" + shiftComboBox.getSelectedItem().toString() + "' order by serialnumber asc";
			//System.out.println(sqlQuery1);
			ResultSet rs1=stmt.executeQuery(sqlQuery1);
			tableModel.setRowCount(0);
			while(rs1.next())
			{
				rowInTable = new Vector<String>();  
				rowInTable.addElement(rs1.getString(1));
				rowInTable.addElement(rs1.getString(2));
				rowInTable.addElement(rs1.getString(3));
				rowInTable.addElement(rs1.getString(4));
				rowInTable.addElement(rs1.getString(5));
				rowInTable.addElement(rs1.getString(6));
				rowInTable.addElement(rs1.getString(7));
				rowInTable.addElement(rs1.getString(8));
				rowInTable.addElement(rs1.getString(9));
				rowInTable.addElement(rs1.getString(10));
				tableModel.addRow(rowInTable);
			}
			}
			catch (SQLException e) {
				        e.printStackTrace();
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
	}
}

