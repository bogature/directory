package sample;

import sample.models.Contact;
import sample.models.ContactNumber;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3307/mydb";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void addContact(String name,String surname,String group){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "insert into contact (contact.name, contact.surname, contact.group) values ('"+name+"','"+surname+"','"+group+"')";

            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public static ArrayList<Contact> getContacts(){
        ArrayList<Contact> arrayList = new ArrayList<>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "select * from contact";
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String group = rs.getString("group");
                Contact contact = new Contact(id,name,surname,group);
                arrayList.add(contact);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return arrayList;
    }

    public static ArrayList<ContactNumber> getNumbers(Contact contact){
        ArrayList<ContactNumber> arrayList = new ArrayList<>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "select * from contactnumber where contactnumber.contact = "+contact.getId();
            rs = stmt.executeQuery(query);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                ContactNumber contactNumber = new ContactNumber(id,number);
                arrayList.add(contactNumber);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return arrayList;
    }
}


