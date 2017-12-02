package me.acgee.manage.auth;

import me.acgee.manage.module.permission.dao.AdminRepository;
import me.acgee.manage.module.permission.entity.Admin;
import me.acgee.manage.secruity.JwtAuthenticationRequest;
import me.acgee.manage.secruity.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthService authService;
    private AdminRepository adminRepository;

    @Autowired
    public AuthController(AuthService authService, AdminRepository adminRepository){
        this.authService = authService;
        this.adminRepository = adminRepository;
    }

    @RequestMapping(value = "${jwt.route.authentication.login}", method = RequestMethod.POST)
    public Auth createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final Admin admin = adminRepository.findByAdminName(authenticationRequest.getUsername());
        //Return the token
        return new Auth(token, admin);
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }
    
    @PostMapping(value = "${jwt.route.authentication.register}")
    public Object register(@RequestBody Admin addAdmin) throws AuthenticationException{
        return authService.register(addAdmin);
    }
}
