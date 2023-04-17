package music.playlist.stack.controller;

import lombok.AllArgsConstructor;
import music.playlist.stack.constant.ApiPath;
import music.playlist.stack.constant.Template;
import music.playlist.stack.exception.NoTrackPlayingException;
import music.playlist.stack.service.FeaturedPlaylistService;
import music.playlist.stack.service.ProfileDetailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
public class RedirectController {

    private final ProfileDetailService userDetails;
//    private final FeaturedPlaylistService featuredPlaylist;

    @GetMapping(value = ApiPath.REDIRECT, produces = MediaType.TEXT_HTML_VALUE)
    public String redirectToCallbackSuccess(final HttpSession session, final Model model) {

        String token = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", token);
        model.addAttribute("userName", userDetails.getUsername(token));

        try {
//            model.addAttribute("playlist", featuredPlaylist.getFeaturedPlaylists(token));
            model.addAttribute("display", 1);
        } catch (NoTrackPlayingException exception) {
            model.addAttribute("display", 0);
        }
        return Template.CALLBACK_SUCCESS;
    }
}
