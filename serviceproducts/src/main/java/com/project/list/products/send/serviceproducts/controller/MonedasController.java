package com.project.list.products.send.serviceproducts.controller;

import com.project.list.products.send.serviceproducts.models.dtos.MonedasDto;
import com.project.list.products.send.serviceproducts.services.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moneda")
public class MonedasController {
    @Autowired
    private MonedaService monedaService;

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody MonedasDto monedasDto){
        monedaService.editMoneda(monedasDto);
        var data = ResponseEntity.noContent().build();
        return ResponseEntity.noContent().build();
    }
}
