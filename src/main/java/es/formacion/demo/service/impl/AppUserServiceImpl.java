package es.formacion.demo.service.impl;

import es.formacion.demo.exception.AppUserNotFoundException;
import es.formacion.demo.model.dto.AppUserRequest;
import es.formacion.demo.model.dto.AppUserResponse;
import es.formacion.demo.model.entity.AppUser;
import es.formacion.demo.repository.AppUserRepository;
import es.formacion.demo.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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

}
