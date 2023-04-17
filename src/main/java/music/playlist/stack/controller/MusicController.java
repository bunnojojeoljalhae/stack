package music.playlist.stack.controller;

import lombok.AllArgsConstructor;
import music.playlist.stack.constant.ApiPath;
import music.playlist.stack.constant.Template;
import music.playlist.stack.service.FeaturedPlaylistService;
import music.playlist.stack.service.NewReleasedService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class MusicController {

    private final FeaturedPlaylistService featuredPlaylistService;
    private final NewReleasedService newReleasedService;

    @GetMapping(value = ApiPath.FEATURED_PLAYLIST, produces = MediaType.TEXT_HTML_VALUE)
    public String featuredPlaylistsHandler(final HttpSession session, final Model model) {

        model.addAttribute("playlists", featuredPlaylistService.getFeaturedPlaylists((String)session.getAttribute("accessToken")));

        return Template.FEATURED_PLAYLISTS;
    }

    @GetMapping(value = ApiPath.NEW_RELEASE, produces = MediaType.TEXT_HTML_VALUE)
    public String newReleasesHandler(final HttpSession session, final Model model) {
        model.addAttribute("releases", newReleasedService.getReleases((String) session.getAttribute("accessToken")));
        return Template.NEW_RELEASES;
    }

/*
    @GetMapping(value = ApiPath.FEATURED_PLAYLIST, produces = MediaType.TEXT_HTML_VALUE)
    public String featuredPlaylistsHandler(@RequestParam(value = "code", required = false) final String code, final HttpSession session, final Model model) {
        String token = accessTokenService.getToken(code);

        session.setAttribute("code", code);
        session.setAttribute("accessToken", token);
        model.addAttribute("playlists", featuredPlaylistService.getPlaylists(token));

        return Template.FEATURED_PLAYLISTS;
    }
*/

}
