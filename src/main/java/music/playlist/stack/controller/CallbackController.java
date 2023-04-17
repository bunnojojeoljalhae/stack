package music.playlist.stack.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.exceptions.detailed.BadRequestException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import lombok.AllArgsConstructor;
import music.playlist.stack.constant.ApiPath;
import music.playlist.stack.constant.Template;
import music.playlist.stack.exception.NoTrackPlayingException;
import music.playlist.stack.service.*;
import org.apache.hc.core5.http.ParseException;
import org.checkerframework.checker.units.qual.Current;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpResponse;

@Controller
@AllArgsConstructor
public class CallbackController {

    private final SpotifyUrlService spotifyUrlService;
    private final AccessTokenService accessTokenService;
    private final ProfileDetailService userDetails;
    private final FeaturedPlaylistService featuredPlaylist;

    @GetMapping(value = ApiPath.CALLBACK, produces = MediaType.TEXT_HTML_VALUE)
    public String handleCallback(@RequestParam(value = "code", required = false) final String code,
                                 @RequestParam(value = "error", required = false) final String error,
                                 final Model model, final HttpSession session) throws BadRequestException {

        if (error != null) {
            model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
            return Template.CALLBACK_FAILURE;
        }

        session.setAttribute("code", code);
        // ↓ 이 새끼가 문제란다
        String token = accessTokenService.getToken(code);
        System.out.println("TOKEN: " + token);

        session.setAttribute("accessToken", token);
        model.addAttribute("accessToken", token);
        model.addAttribute("userName", userDetails.getUsername(token));

        try {
            model.addAttribute("getPlaylists", featuredPlaylist.getFeaturedPlaylists(token));
            model.addAttribute("display", 1);
        } catch (NoTrackPlayingException e) {
            System.out.println("CALLBACK ERROR " + e.getMessage());
            model.addAttribute("display", 0);
        }
        return Template.CALLBACK_SUCCESS;
    }


}
