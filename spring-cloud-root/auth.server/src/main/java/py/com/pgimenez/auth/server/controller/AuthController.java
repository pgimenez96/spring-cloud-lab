package py.com.pgimenez.auth.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.pgimenez.auth.server.dto.TokenDto;
import py.com.pgimenez.auth.server.dto.UserDto;
import py.com.pgimenez.auth.server.service.AuthService;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<TokenDto> jwtCreate(@RequestBody UserDto user) {
        return ResponseEntity.ok(this.authService.login(user));
    }

    @PostMapping(path = "jwt")
    public ResponseEntity<TokenDto> jwtValidate(@RequestHeader String accessToken) {
        return ResponseEntity.ok(this.authService.validateToken(accessToken));
    }

}
