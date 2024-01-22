package com.faketri.market.contoller;

import com.faketri.market.payload.request.UserDto;
import com.faketri.market.service.user.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController("/api/auth")
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "signIn/signUp")
public class AuthController {
    // TODO : authentication controller (LOG IN, SING IN, LOG OUT)
    @Autowired
    private AuthService authService;

    @RequestMapping(path = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody UserDto userDto){
        authService.registration(userDto);
    }
    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody UserDto userDto){
        //UserDetails user = userDetailsServer.loadUserByUsername(userDto.getLogin());

    }
}
