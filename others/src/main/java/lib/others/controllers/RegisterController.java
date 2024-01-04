package lib.others.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lib.others.dtos.RegisterDTO;
import lib.others.services.AuthService;

@RestController
public class RegisterController {
    @Autowired AuthService authService;


    @PostMapping("/register")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        String ret = authService.register(registerDTO);
        return ResponseEntity.ok(ret);
    }
}
