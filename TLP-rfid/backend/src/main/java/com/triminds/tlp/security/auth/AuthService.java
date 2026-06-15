package com.triminds.tlp.security.auth;

import com.triminds.tlp.company.model.Company;
import com.triminds.tlp.company.repository.CompanyRepository;
import com.triminds.tlp.security.config.Role;
import com.triminds.tlp.security.jwt.JwtService;
import com.triminds.tlp.user.model.User;
import com.triminds.tlp.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository users;
    private final CompanyRepository companies;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(
            UserRepository users,
            CompanyRepository companies,
            PasswordEncoder encoder,
            JwtService jwt
    ) {
        this.users = users;
        this.companies = companies;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public TokenResponse login(LoginRequest req) {
        User user = users.findByEmail(req.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Credenciais inválidas"
                ));

        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Credenciais inválidas"
            );
        }

        String tenantId = user.getCompany() != null
                ? user.getCompany().getTenantId()
                : "default";

        String token = jwt.generateToken(
                user.getEmail(),
                user.getRole().name(),
                tenantId
        );

        return TokenResponse.bearer(token, 86_400);
    }

    public TokenResponse register(RegisterRequest req) {
        if (users.existsByEmail(req.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado"
            );
        }

        Company company = companies.findByTenantId(req.companyTenantId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada"
                ));

        User user = new User();
        user.setEmail(req.email());
        user.setPassword(encoder.encode(req.password()));
        user.setRole(Role.OPERATOR);
        user.setCompany(company);

        users.save(user);

        String token = jwt.generateToken(
                user.getEmail(),
                user.getRole().name(),
                company.getTenantId()
        );

        return TokenResponse.bearer(token, 86_400);
    }
}
