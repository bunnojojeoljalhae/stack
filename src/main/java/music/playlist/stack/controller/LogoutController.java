package music.playlist.stack.controller;

import music.playlist.stack.constant.ApiPath;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping(value = ApiPath.LOGOUT, produces = MediaType.TEXT_HTML_VALUE)
    public String logoutHandler(final HttpSession session) {
        session.invalidate();
        return "redirect:/?logout";
        /*return "redirect:/home";*/
    }
}
