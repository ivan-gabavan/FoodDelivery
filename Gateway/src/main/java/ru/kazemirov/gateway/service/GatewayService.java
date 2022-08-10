package ru.kazemirov.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GatewayService {

    @Value("${shopping.cart.upl}")
    private String shoppingCartUrl;

    public void postProductInCart(HttpEntity<String> httpEntity){
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(
                shoppingCartUrl + "/product/post", httpEntity,
                String.class);
    }

}
