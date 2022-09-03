package ru.kazemirov.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kazemirov.gateway.domain.FoodDeliveryUserDetails;
import ru.kazemirov.gateway.repos.UserRepo;

@Service
public class FoodDeliveryUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new FoodDeliveryUserDetails(userRepo.findUserByEmail(userName));
    }
}
