package music.playlist.stack.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Followers;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode
@ToString(callSuper=true)
//@NoArgsConstructor
//@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class SpotifyUsers {

    private String name;
    private String country;
    private String display_name;
    private String email;
    private ExplicitContentSettings explicit_content;
    private ExternalUrl external_urls;
    private Followers followers;
    private String href;
    private String id;
    private List<Image> images;
    private String product;
    private String type;
    private String uri;

    @Builder
    public SpotifyUsers(String name, String country, String display_name, String email, ExplicitContentSettings explicit_content, ExternalUrl external_urls, Followers followers, String href, String id, List<Image> images, String product, String type, String uri) {
        this.name = name;
        this.country = country;
        this.display_name = display_name;
        this.email = email;
        this.explicit_content = explicit_content;
        this.external_urls = external_urls;
        this.followers = followers;
        this.href = href;
        this.id = id;
        this.images = images;
        this.product = product;
        this.type = type;
        this.uri = uri;
    }

}
