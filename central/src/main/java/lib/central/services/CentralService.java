package lib.central.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lib.central.dtos.RegisterDTO;
import lib.central.dtos.UserIdDTO;
import lib.central.exceptions.BadRequestException;
import lib.central.exceptions.NotFoundException;
import lib.central.models.User;
import lib.central.repositories.UserRepository;

@Service
public class CentralService {
    @Autowired UserRepository userRepository;
    @Autowired ModelMapper modelMapper;

    public void register(RegisterDTO registerDTO) throws Exception  {
        User user = userRepository.findByJmbg(registerDTO.getJmbg()).orElse(null);
        if(user != null) throw new BadRequestException("User already exists");

        user = modelMapper.map(registerDTO, User.class);
        userRepository.saveAndFlush(user);
    }
    
    public void rent(UserIdDTO userIdDTO) throws Exception {
        User user = userRepository.findById(userIdDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        
        if(user.getRentedBooks() >= 3) throw new BadRequestException("Too Many Books");
        
        user.setRentedBooks(user.getRentedBooks() + 1);
        userRepository.saveAndFlush(user);
    }

    public void returnBook(UserIdDTO userIdDTO) throws Exception {
        System.out.println(userIdDTO.getUserId());
        User user = userRepository.findById(userIdDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));

        user.setRentedBooks(user.getRentedBooks() > 0 ? user.getRentedBooks() - 1 : 0);
        userRepository.saveAndFlush(user);
    }
}
