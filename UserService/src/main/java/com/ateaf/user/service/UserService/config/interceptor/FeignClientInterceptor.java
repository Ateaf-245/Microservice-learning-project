package com.ateaf.user.service.UserService.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);

    @Autowired
    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {

        String token =  manager.authorize(OAuth2AuthorizeRequest
                    .withClientRegistrationId("my-client")
                    .principal("Internal")
                    .build())
                .getAccessToken()
                .getTokenValue();

        logger.info("FeignClientInterceptor : {}" +token);

        template.header("Authorization","Bearer "+ token); //make sure to add space after Bearer

    }
}
