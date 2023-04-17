package music.playlist.stack.controller;

import lombok.AllArgsConstructor;
import music.playlist.stack.config.SpotifyConnectionConfig;
import music.playlist.stack.constant.Template;
import music.playlist.stack.service.SpotifyUrlService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class HomeController {

    private final SpotifyUrlService spotifyUrlService;
    private final SpotifyConnectionConfig spotifyConnectionConfig = new SpotifyConnectionConfig();

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index(final Model model) {
        model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("myPlaylistUrl", spotifyConnectionConfig.getCurrentUserPlaylistUrl());

        return Template.HOME;

    }

    @GetMapping("/home")
    public String home(final Model model) {
        model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
        model.addAttribute("myPlaylistUrl", spotifyConnectionConfig.getCurrentUserPlaylistUrl());
        System.out.println("Redirecting to HOME");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH: " + auth);
        System.out.println(auth.getDetails());
        System.out.println("------------------------------------------------------------------------------------------------------");

        return Template.HOME;
    }


}
