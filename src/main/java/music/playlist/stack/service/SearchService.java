package music.playlist.stack.service;

import lombok.RequiredArgsConstructor;
import music.playlist.stack.exception.InvalidSearchException;
import music.playlist.stack.utility.SearchQueryFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/search?q=SEARCH_QUERY&type=album,artist,playlist,track&limit=3";

    public Object search(String token, String searchQuery) {

        if(!isValid(token, searchQuery)) {
            throw new InvalidSearchException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                URL.replace("SEARCH_QUERY", SearchQueryFormatter.format(searchQuery)), HttpMethod.GET, entity, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();

        return result;

    }

        public boolean isValid(String token, String searchQuery) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<Object> response = restTemplate.exchange(
                    URL.replace("SEARCH_QUERY", SearchQueryFormatter.format(searchQuery)), HttpMethod.GET, entity, Object.class);

            LinkedHashMap result = (LinkedHashMap) response.getBody();

            LinkedHashMap albums = (LinkedHashMap) result.get("albums");
            ArrayList itemsAlbums = (ArrayList) albums.get("items");

            LinkedHashMap artists = (LinkedHashMap) result.get("artists");
            ArrayList itemsArtists = (ArrayList) albums.get("items");

            LinkedHashMap tracks = (LinkedHashMap) result.get("tracks");
            ArrayList itemsTracks = (ArrayList) albums.get("items");

            LinkedHashMap playlists = (LinkedHashMap) result.get("playlists");
            ArrayList itemsPlaylists = (ArrayList) albums.get("items");

            if ((itemsAlbums.size() + itemsArtists.size() + itemsPlaylists.size() + itemsTracks.size()) == 0) {
                return false;
            }
            return true;
        }


}
