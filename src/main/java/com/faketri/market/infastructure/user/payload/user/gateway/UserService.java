package com.faketri.market.infastructure.user.payload.user.gateway;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.user.dto.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {

    Users findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String login);

    Image updateUserImage(final MultipartFile image);

    void updateUserData(final UserUpdateRequest userUpdateRequest);

    void likeProduct(final Product product);

    void dislikeProduct(final Product product);

    Users findById(UUID id);

    Users getCurrentUser();

    Users save(Users users);
}
