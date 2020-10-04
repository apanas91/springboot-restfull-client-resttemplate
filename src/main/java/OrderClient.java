import model.AuthReq;
import model.AuthRes;
import model.Orders;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

class OrderClient {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080";
    private String token = null;

    Orders getOrderById(long orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> orderOneReq = new HttpEntity<>(headers);
        ResponseEntity<Orders> response = restTemplate.exchange(BASE_URL + "/api/v1/order/" + orderId, HttpMethod.GET, orderOneReq, Orders.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else return null;
    }

    Orders[] getOrderList() {
        String urlOrder = BASE_URL + "/api/v1/order";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> ordersReq = new HttpEntity<>(headers);
        ResponseEntity<Orders[]> ordersFromGetRequest = restTemplate.exchange(urlOrder, HttpMethod.GET, ordersReq, Orders[].class);
        HttpStatus rc = ordersFromGetRequest.getStatusCode();
        if (rc == HttpStatus.OK) {
            return ordersFromGetRequest.getBody();
        } else return null;
    }

    Orders createOrder(Orders orders) {
        String urlOrder = BASE_URL + "/api/v1/order";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> orderBody = new HttpEntity<>(orders, headers);
        Orders orderRes = restTemplate.postForObject(urlOrder, orderBody, Orders.class);
        if (orderRes != null) {
            return orderRes;
        } else return null;
    }

    Orders updateOrder(long orderId, Orders orders) {
        String urlOrder = BASE_URL + "/api/v1/order/" + orderId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> orderBody = new HttpEntity<>(orders, headers);
        ResponseEntity<Orders> orderUpd = restTemplate.exchange(urlOrder, HttpMethod.PUT, orderBody, Orders.class);
        if (orderUpd.getStatusCode() == HttpStatus.OK) {
            return orderUpd.getBody();
        } else return null;
    }

    void deleteOrder(long orderId) {
        String urlOrder = BASE_URL + "/api/v1/order/" + orderId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> orderReq = new HttpEntity<>(headers);
        restTemplate.exchange(urlOrder, HttpMethod.DELETE, orderReq, Void.class);
    }

    void authenticate(String login, String password) throws NullPointerException{
        //Получаем токен
        String urlAuthenticate = BASE_URL + "/api/v1/authenticate";
        AuthReq authReq = new AuthReq(login, password);
        //Добавляем тело в запрос
        HttpEntity<AuthReq> authReqBody = new HttpEntity<>(authReq);
        //Выполняем запрос
        ResponseEntity<AuthRes> authResult = restTemplate.postForEntity(urlAuthenticate, authReq, AuthRes.class);
        AuthRes authBody = null;
        if (authResult.getStatusCode() == HttpStatus.OK) {
            authBody = authResult.getBody();
        }
            this.token = authBody.getToken();
    }
}
