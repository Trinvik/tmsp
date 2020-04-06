package tsmp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private Connection conn;
	private Statement stmt1, stmt2;
	private ResultSet rs;
	private PreparedStatement pstmt;

	public DbConnection() {
		super();
	}

	public boolean connect() throws ClassNotFoundException, SQLException {
		try {
			String url = "jdbc:mysql://localhost:3306/mps??autoReconnect=true&relaxAutoCommit=true";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Connection Established!");
		return true;
	}

	public void commitAll() throws SQLException {
		if (conn != null)
			conn.commit();
	}

	public void close() throws SQLException {
		try {
			if (stmt1 != null)
				stmt1.close();
			if (stmt2 != null)
				stmt2.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet exeSQL(String query) throws SQLException {
		try {
			stmt1 = conn.createStatement();
			rs = stmt1.executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return (rs == null) ? null : rs;
	}

	public int exeUpdate(String query)  {
		int  i = 0;
		try {
			i = 0;
		stmt2 = conn.createStatement();
		i = stmt2.executeUpdate(query);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return (i == 0) ? 0 : i;
		}
	
	public PreparedStatement pstmtExeUpdate(String query)  {
		try {
		pstmt = conn.prepareStatement(query);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return pstmt;
		}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		DbConnection dbObj = new DbConnection();
		boolean connected = dbObj.connect();
	}
}