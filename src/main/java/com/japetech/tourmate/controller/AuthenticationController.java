package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.LoginDto;
import com.japetech.tourmate.dtos.TokenJWT;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto loginDto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuarioModel) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));

    }

}
