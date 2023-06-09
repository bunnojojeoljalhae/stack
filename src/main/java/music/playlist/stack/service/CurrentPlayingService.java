package music.playlist.stack.service;

import lombok.RequiredArgsConstructor;
import music.playlist.stack.exception.NoTrackPlayingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class CurrentPlayingService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/player/currently-playing";

    public LinkedHashMap getCurrentPlaying(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameter", headers);

        ResponseEntity<Object> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
        if(response.getStatusCodeValue() == 204) {
            throw new NoTrackPlayingException();
        }
        LinkedHashMap result = (LinkedHashMap) response.getBody();
        return result;
    }
}
