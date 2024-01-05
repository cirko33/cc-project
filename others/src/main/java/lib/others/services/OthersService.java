package lib.others.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import lib.others.dtos.BookRentDTO;
import lib.others.dtos.RegisterDTO;
import lib.others.dtos.ReturnBookDTO;
import lib.others.dtos.UserIdDTO;
import lib.others.exceptions.ResultException;
import lib.others.models.BookRental;
import lib.others.repositories.BookRentalRepository;

@Service
public class OthersService {
    @Value("${CENTRAL_URL}")
    public String centralUrl;

    @Autowired
    HttpClient httpClient;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookRentalRepository repo;

    public void register(RegisterDTO registerDTO) throws Exception {
        sendRequest("register", objectMapper.writeValueAsString(registerDTO));
    } 

    public void rent(BookRentDTO bookRentDTO) throws Exception {
        UserIdDTO userIdDTO = modelMapper.map(bookRentDTO, UserIdDTO.class);
        sendRequest("rent", objectMapper.writeValueAsString(userIdDTO));
    
        BookRental rent = modelMapper.map(bookRentDTO, BookRental.class);
        repo.save(rent);        
    }

    public void returnBook(ReturnBookDTO returnBookDTO) throws Exception {
        BookRental rent = repo.findByIsbn(returnBookDTO.getIsbn());
        if(rent == null) throw new ResultException("Book not found");

        UserIdDTO userIdDTO = modelMapper.map(returnBookDTO, UserIdDTO.class);
        sendRequest("return", objectMapper.writeValueAsString(userIdDTO));

        rent.setReturnDate(LocalDate.now());
        repo.save(rent);
    }

    private void sendRequest(String route, String body) throws Exception {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(centralUrl + "/" + route))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")
            .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) 
                throw new ResultException(response.body(), HttpStatus.valueOf(response.statusCode()));
            
        } catch (Exception e) {
            throw new Exception("Can't connect to server");
        }
    }
}
