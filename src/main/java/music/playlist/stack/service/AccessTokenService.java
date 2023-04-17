package music.playlist.stack.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wrapper.spotify.exceptions.detailed.BadRequestException;
import lombok.RequiredArgsConstructor;
import music.playlist.stack.config.SpotifyConnectionConfig;
import music.playlist.stack.dto.AccessTokenDto;
import music.playlist.stack.properties.SpotifyAppConfigurationProperties;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(value = SpotifyAppConfigurationProperties.class)
public class AccessTokenService {

    private final SpotifyUrlService spotifyUrlService;
    private final RestTemplate restTemplate;
    private final SpotifyAppConfigurationProperties spotifyAppConfigurationProperties;
    private final SpotifyConnectionConfig spotifyConnectionConfig;
    private static final String URL = "https://accounts.spotify.com/api/token";

    public String getToken(String code) {
        final var properties = spotifyConnectionConfig;
        //final var properties = spotifyAppConfigurationProperties.getApp();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", properties.getRedirectUri());
        map.add("client_id", properties.getClientId());
        map.add("client_secret", properties.getClientSecret());
        map.add("code_verifier", spotifyUrlService.getCodeVerifier());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

//        ResponseEntity<AccessTokenDto> response = restTemplate.exchange(URL, HttpMethod.POST, request, AccessTokenDto.class);
        ResponseEntity<AccessTokenDto> response = restTemplate.postForEntity(URL, request, AccessTokenDto.class);
        return response.getBody().getAccess_token();
    }

}
