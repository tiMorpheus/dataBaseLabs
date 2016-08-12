package library;

import java.sql.*;

public class Library {
    private Connection connection = null;
    private Statement statement = null;


    public Library(String DBName, String ip, int port) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+DBName;
        connection = DriverManager.getConnection(url,"root","root");
        statement = connection.createStatement();
    }

    public void stop() throws SQLException {
        connection.close();
    }

    public boolean addAuthor(int id , String name){
        String sql = "INSERT INTO authors (id_author, name) VALUES ("
                + id + ", '"+name+"')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Author added");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR");
            System.out.println(">>> "+e.getMessage());
            return false;
        }
    }

    public boolean addBook(int id, String title, int id_author){
        String sql = "INSERT INTO books VALUES ("
                + id + ", '"+title+"', "+ id_author+")";

        try{
            statement.executeUpdate(sql);
            System.out.println("Book added");
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
