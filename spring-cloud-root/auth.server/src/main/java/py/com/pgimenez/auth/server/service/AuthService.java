package py.com.pgimenez.auth.server.service;

import py.com.pgimenez.auth.server.dto.TokenDto;
import py.com.pgimenez.auth.server.dto.UserDto;

public interface AuthService {

    TokenDto login(UserDto user);

    TokenDto validateToken(String accessToken);

}
