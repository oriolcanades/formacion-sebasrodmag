package es.formacion.demo.service;

import es.formacion.demo.model.dto.AppUserRequest;
import es.formacion.demo.model.dto.AppUserResponse;

public interface AppUserService {

    void createUser(AppUserRequest appUserRequest);
    AppUserResponse getUserById(Long id);

}
