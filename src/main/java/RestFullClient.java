import model.Orders;

public class RestFullClient {
    public static void main(String[] args) {
        OrderClient client = new OrderClient();
        client.authenticate("admin", "admin");
        Orders orderRes = client.createOrder(new Orders("Пишем код правильно", "И не косячим"));
        long orderId = orderRes.getId();
        Orders orderById = client.getOrderById(orderId);
        Orders[] ordersList = client.getOrderList();
        Orders editedOrder = client.updateOrder(orderId, new Orders("Измененный текст", "Измененный комментарий"));
        client.deleteOrder(orderId);
    }
}
