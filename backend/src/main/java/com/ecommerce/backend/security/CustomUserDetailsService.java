package com.ecommerce.backend.security;

import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Customer customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado"
                        ));

        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .authorities(
                        List.of(
                                new SimpleGrantedAuthority(
                                        customer.getRole().getName()
                                )
                        )
                )
                .disabled(!customer.getActive())
                .build();

    }

}
