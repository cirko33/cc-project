package lib.central.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lib.central.dtos.RegisterDTO;
import lib.central.dtos.UserIdDTO;
import lib.central.services.CentralService;

@RestController
public class CentralController {
    @Autowired CentralService centralService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDTO registerDTO) throws Exception {
        centralService.register(registerDTO);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/rent")
    public ResponseEntity<Void> rent(@Valid @RequestBody UserIdDTO userIdDTO) throws Exception{
        centralService.rent(userIdDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return")
    public ResponseEntity<Void>  returnBook(UserIdDTO userIdDTO) throws Exception {
        centralService.returnBook(userIdDTO);
        return ResponseEntity.ok().build();
    }
}
