package lib.others.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lib.others.dtos.BookRentDTO;
import lib.others.dtos.RegisterDTO;
import lib.others.services.OthersService;

@RestController
public class RegisterController {
    @Autowired OthersService othersService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) throws Exception {
        String ret = othersService.register(registerDTO);
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rent(@Valid @RequestBody BookRentDTO bookRentDTO) throws Exception {
        String ret = othersService.rent(bookRentDTO);
        return ResponseEntity.ok(ret);
    }
}
