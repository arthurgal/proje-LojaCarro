package com.example.springsecurity.repository;

import com.example.springsecurity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findAllByLogin(String login);

    Usuario findUsuarioByLogin(String login);
}
