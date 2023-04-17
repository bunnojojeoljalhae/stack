package music.playlist.stack.config.oauth;

import music.playlist.stack.config.SpotifyConnectionConfig;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

import java.net.URI;

@Configuration
public class SpotifyAuthorizationConfig {

    private static final SpotifyConnectionConfig spotifyConnectionConfig = new SpotifyConnectionConfig();

    private static final String clientId = spotifyConnectionConfig.getClientId();
    private static final String clientSecret = spotifyConnectionConfig.getClientSecret();
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8088/callback");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

}
