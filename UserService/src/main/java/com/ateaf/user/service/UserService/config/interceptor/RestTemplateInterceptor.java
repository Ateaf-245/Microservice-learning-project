package com.ateaf.user.service.UserService.config.interceptor;

import com.ateaf.user.service.UserService.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

    private OAuth2AuthorizedClientManager manager;

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
        logger.info("RestTemplateInterceptor");
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token =  manager.authorize(OAuth2AuthorizeRequest
                        .withClientRegistrationId("my-client")
                        .principal("Internal")
                        .build())
                .getAccessToken()
                .getTokenValue();

        logger.info("RestTemplateInterceptor Token {} "+token);

        request.getHeaders().add("Authorization","Bearer "+token);

        return execution.execute(request, body);
    }
}
