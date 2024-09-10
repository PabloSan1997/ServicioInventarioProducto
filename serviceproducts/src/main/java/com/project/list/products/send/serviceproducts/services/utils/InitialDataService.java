package com.project.list.products.send.serviceproducts.services.utils;

import com.project.list.products.send.serviceproducts.models.RoleEntity;
import com.project.list.products.send.serviceproducts.models.enums.RoleEnum;
import com.project.list.products.send.serviceproducts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InitialDataService {
    @Autowired
    private RoleRepository roleRepository;

    public void generateRole(){
        List<RoleEntity> roles = new ArrayList<>();
        for(RoleEnum roleEnum: RoleEnum.values()){
            Optional<RoleEntity> rol = roleRepository.findByName(roleEnum);
            if(rol.isEmpty()){
                RoleEntity roleEntity = RoleEntity.builder().name(roleEnum).build();
                roles.add(roleEntity);
            }
        }
        if(roles.size()>1)
            roleRepository.saveAll(roles);
    }
}
