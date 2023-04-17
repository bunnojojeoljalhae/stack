package music.playlist.stack.controller;

import music.playlist.stack.constant.ApiPath;
import music.playlist.stack.constant.Template;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorNotFoundController implements ErrorController {

    @RequestMapping(value = ApiPath.ERROR, produces = MediaType.TEXT_HTML_VALUE)
    public String handleError() {
        return Template.ERROR;
    }

}
