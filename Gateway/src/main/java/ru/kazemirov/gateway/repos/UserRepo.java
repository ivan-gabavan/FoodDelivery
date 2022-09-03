package ru.kazemirov.gateway.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.gateway.domain.User;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);
}
