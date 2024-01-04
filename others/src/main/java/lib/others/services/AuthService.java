package lib.others.services;

import org.springframework.stereotype.Service;

import lib.others.dtos.RegisterDTO;

@Service
public class AuthService {
    
    public String register(RegisterDTO registerDTO) {
        return registerDTO.toString();
    }
}
