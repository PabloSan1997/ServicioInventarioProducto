package com.project.list.products.send.serviceproducts.repository;

import com.project.list.products.send.serviceproducts.models.RoleEntity;
import com.project.list.products.send.serviceproducts.models.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
