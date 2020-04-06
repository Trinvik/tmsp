package tsmp.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

public class fatrange extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fatrange frame = new fatrange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	
	public fatrange() {
		connection=Dbconnection.dbconnect();
		setTitle("DAIRY MANAGEMENT SYSTEM");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 403);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" RANGE REGISTRATION");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(292, -8, 216, 50);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(291, 24, 177, 24);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(500, 53, 46, -32);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(416, 102, 384, 218);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);
		
		JButton btnNewButton_2 = new JButton("VIEW");
		btnNewButton_2.setBackground(new Color(51, 204, 102));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Select * from rang_registration";
					PreparedStatement sta=connection.prepareStatement(query);
					ResultSet rs=sta.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException E) {
					// TODO Auto-generated catch block
					E.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(547, 68, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("FAT:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(45, 136, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(83, 135, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(" ");
				textField.setText(" ");
				textField_1.requestFocus();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(141, 290, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SAVE");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					 String query = "INSERT INTO rang_registration(SNF,FAT,CLR) values(?,?,?)";
					 PreparedStatement sta=connection.prepareStatement(query);
				
						sta.setString(1,textField_1.getText());
						sta.setString(2,textField.getText());
						sta.setString(3,textField_2.getText());
						sta.execute();
						JOptionPane.showConfirmDialog(null, "Saved Data");
						sta.close();
				}
				catch(Exception E) {
					JOptionPane.showConfirmDialog(null, "Something went Wrong");
					E.printStackTrace();
							
				}
			}
		
				
				
				
				
			
		});
		btnNewButton_1.setBounds(30, 290, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("EXIT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmValue = JOptionPane.showConfirmDialog(null, "Do you really want to exit?");
				//System.out.println(confirmValue);
				if(confirmValue == 0)
				System.exit(0);
				if(confirmValue==1)
					textField_1.requestFocus();
				if(confirmValue == 2)
					textField_1.requestFocus();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_4.setBounds(259, 290, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("SNF:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(45, 176, 38, 24);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 180, 117, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CLR:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(45, 225, 38, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(83, 224, 117, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(fatrange.class.getResource("/resources/output (19).png")));
		lblNewLabel_5.setBounds(0, 0, 825, 364);
		contentPane.add(lblNewLabel_5);
		
		
		
		
	
	}
}