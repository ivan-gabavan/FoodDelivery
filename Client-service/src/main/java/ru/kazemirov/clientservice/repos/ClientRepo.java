package ru.kazemirov.clientservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.clientservice.domain.Client;

public interface ClientRepo extends CrudRepository<Client, Integer> {
}
