package lib.others.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import lib.others.dtos.BookRentDTO;
import lib.others.dtos.RegisterDTO;
import lib.others.dtos.RentedBookDTO;
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
        BookRental rent = modelMapper.map(bookRentDTO, BookRental.class);
        rent.setId(null);
        UserIdDTO userIdDTO = modelMapper.map(bookRentDTO, UserIdDTO.class);
        sendRequest("rent", objectMapper.writeValueAsString(userIdDTO));
    
        repo.saveAndFlush(rent);        
    }

    public void returnBook(ReturnBookDTO returnBookDTO) throws Exception {
        BookRental rent = repo.findById(returnBookDTO.getBookId())
            .orElseThrow(() -> new ResultException("Book not found"));
        
        sendRequest("return", objectMapper.writeValueAsString(new UserIdDTO(rent.getUserId())));

        rent.setReturnDate(LocalDate.now());
        repo.saveAndFlush(rent);
    }

    private void sendRequest(String route, String body) throws Exception {
        HttpResponse<String> response;
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(centralUrl + "/" + route))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")
            .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());           
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception("Can't connect to server");
        }
        
        if(response.statusCode() != 200) 
            throw new ResultException(response.body(), HttpStatus.valueOf(response.statusCode()));
    }

    public List<RentedBookDTO> getRented() {
        List<BookRental> rents = repo.findByReturnDateIsNull();
        return modelMapper.map(rents, new TypeToken<List<RentedBookDTO>>() {}.getType());
    }
}
