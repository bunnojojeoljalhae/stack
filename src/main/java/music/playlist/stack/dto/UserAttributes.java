package music.playlist.stack.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import se.michaelthelin.spotify.model_objects.specification.User;

import java.util.List;
import java.util.Map;

@Data
@Getter
public abstract class UserAttributes implements UserDetails, OAuth2User {

    private User user;
    private Map<String, Object> attributes;
    private SpotifyUsers spotifyUsers;

    public UserAttributes(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

}
