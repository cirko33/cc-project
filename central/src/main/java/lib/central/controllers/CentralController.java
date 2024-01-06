package lib.central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lib.central.dtos.RegisterDTO;
import lib.central.dtos.UserIdDTO;
import lib.central.models.User;
import lib.central.repositories.UserRepository;
import lib.central.services.CentralService;

@RestController
public class CentralController {
    @Autowired CentralService centralService;
    //FOR TEST
    @Autowired UserRepository userRepository;

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
    public ResponseEntity<Void> returnBook(@Valid @RequestBody UserIdDTO userIdDTO) throws Exception {
        System.out.println(userIdDTO.getUserId());
        centralService.returnBook(userIdDTO);
        return ResponseEntity.ok().build();
    }

    //TESTING CONTAINTER
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }

    //TESTING DB
    @GetMapping("/test-db")
    public ResponseEntity<List<User>> testDb() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
