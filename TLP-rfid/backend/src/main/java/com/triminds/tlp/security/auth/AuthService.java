package com.triminds.tlp.security.auth;

import com.triminds.tlp.company.model.Company;
import com.triminds.tlp.company.repository.CompanyRepository;
import com.triminds.tlp.security.config.Role;
import com.triminds.tlp.security.jwt.JwtService;
import com.triminds.tlp.user.model.User;
import com.triminds.tlp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository users;
    private final CompanyRepository companies;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public TokenResponse login(LoginRequest req) {
        User u = users.findByEmail(req.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));
        if (!encoder.matches(req.password(), u.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }
        String tenantId = u.getCompany() != null ? u.getCompany().getTenantId() : "default";
        String token = jwt.generateToken(u.getEmail(), u.getRole().name(), tenantId);
        return TokenResponse.bearer(token, 86_400);
    }

    public TokenResponse register(RegisterRequest req) {
        if (users.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        Company company = companies.findByTenantId(req.companyTenantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        User u = User.builder()
                .email(req.email())
                .password(encoder.encode(req.password()))
                .role(Role.OPERATOR)
                .company(company)
                .build();
        users.save(u);

        String token = jwt.generateToken(u.getEmail(), u.getRole().name(), company.getTenantId());
        return TokenResponse.bearer(token, 86_400);
    }
}
