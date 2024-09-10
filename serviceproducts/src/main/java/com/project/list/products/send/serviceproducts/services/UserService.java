package com.project.list.products.send.serviceproducts.services;

import com.project.list.products.send.serviceproducts.models.dtos.LoginDto;
import com.project.list.products.send.serviceproducts.models.dtos.LoginResponse;

public interface UserService {
    LoginResponse login(LoginDto loginDto);
}
