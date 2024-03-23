package com.samis.security.auth;


import com.samis.security.JwtService;
import com.samis.user.Role;
import com.samis.user.User;
import com.samis.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    public  final  EmailValidator emailValidator;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> userDetails= userRepository.findByusername(request.getUsername());

        if(userDetails.isPresent())
        {
            User UserDetails= userDetails.get();
            Boolean isActive=UserDetails.getIs_active();
            if(isActive)
            {
                System.out.println("account active");
            }else
            {
                throw  new UserAlreadyExistsException("account not active");
            }

        }else
        {
         throw  new UserAlreadyExistsException("Account not found");
        }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );


        var user = userRepository.findByusername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    public AuthenticationResponse register(RegistrationRequest request) throws MessagingException, jakarta.mail.MessagingException {
        // Check if the user exists in the database
        Optional<User> existingUser = userRepository.findByUsername("admin@"+request.getUsername());
        if (existingUser.isPresent()) {
            // User already exists, handle the case appropriately
            // For example, you can throw an exception or return an error response
            throw new UserAlreadyExistsException("User with username already exists");
        }

        // User does not exist, proceed with user creation
        var user = User.builder()
                .username("admin@"+request.getUsername())
                .schoolName(request.getSchoolname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(emailValidator.ValidateEmail(request.getEmail()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        System.out.println(jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public List<User> getRegisteruser(String username) {

        Optional<User> userDetails=userRepository.findByusername(username);
        User user=new User();
        if(userDetails.isPresent())
        {
            User dbuserdetails=userDetails.get();
            user.setId(dbuserdetails.getId());
            user.setRole(dbuserdetails.getRole());
            user.setUsername(dbuserdetails.getUsername());
        }
        return List.of(user);

    }
    @Transactional
    public void manageAccount(Long id, boolean isactive) {

        Optional<User> school=userRepository.findById(id);
        if(school.isPresent())
        {
            User schooolDetail=school.get();
            schooolDetail.setIs_active(isactive);
            userRepository.save(schooolDetail);
        }

    }

    public List<User> getAllRegisteredSchools() {
        List<User> allSchools=userRepository.findAll();
        return allSchools;
    }
}
