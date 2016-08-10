package MAP;


import java.sql.*;

public class Map {

    private Connection connection = null;  // соединение с БД
    private Statement statement = null; // operator

    public Map(String DBName, String ip, int port) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://"+ip+":"+port+"/"+DBName;
        connection = DriverManager.getConnection(url,"root","root");
        statement = connection.createStatement();
    }


    public void stop() throws SQLException {
        connection.close();
    }
    public boolean addCountry(int id, String name){
        String sql = "INSERT INTO countries (id_country, name) VALUES ("+id+", '"+name+"')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Country "+ name + " added to th DB");
            return true;
        } catch (SQLException e) {
            System.out.println("Error! country " + name + " didn't added to the DB");
            System.out.println(">>>> "+e.getMessage());
            return false;
        }
    }
    public boolean deleteCountry(int id){
        String sql = "DELETE FROM countries WHERE id_country = "+id;
        try {
            int c = statement.executeUpdate(sql);
            if(c < 0){
                System.out.println("Adding country with id = "+ id + "- true");
                return true;
            }else{

                System.out.println("country with id =" +id+"doesn't exist");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Exception... Can't delete country with id ="+ id);
            System.out.println(" >>>> "+ e.getMessage());
            return false;
        }
    }

    public void showCountries() {
        String sql = "SELECT id_country, name FROM countries";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Country list: ");
            while(resultSet.next()){
                int id = resultSet.getInt("id_country");
                String name = resultSet.getString("name");
                System.out.println(" >> "+ id+  " - "+name);
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println("Exception");
            System.out.println(" >>> "+ e.getMessage());
        }
    }
}


