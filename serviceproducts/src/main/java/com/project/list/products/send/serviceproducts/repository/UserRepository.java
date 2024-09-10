package com.project.list.products.send.serviceproducts.repository;

import com.project.list.products.send.serviceproducts.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
