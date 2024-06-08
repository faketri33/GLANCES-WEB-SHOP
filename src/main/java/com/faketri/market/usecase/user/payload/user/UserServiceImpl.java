package com.faketri.market.usecase.user.payload.user;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.user.gateway.repository.UserRepository;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.user.payload.user.dto.UserUpdateRequest;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import com.faketri.market.usecase.file.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * The type User service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userImpl;
    private final ImageService imageService;
    private final FileUploadService fileService;

    @Autowired
    public UserServiceImpl(UserRepository userImpl, ImageService imageService, FileUploadService fileService) {
        this.userImpl = userImpl;
        this.imageService = imageService;
        this.fileService = fileService;
    }

    public Users findByLogin(String login) {
        return userImpl.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Пользователь с логином " + login + " не найден"));
    }

    @Override
    public boolean existsByLogin(String login) {
        return userImpl.existsByLogin(login);
    }

    @Override
    public boolean existsByEmail(String login) {
        return false;
    }

    @Override
    public Image updateUserImage(MultipartFile image) {
       final Users user = getCurrentUser();

        log.info("updateUserImage: user id - " + user.getId());

        final Image deleteImage = user.getProfileImage();
        user.setProfileImage(fileService.saveImage(FileUploadService.USER_PATH, user.getLogin(), image));

        final Users savedUser = save(user);
        final Image returnedImage = savedUser.getProfileImage();

        imageService.delete(deleteImage);

        return returnedImage;
    }

    @Override
    public void updateUserData(UserUpdateRequest userUpdateRequest) {
        final Users user = getCurrentUser();

        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());
        user.setDateOfBirthday(userUpdateRequest.getDateOfBirthday());
        user.setEmail(userUpdateRequest.getEmail());
        user.setCity(userUpdateRequest.getCity());

        save(user);
    }

    @Override
    public void likeProduct(Product product) {
        log.info("LIKE PROD " + product.getId());
        Users user = getCurrentUser();
        user.getFavoriteProduct().add(product);
        save(user);
    }

    @Override
    public void dislikeProduct(Product product) {
        log.info("DISLIKE PROD " + product.getId());
        Users user = getCurrentUser();
        user.getFavoriteProduct().remove(product);
        save(user);
    }

    public Users findById(UUID id) {
        return userImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + id + " not found"));
    }

    public Users getCurrentUser() {
        var username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return findByLogin(username);
    }

    public Users save(Users users) {
        log.info("User with login - " + users.getLogin() + "  saved");
        return userImpl.save(users);
    }
}
