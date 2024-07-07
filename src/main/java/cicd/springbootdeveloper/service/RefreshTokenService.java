package cicd.springbootdeveloper.service;

import cicd.springbootdeveloper.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import cicd.springbootdeveloper.domain.RefreshToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}

