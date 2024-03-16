package com.samis.security.auth;

import com.samis.security.ExtractUserNameJwtMyCustom;
import com.samis.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public String register(
            @RequestBody RegistrationRequest request
    ) throws MessagingException, jakarta.mail.MessagingException {
        ResponseEntity.ok(service.register(request));
        return "Registered successfully";

//

    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));

    }

    @GetMapping("/user/")
    public List<User> getLogggedInUser(@RequestHeader("Authorization") String token) {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
        return service.getRegisteruser(username);
    }

    @GetMapping("/activateAccount/{id}/{isActive}")
    public void ManageAccount(@PathVariable("id") Long id, @PathVariable("isActive") boolean isactive) {
        service.manageAccount(id, isactive);
    }

    @GetMapping("/registeredSchools/")
    public List<User> getAllRegisteredSchools() {
        return service.getAllRegisteredSchools();
    }


}
