package ru.kazemirov.courierservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.courierservice.domain.Passport;

public interface PassportRepo extends CrudRepository<Passport, Integer> {
}
