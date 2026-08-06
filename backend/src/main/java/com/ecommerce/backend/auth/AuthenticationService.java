package com.ecommerce.backend.auth;

import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.model.Role;
import com.ecommerce.backend.repository.CustomerRepository;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {



    private final CustomerRepository customerRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;





    // ==========================
    // LOGIN
    // ==========================


    public AuthenticationResponse login(LoginRequest request) {



        authenticationManager.authenticate(


                new UsernamePasswordAuthenticationToken(


                        request.getEmail(),


                        request.getPassword()


                )


        );







        Customer customer = customerRepository

                .findByEmail(request.getEmail())

                .orElseThrow(() ->

                        new RuntimeException(

                                "Usuario no encontrado"

                        )

                );








        String jwt = jwtService.generateToken(

                customer.getEmail()

        );







        return AuthenticationResponse.builder()


                .token(jwt)


                .customerId(customer.getId())


                .build();



    }









    // ==========================
    // REGISTER
    // ==========================


    public AuthenticationResponse register(RegisterRequest request) {





        if(customerRepository.existsByEmail(request.getEmail())) {


            throw new RuntimeException(

                    "El email ya está registrado"

            );


        }







        Role customerRole = roleRepository

                .findByName("CUSTOMER")

                .orElseThrow(() ->

                        new RuntimeException(

                                "Rol CUSTOMER no encontrado"

                        )

                );









        Customer customer = Customer.builder()



                .firstName(request.getFirstName())



                .lastName(request.getLastName())



                .email(request.getEmail())



                .password(


                        passwordEncoder.encode(


                                request.getPassword()


                        )


                )



                .role(customerRole)



                .active(true)



                .emailVerified(false)



                .build();







        customerRepository.save(customer);









        String jwt = jwtService.generateToken(

                customer.getEmail()

        );









        return AuthenticationResponse.builder()



                .token(jwt)



                .customerId(customer.getId())



                .build();



    }



}
