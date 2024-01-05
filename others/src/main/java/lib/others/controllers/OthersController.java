package lib.others.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lib.others.dtos.BookRentDTO;
import lib.others.dtos.RegisterDTO;
import lib.others.dtos.RentedBookDTO;
import lib.others.dtos.ReturnBookDTO;
import lib.others.services.OthersService;

@RestController
public class OthersController {
    @Autowired OthersService othersService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) throws Exception {
        othersService.register(registerDTO);
        return ResponseEntity.ok("Successfull registration");
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rent(@Valid @RequestBody BookRentDTO bookRentDTO) throws Exception {
        othersService.rent(bookRentDTO);
        return ResponseEntity.ok("Successfull rent");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@Valid @RequestBody ReturnBookDTO returnBookDTO) throws Exception  {
        othersService.returnBook(returnBookDTO);
        return ResponseEntity.ok("Successfull return");
    }

    @GetMapping("/rented")
    public ResponseEntity<List<RentedBookDTO>> rented() {
        return ResponseEntity.ok(othersService.getRented());
    }
}
