package music.playlist.stack.controller;

import lombok.AllArgsConstructor;
import music.playlist.stack.constant.ApiPath;
import music.playlist.stack.constant.Template;
import music.playlist.stack.exception.InvalidSearchException;
import music.playlist.stack.service.SearchService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping(value = ApiPath.SEARCH, produces = MediaType.TEXT_HTML_VALUE)
    public String getSearchResults(@RequestParam("searchKey") final String searchKey, final HttpSession session, final Model model) {
        String token = (String) session.getAttribute("accessToken");
        try {
            model.addAttribute("result", searchService.search(token, searchKey));
        } catch (InvalidSearchException e) {
            return Template.SEARCH_ERROR;
        }
        return Template.SEARCH_RESULTS;
    }

}
