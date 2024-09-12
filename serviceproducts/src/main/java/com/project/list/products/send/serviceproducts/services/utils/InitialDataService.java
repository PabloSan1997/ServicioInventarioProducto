package com.project.list.products.send.serviceproducts.services.utils;

import com.project.list.products.send.serviceproducts.models.Monedas;
import com.project.list.products.send.serviceproducts.models.RoleEntity;
import com.project.list.products.send.serviceproducts.models.UserEntity;
import com.project.list.products.send.serviceproducts.models.enums.RoleEnum;
import com.project.list.products.send.serviceproducts.repository.MonedaRepository;
import com.project.list.products.send.serviceproducts.repository.RoleRepository;
import com.project.list.products.send.serviceproducts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InitialDataService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MonedaRepository monedaRepository;

    @Value("${admins.info.data}")
    private String[] infoData;

    public InitialDataService() {
    }

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
        if(monedaRepository.findById(1L).isEmpty()){
            Monedas monedas = new Monedas();
            monedas.setId(1L);
            monedas.setIva(1.21F);
            monedas.setDolar(21.22F);
            monedas.setPorcentajeGanancia(1.16F);
            monedaRepository.save(monedas);
        }
    }
    public void generateUserInfo(){
        generateRole();
        String[] user1 = {infoData[0], infoData[1]};
        String[] user2 = {infoData[2], infoData[3]};
        String[][] allUser = {user1, user2};
        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();
        List<UserEntity> listaUSers = new ArrayList<>();
        for (String[] user:allUser) {
            Optional<UserEntity> userEntity = userRepository.findByUsername(user[0]);
            if (userEntity.isEmpty()) {
               UserEntity newUser = UserEntity.builder()
                       .username(user[0])
                       .password(passwordEncoder.encode(user[1]))
                       .roles(roles).build();
               listaUSers.add(newUser);
            }
        }
        if(listaUSers.size()>0){
            userRepository.saveAll(listaUSers);
        }
    }
}
