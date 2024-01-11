
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement.*;

public class ServiceImplementation implements Service {
    private static Connection conn;
    static {
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","tiger");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    @Override
    public List<Product> displayAllProduct() throws SQLException {
        String query="Select * from Product";
        List<Product> productList=new ArrayList<>();
        Statement stmt=conn.createStatement();
        ResultSet rs= stmt.executeQuery(query);
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt(1));
            product.setProductName(rs.getString(2));
            product.setProductPrice(rs.getDouble(3));
            product.setProductQty(rs.getInt(4));
            productList.add(product);
        }

        return productList;
    }

    @Override
    public boolean placeOrder(Order newOrder) throws SQLException {
        boolean status=false;
        CallableStatement cstmt= conn.prepareCall( " {call  placeOrder( ? , ? , ? , ?)}");
        cstmt.setString(1, newOrder.getCustomerName());
        cstmt.setInt(2,newOrder.getOrderId());
        cstmt.setInt(3,newOrder.getProductQty());
        cstmt.execute();
       status= cstmt.getBoolean(4);
        return status;
    }
    @Override
    public boolean cancelOrder(int orderId) {

        return false;
    }
}
