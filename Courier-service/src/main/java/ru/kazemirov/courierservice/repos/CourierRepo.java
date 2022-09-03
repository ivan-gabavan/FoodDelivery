package ru.kazemirov.courierservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.courierservice.domain.Courier;

public interface CourierRepo extends CrudRepository<Courier, Integer> {
}
