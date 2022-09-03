package ru.kazemirov.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kazemirov.gateway.domain.Role;
import ru.kazemirov.gateway.domain.User;
import ru.kazemirov.gateway.repos.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Value("${client.service.url}")
    private String clientServiceUrl;

    @Value("${courier.service.url}")
    private String courierServiceUrl;

    @Value("${seller.service.url}")
    private String sellerServiceUrl;


    public void addUser(Role role, String registrationData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(registrationData);
        User newUser = new User(
                null,
                jsonNode.get("email").asText(),
                jsonNode.get("phone").asText(),
                jsonNode.get("password").asText(),
                true,
                Collections.singleton(role)
        );

        Integer newUserId =  userRepo.save(newUser).getId();

        String urlUserRoleService = switch (role){
            case CLIENT -> clientServiceUrl;
            case COURIER -> courierServiceUrl;
            case SELLER -> sellerServiceUrl;
            default -> throw new Error("Wrong role!");
        };

        ObjectNode jsonRegistrationData = (ObjectNode) new ObjectMapper().readTree(registrationData);
        ArrayList<String> fieldToRemove = new ArrayList<>();
        fieldToRemove.add("email");
        fieldToRemove.add("password");
        fieldToRemove.add("phone");

        jsonRegistrationData.remove(fieldToRemove);

        jsonRegistrationData.set("id", new IntNode(newUserId));
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(
                urlUserRoleService + "/add/user", new HttpEntity<>(jsonRegistrationData),
                String.class);
    }
    public List<User> getAllUsers (){
        ArrayList<User> users = new ArrayList<>();
        for (User user: userRepo.findAll()){
            users.add(user);
        }
        return users;
    }
}
