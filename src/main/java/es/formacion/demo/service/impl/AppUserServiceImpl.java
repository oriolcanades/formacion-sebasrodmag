package es.formacion.demo.service.impl;

import es.formacion.demo.exception.AppUserNotFoundException;
import es.formacion.demo.model.dto.AppUserRequest;
import es.formacion.demo.model.dto.AppUserResponse;
import es.formacion.demo.model.entity.AppUser;
import es.formacion.demo.repository.AppUserRepository;
import es.formacion.demo.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void createUser(AppUserRequest appUserRequest) {
        log.info("Creating user: {}", appUserRequest);
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserRequest.username());
        appUser.setPassword(String.valueOf(appUserRequest.password()));
        appUserRepository.save(appUser);
        log.info("User created");
    }

    @Override
    public AppUserResponse getUserById(Long id) {
        log.info("Getting user");
        Optional<AppUser> appUserFound = appUserRepository.findById(id);
        if(appUserFound.isPresent()) {
            return new AppUserResponse(
                    String.valueOf(appUserFound.get().getId()),
                    appUserFound.get().getUsername()
            );
        } else {
            throw new AppUserNotFoundException("User not found");
        }
    }
    /**
     * The getAllUsers function returns a list of users.
     * The list of users returned by AppUsersResponse.findAll() 
     * is a DTO with the ID and name stored in a list, and this list is stored in a map
     */

    @Override
    public List<AppUserResponse> getAllUsers() {
        log.info("Getting all users");
        List<AppUser> users = StreamSupport
        .stream(appUserRepository.findAll().spliterator(),false).toList();
        if(appUserRepository.count()!=0){
            return users.stream().map(user->new AppUserResponse(
                String.valueOf(user.getId()), user.getUsername())).toList();
        }else{
            throw new AppUserNotFoundException("No users found");
        }
        
    }

    /**
     * I intended to call the getUserById() function, but it returns a DTO, 
     * and I would need it to return an AppUser Object. 
     * So I will look for the user by id.
     */

    @Override
    public AppUserResponse updateAppUserById(Long id, AppUserRequest appUserRequest) {
        log.info("Updating user with id:{}.", id);
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new AppUserNotFoundException("User with ID: "+id+" not found."));
        appUser.setUsername(appUserRequest.username());
        appUser.setPassword(String.valueOf(appUserRequest.password()));
        appUserRepository.save(appUser);
        log.info("User whith ID:{}. updated", id);

        return new AppUserResponse(String.valueOf(appUser.getId()), appUser.getUsername());
    }

    @Override
    public void deleteUserById(Long id){
        log.info("Deleting user with id:{}", id);
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new AppUserNotFoundException("User with ID: "+id+" not found."));
        appUserRepository.delete(appUser);
        log.info("User with ID:{}. deleted", id);
    }
}
