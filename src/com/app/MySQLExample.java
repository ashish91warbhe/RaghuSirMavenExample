package com.app;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
/**
* @author RAGHU
* @version JDK 1.7
*/
public class MySQLExample {
/**
* 1. Load Connection Details from Properties file
*/
public static Properties loadProperties(){
Properties props=null;
try {
ClassLoader loader = Thread.currentThread().
getContextClassLoader();
InputStream resourceStream = loader. getResourceAsStream("jdbc.properties");
props=new Properties();
props.load(resourceStream);
} catch (Exception e) {
e.printStackTrace();
}
return props;
}
/**
* 2. Get Connection
* @throws Exception
*/
public static Connection getConnection() throws Exception{
Properties props=loadProperties();
Class.forName(props.getProperty("jdbc.driver"));
Connection con = DriverManager.getConnection(
props.getProperty("jdbc.url"),
props.getProperty("jdbc.user"),
props.getProperty("jdbc.password"));
return con;
}
/**
* 3. Select Data from employee table
* @throws Exception
*/
public static void selectEmployee() throws Exception{


Statement st = getConnection().createStatement();
ResultSet rs = st.executeQuery("select * from employee");
while (rs.next()) {
System.out.print(rs.getInt(1)+",");
System.out.print(rs.getString(2)+",");
System.out.println(rs.getString(3));
}
}
public static void main(String[] args) {
try {
selectEmployee();
} catch (Exception e) {
e.printStackTrace();
}
}
}
