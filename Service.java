
import java.sql.SQLException;
import java.util.List;
public interface Service {
        List<Product> displayAllProduct() throws SQLException;
        boolean placeOrder( Order newOrder ) throws SQLException;
        boolean cancelOrder(int orderId );
    }

