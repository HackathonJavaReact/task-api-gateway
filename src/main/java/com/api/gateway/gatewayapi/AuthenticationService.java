package com.api.gateway.gatewayapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

@FeignClient(name = "authentication-service", url = "${zuul.routes.authentication.url}")
public interface AuthenticationService {

    @GetMapping("/isAuthenticated")
    @Headers("Content-Type: application/json")
    public boolean isAuthenticated(@RequestHeader("Authorization") String token);

}
