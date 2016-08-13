
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    // Вспомогательный метод получения соединения

    private Connection getConnection() throws Exception {

        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        return DriverManager.getConnection( "jdbc:derby://172.27.12.108:1527/myDB10;user=me;password=mine");
    }

    public List<Integer> getProductIds() throws Exception{
        List<Integer> productsIds= new ArrayList<>();
        Connection connection = getConnection();

        ResultSet rs = connection.createStatement().executeQuery("SELECT id FROM products");

        while (rs.next()){
            productsIds.add(rs.getInt(1));
        }
        rs.close();
        connection.close();
        return productsIds;
    }

    public List<Product> getProductById(int id) throws Exception{
        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT description, rate, quantity FROM products WHERE id = ? "
        );
        statement.setInt(1,id);

        ResultSet rs = statement.executeQuery();

        Product product = null;

        while (rs.next()){
            product = new Product(id,rs.getString(1),
                    rs.getFloat(2),
                    rs.getInt(3));
            products.add(product);
        }
        rs.close();
        connection.close();
        return products;
    }

    public void addProduct(Product product) throws Exception{
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("Insert into products " +
                                                                    "(id, description, rate, quantity) " +
                                                                         "values (?, ?, ?, ?)");
        statement.setInt(1,product.getId());
        statement.setString(2, product.getDescription());
        statement.setFloat(3,product.getRate());
        statement.setInt(4,product.getQuantity());

        statement.executeUpdate();

        connection.close();
    }

    public void setProduct(Product product) throws Exception{
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("Update products " +
                                                                  "Set description=?, rate=?, quantity=? " +
                                                                    "Where id=?");
        statement.setString(1, product.getDescription());
        statement.setFloat(2, product.getRate());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getId());


        statement.executeUpdate();
        connection.close();
    }
    public void removeProduct(int id) throws Exception{
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");

        statement.setInt(1,id);

        statement.executeUpdate();
        connection.close();
    }
}
