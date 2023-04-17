package music.playlist.stack.config.oauth;

import lombok.RequiredArgsConstructor;
import music.playlist.stack.config.SpotifyConnectionConfig;
import music.playlist.stack.domain.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity  // Spring Security 설정 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2LoginSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // spotify
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.spotifyClientRegistration());
//    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                    .and()
                .authorizeRequests().antMatchers("/", "/home", "/login/**", "/webjars/**", "/error**", "/oauth2/authorization/**", "/v1/**", "/css/**" , "/js/**", "/images/**", "/h2-console/**").permitAll()
                .anyRequest().permitAll()
                    .and()
                .oauth2Login()
                .userInfoEndpoint();

        //http.getConfigurer().oidc(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }

    private ClientRegistration spotifyClientRegistration() {
        SpotifyConnectionConfig spotifyConnectionConfig = new SpotifyConnectionConfig();
        User user = new User();

        // @formatter:off
        return ClientRegistration
                .withRegistrationId(spotifyConnectionConfig.getRegistrationId())
                .clientId(spotifyConnectionConfig.getClientId())
                .clientSecret(spotifyConnectionConfig.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(spotifyConnectionConfig.getRedirectUri())
                .scope(spotifyConnectionConfig.getAuthorizationScope())
//                .scope(
//                        "ugc-image-upload", "streaming",
//                        "user-read-private", "user-read-email",
//                        "user-read-playback-state", "user-modify-playback-state",
//                        "playlist-read-private", "playlist-modify-private", "playlist-modify-public"
//                )
                .authorizationUri(spotifyConnectionConfig.getAuthorizeUrl())
                .tokenUri(spotifyConnectionConfig.getTokenUri())
                .userInfoUri(spotifyConnectionConfig.getUserInfoUrl())
                .userNameAttributeName("display_name")
//                .clientName(user.getName())
                .clientName(spotifyConnectionConfig.getClientName())
                .build();
        // @formatter:on
/*
        // @formatter:off
        return ClientRegistration
                .withRegistrationId(spotifyConnectionConfig.getRegistrationId())
                .clientId(spotifyConnectionConfig.getClientId())
                .clientSecret(spotifyConnectionConfig.getClientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(spotifyConnectionConfig.getRedirectUri())
                .scope(
                        "ugc-image-upload", "streaming",
                        "user-read-private", "user-read-email",
                        "user-read-playback-state", "user-modify-playback-state",
                        "playlist-read-private", "playlist-modify-private", "playlist-modify-public"
                )
                .authorizationUri(spotifyConnectionConfig.getAuthorizeUrl())
                .tokenUri(spotifyConnectionConfig.getTokenUri())
                .userInfoUri(spotifyConnectionConfig.getUserInfoUrl())
                .userNameAttributeName("display_name")
                .clientName(user.getName())
                //.clientName(spotifyConnectionConfig.getClientName())
                .build();
        // @formatter:on
*/
    }

}


