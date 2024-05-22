package com.example.springsecurity.controller;

import com.example.springsecurity.config.TokenService;
import com.example.springsecurity.dto.AuthenticationDto;
import com.example.springsecurity.dto.LoginResponseDto;
import com.example.springsecurity.dto.RegisterDto;
import com.example.springsecurity.model.Usuario;
import com.example.springsecurity.repository.UsuarioRepository;
import com.example.springsecurity.service.AuthorizationSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthorizationSevice sevice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        var user = sevice.buscarUsuarioPorLogin(data.login());
        return  ResponseEntity.ok(new LoginResponseDto(token, data.login(), user.getRole().toString()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto data){
        if(this.repository.findAllByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario usuario = new Usuario(data.login(), encryptedPassword, data.role());

        this.repository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
