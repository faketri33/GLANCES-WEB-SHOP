package com.faketri.market.usecase.user.payload.user;

import com.faketri.market.entity.exception.ImageFormatException;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.user.gateway.repository.UserRepository;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.image.gateway.ImageService;
import com.faketri.market.infastructure.user.payload.user.dto.UserUpdateRequest;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import com.faketri.market.usecase.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
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
    private final FileService fileService;

    @Autowired
    public UserServiceImpl(UserRepository userImpl, ImageService imageService, FileService fileService) {
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
        if (!fileService.isImageFile(image)) throw new ImageFormatException("Неверный формат изображения.");

        final Users user = getCurrentUser();

        log.info("updateUserImage: user id - " + user.getId());

        final String path = "/app/images/user/profile/";
        final String name = user.getLogin().replace(' ', '-');

        final String imageName = path + name + "-" + Objects.requireNonNull(image.getOriginalFilename()).replace(' ', '-').toLowerCase();
        System.out.println(imageName);
        try {
            image.transferTo(Paths.get(imageName));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        final Image deleteImage = user.getProfileImage();
        user.setProfileImage(new Image(null, imageName));

        final Users savedUser = save(user);
        final Image returnedImage = savedUser.getProfileImage();

        imageService.delete(deleteImage);

        return returnedImage;
    }

    @Override
    public void updateUserData(UserUpdateRequest userUpdateRequest) {

    }

    @Override
    public void likeProduct(Product product) {

    }

    @Override
    public void dislikeProduct(Product product) {

    }

    public Users findById(UUID id) {
        return userImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + id + " not found"));
    }

    public Users getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
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
