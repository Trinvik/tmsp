package tsmp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Dbconnection {
	
	
	 static Connection con=null;
	 static Statement stmt=null;
		public static Connection dbconnect()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MPS", "root", "root");
			
				return con;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			/*finally
			{
				if(stmt!=null)
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(con!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		*/
		
		}
		
}