package com.example.springsecurity.dto;

import com.example.springsecurity.model.UsuarioRole;

public record RegisterDto(String login, String password, UsuarioRole role) {
}
