package lib.others.services;

import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lib.others.dtos.BookRentDTO;
import lib.others.dtos.RegisterDTO;

@Service
public class OthersService {
    @Value("${CENTRAL_URL}")
    public String centralUrl;

    @Autowired
    HttpClient httpClient;
    @Autowired
    ObjectMapper objectMapper;

    public String register(RegisterDTO registerDTO) throws Exception {
        String response = sendRequest("register", objectMapper.writeValueAsString(registerDTO));
        if(response.equals("true")) {
            return "Register success";
        }

        return "Register failed";
    } 

    public String rent(BookRentDTO bookRentDTO) throws Exception {
        String response = sendRequest("rent", objectMapper.writeValueAsString(bookRentDTO));
        if(response.equals("true")) {
            return "Rent success";
        }

        return "Rent failed";
    }

    private String sendRequest(String route, String body) throws IOException, Exception {
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(centralUrl + "/" + route))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")
            .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new Exception("Can't connect to server");
        }
    }
}
