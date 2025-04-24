package py.com.pgimenez.auth.server.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import py.com.pgimenez.auth.server.dto.TokenDto;
import py.com.pgimenez.auth.server.dto.UserDto;
import py.com.pgimenez.auth.server.entity.UserEntity;
import py.com.pgimenez.auth.server.helper.JwtHelper;
import py.com.pgimenez.auth.server.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    private static final String USER_EXCEPTION_MESSAGE = "Error to auth user";

    @Override
    public TokenDto login(UserDto user) {
        final var userFromDB = this.userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MESSAGE));
        this.validPassword(user, userFromDB);
        return TokenDto.builder()
                .accessToken(jwtHelper.createToken(userFromDB.getUsername()))
                .build();
    }

    @Override
    public TokenDto validateToken(String accessToken) {
        if (!this.jwtHelper.validateToken(accessToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MESSAGE);
        }
        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    private void validPassword(UserDto userDto, UserEntity userEntity) {
        if (!this.passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MESSAGE);
        }
    }

}
