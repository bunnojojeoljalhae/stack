package music.playlist.stack.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import music.playlist.stack.config.SpotifyConnectionConfig;
import music.playlist.stack.properties.SpotifyAppConfigurationProperties;
import music.playlist.stack.utility.CodeChallengeUtility;
import music.playlist.stack.utility.CodeVerifierUtility;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(SpotifyAppConfigurationProperties.class)
public class SpotifyUrlService {

//    private final SpotifyAppConfigurationProperties spotifyAppConfigurationProperties;
    private final SpotifyConnectionConfig spotifyConnectionConfig;
    private String codeVerifier;
    
    // 2번으로 작동
    public String getAuthorizationURL() {
//        final var properties = spotifyAppConfigurationProperties.getApp();
        final var properties = spotifyConnectionConfig;

        final var codeVerifier = CodeVerifierUtility.generate();
        setCodeVerifier(codeVerifier);

        System.out.println("url service client id: " + properties.getClientId());
        System.out.println("url service redirect uri: " + properties.getRedirectUri());
        System.out.println("url service scope: " + properties.getAuthorizationScope());

//        return "https://accounts.spotify.com/en/authorize?client_id=" + properties.getClientId()
        return "https://accounts.spotify.com/authorize?client_id=" + properties.getClientId()
                + "&response_type=code&redirect_uri=" + properties.getRedirectUri()
                //+ "&code_challenge_method=S256&code_challenge=" + CodeChallengeUtility.generate(codeVerifier)
                + "&scope=" + properties.getAuthorizationScope();
                //+ "&scope=ugc-image-upload,user-read-playback-state,user-modify-playback-state,user-read-currently-playing,streaming,app-remote-control,user-read-email,user-read-private"
                //+ ",playlist-read-collaborative,playlist-modify-public,playlist-read-private,playlist-modify-private,user-library-modify,user-library-read,user-top-read,user-read-playback-position,user-read-recently-played,user-follow-read,user-follow-modify";
    }



}
