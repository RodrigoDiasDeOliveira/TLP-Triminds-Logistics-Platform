package com.triminds.tlp.gateway.security.filter;

import com.triminds.tlp.security.jwt.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtGatewayFilter extends AbstractGatewayFilterFactory<Object> {

    private final JwtService jwtService;

    public JwtGatewayFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Missing token");
            }

            String token = authHeader.substring(7);

            // valida token no gateway
            jwtService.validateToken(token);

            String email = jwtService.extractEmail(token);
            String role = jwtService.extractRole(token);

            // injeta headers para microservices
            var mutatedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-User-Email", email)
                    .header("X-User-Role", role)
                    .build();

            return chain.filter(
                    exchange.mutate().request(mutatedRequest).build()
            );
        };
    }
}