package music.playlist.stack.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RestTemplateResponseException implements ResponseErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
//        DefaultResponseErrorHandler defaultResponseErrorHandler = new DefaultResponseErrorHandler();
//        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        logger.debug("## handleError : {}", response);
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                //throw new NotFoundException();
            }
        }
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseException());
    }
}