package es.formacion.demo.controller;

import es.formacion.demo.model.dto.AppUserRequest;
import es.formacion.demo.model.dto.AppUserResponse;
import es.formacion.demo.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createUser(@RequestBody AppUserRequest appUserRequest) {
        log.info("Creating user: {}", appUserRequest);
        appUserService.createUser(appUserRequest);
        return "User created";
    }

    // Todo: Create a new endpoint that receives a GET request at /api/v1/users and returns a list of all users.

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserResponse getUserById(@PathVariable Long id) {
        log.info("Getting user");
        return appUserService.getUserById(id);
    }

    // Todo: Create a new endpoint that receives a PUT request at /api/v1/users/{id} and updates the user with the given id.

    // Todo: Create a new endpoint that receives a DELETE request at /api/v1/users/{id} and deletes the user with the given id.

}
