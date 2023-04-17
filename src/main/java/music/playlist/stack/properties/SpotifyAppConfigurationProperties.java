package music.playlist.stack.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "music.playlist.stack")
//@ConfigurationProperties(prefix = "music.playlist.stack")
//@ConfigurationProperties(prefix = "music.playlist.stack.spotify")
public class SpotifyAppConfigurationProperties {

    private App app = new App();

    @Data
    public class App {
        private String clientId;
        private String redirectUri;
    }

}
