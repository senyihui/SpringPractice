package orders.db;

import orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


public class OrderRepositoryTemplate {

    @Autowired
    MongoOperations mongo;

    long orderCount = mongo.getCollection("order").count();

    Order findById(String id) {
        return mongo.findById(id, Order.class);
    }

    List<Order> findCustomer(String customerName) {
        return mongo.find(Query.query(
                Criteria.where("client").is(customerName)), Order.class
        );
    }

    void remove(Order order) {
        mongo.remove(order);
    }


}
