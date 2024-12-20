package es.formacion.demo.service;

import es.formacion.demo.model.dto.AppUserRequest;
import es.formacion.demo.model.dto.AppUserResponse;

import java.util.List;

public interface AppUserService {

    void createUser(AppUserRequest appUserRequest);
    AppUserResponse getUserById(Long id);
    List<AppUserResponse> getAllUsers(); //Implements this method to list all users
    AppUserResponse updateAppUserById(Long id, AppUserRequest appUserRequest); //Check if the id exists and if so, re-assign the name and password
    void deleteUserById(Long id);//Checks if the id exists and if so, deletes it
}
