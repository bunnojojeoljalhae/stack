package music.playlist.stack.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component // to make a default bean
@Data // to create getter setter
@JsonIgnoreProperties(ignoreUnknown = true) // to ignore
@PropertySource("classpath:application.properties") // to read property values
public class SpotifyConnectionConfig {

    @Value("${spotify.client-id}") // TODO add relevant fields
//    @Value("${spotify.clientId}") // TODO add relevant fields
    @NotEmpty
    private String clientId;

    @Value("${spotify.client-secret}") // TODO add relevant fields
//    @Value("${spotify.clientSecret}") // TODO add relevant fields
    @NotEmpty
    private String clientSecret;

    @Value("${spotify.authorization.redirect_uri}")
    @NotEmpty
    private String redirectUri;

    @Setter(AccessLevel.NONE)
    private String registrationId = "spotify";

    @Setter(AccessLevel.NONE)
    private String clientName;

    @Setter(AccessLevel.NONE)
    private String authorizeUrl = "https://accounts.spotify.com/authorize";

    @Setter(AccessLevel.NONE)
    private String authorizeResponseType = "code";

    @Setter(AccessLevel.NONE)
    private String authorizationScope = "user-read-private playlist-read-private playlist-modify-public user-library-read user-read-recently-played streaming";

    @Setter(AccessLevel.NONE)
    private String tokenUri = "https://accounts.spotify.com/api/token";

    @Setter(AccessLevel.NONE)
    private String userInfoUrl = "https://api.spotify.com/v1/me";

    @Setter(AccessLevel.NONE)
    private String userProfileUrl = "https://api.spotify.com/v1/users/";

    @Setter(AccessLevel.NONE)
    private String searchItemUrl = "https://api.spotify.com/v1/search";

    @Setter(AccessLevel.NONE)
    private String currentUserPlaylistUrl = "https://api.spotify.com/v1/me/playlists";

    @Setter(AccessLevel.NONE)
    private String playerUrlRecentlyPlayed = "https://api.spotify.com/v1/me/player/recently-played";

    @Setter(AccessLevel.NONE)
    private String albumUrl = "https://api.spotify.com/v1/albums/";

    @Setter(AccessLevel.NONE)
    private String playlistUrl = "https://api.spotify.com/v1/playlists/";

    @Setter(AccessLevel.NONE)
    private String createPlaylists = "https://api.spotify.com/v1/users/{user_id}/playlists";

    @Setter(AccessLevel.NONE)
    private String featuredPlaylists = "https://api.spotify.com/v1/browse/featured-playlists";


}
