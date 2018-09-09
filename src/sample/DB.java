package sample;

import sample.models.Contact;
import sample.models.ContactNumber;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "1";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void uppdateContact(int id,String name,String surname,String group){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "update contact set contact.name = '"+name+"', contact.surname ='"+surname+"', contact.group ='"+group+"' where id ="+id;

            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

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
            String query = "select * from contact order by name";
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
        return getNumbers(contact.getId());
    }

    public static ArrayList<ContactNumber> getNumbers(int id1){
        ArrayList<ContactNumber> arrayList = new ArrayList<>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "select * from contactNumber where contactNumber.contact = "+id1;
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

    public static void addNumber(String number,int id){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "insert into contactNumber (contactNumber.number, contactNumber.contact) values ('"+number+"','"+id+"')";

            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public static void deleteNumber(int id){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "delete from contactNumber where id="+id;

            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public static void deleteContact(int id){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (ContactNumber number :
                    DB.getNumbers(id)) {
                deleteNumber(number.getId());
            }

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String query = "delete from contact where id="+id;

            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}


