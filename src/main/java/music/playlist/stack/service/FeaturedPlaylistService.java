package music.playlist.stack.service;

import lombok.RequiredArgsConstructor;
import music.playlist.stack.config.SpotifyConnectionConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;


@Service
@RequiredArgsConstructor
public class FeaturedPlaylistService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/browse/featured-playlists?limit=50";

    public Object getFeaturedPlaylists(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        System.out.println("TOKEN: " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        System.out.println("ENTITY: " + entity);

        ResponseEntity<Object> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();

        return result;
    }

}
