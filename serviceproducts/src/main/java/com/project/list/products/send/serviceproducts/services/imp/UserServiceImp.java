package com.project.list.products.send.serviceproducts.services.imp;

import com.project.list.products.send.serviceproducts.exceptions.MyBadRequestException;
import com.project.list.products.send.serviceproducts.models.dtos.LoginDto;
import com.project.list.products.send.serviceproducts.models.dtos.LoginResponse;
import com.project.list.products.send.serviceproducts.models.dtos.UserSecurity;
import com.project.list.products.send.serviceproducts.services.UserService;
import com.project.list.products.send.serviceproducts.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public LoginResponse login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            UserSecurity userSecurity = (UserSecurity) authenticationManager.authenticate(authenticationToken).getPrincipal();
            return jwtService.generateToken(userSecurity);
        }catch (Exception e){
            throw new MyBadRequestException("Usuario o contraseña incorrectos");
        }

    }
}
