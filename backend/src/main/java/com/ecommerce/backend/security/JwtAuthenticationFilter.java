package com.ecommerce.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        System.out.println("=================================");
        System.out.println("HEADER: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("NO HAY TOKEN");

            filterChain.doFilter(request, response);

            return;

        }

        String jwt = authHeader.substring(7);

        System.out.println("JWT: " + jwt);

        String email = jwtService.extractUsername(jwt);

        System.out.println("EMAIL: " + email);

        if (email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("USER: " + userDetails.getUsername());
            System.out.println("AUTHORITIES: " + userDetails.getAuthorities());

            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {

                System.out.println("TOKEN VÁLIDO");

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(

                                userDetails,

                                null,

                                userDetails.getAuthorities()

                        );

                authToken.setDetails(

                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)

                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

                System.out.println("USUARIO AUTENTICADO");

            } else {

                System.out.println("TOKEN INVÁLIDO");

            }

        }

        filterChain.doFilter(request, response);

    }

}
