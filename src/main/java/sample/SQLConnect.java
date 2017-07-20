package sample;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SQLConnect {
    //Declare JDBC objects
    private Connection con = null;
    private Statement stmt = null;
    private CallableStatement cstmt = null;
    private ResultSet rst = null;

    String serverName = null;
    String portNumber = null;
    String databaseName = null;
    String username = null;
    String password = null;

    public SQLConnect(String serverName, String portNumber, String databaseName, String username, String password){
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public void Connect() throws Exception {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(serverName);
        ds.setPortNumber(Integer.parseInt(portNumber));
        ds.setDatabaseName(databaseName);
        ds.setUser(username);
        ds.setPassword(password);

        con = ds.getConnection();
    }

    public void getData() throws Exception{
        String sqlQuery = "SELECT * FROM dbo.pracownicy";
        stmt = con.createStatement();
        rst = stmt.executeQuery(sqlQuery);
        int lp = 0;
        while( rst.next() ) {
            System.out.println("Imie i nazwisko: " + ++lp + " " + rst.getString(1) + " " + rst.getString(2));
        }
    }

    public boolean checkConn() throws SQLException {
        if( !con.isClosed() || con != null){
            return true;
        }
        return false;
    }



}
