package music.playlist.stack.dto;

import lombok.Builder;
import lombok.Getter;
import music.playlist.stack.domain.user.Role;
import music.playlist.stack.domain.user.User;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    private String redirectUri;
    private String scopes;
    private String authorizationUri;


    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String redirectUri) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;

        this.redirectUri = redirectUri;

    }

    public static OAuthAttributes of(String withRegistrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("spotify".equals(withRegistrationId)) {
            return ofSpotify("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    private static OAuthAttributes ofSpotify(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .redirectUri((String) attributes.get("redirect_uri"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.ADMIN)
                .build();
    }
}
