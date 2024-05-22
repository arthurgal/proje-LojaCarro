package com.example.springsecurity.service;

import com.example.springsecurity.model.Usuario;
import com.example.springsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationSevice implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findAllByLogin(username);
    }

    public Usuario buscarUsuarioPorLogin(String login){
        return  repository.findUsuarioByLogin(login);
    }
}
