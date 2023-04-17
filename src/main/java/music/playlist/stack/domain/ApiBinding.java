package music.playlist.stack.domain;

import java.io.IOException;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass   // 클래스가 만들어지지 않는 기초 클래스라는 Annotation
public abstract class ApiBinding {

    private String accessToken;
    protected RestTemplate restTemplate;

    public ApiBinding(String token) {
        this.restTemplate = new RestTemplate();
        if (token != null) {
            this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(token));
        } else {
            this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String token) {
        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().add("Authorization", "Bearer " + token);
                return execution.execute(request, body);
            }
        };

        return interceptor;
    }

    private ClientHttpRequestInterceptor getNoTokenInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                throw new IllegalStateException("Can't access the API without an access token");
            }
        };
    }


}
