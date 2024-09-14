package com.project.list.products.send.serviceproducts.controller;

import com.project.list.products.send.serviceproducts.models.dtos.MonedasDto;
import com.project.list.products.send.serviceproducts.services.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moneda")
public class MonedasController {
    @Autowired
    private MonedaService monedaService;

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody MonedasDto monedasDto){

        return ResponseEntity.ok(monedaService.editMoneda(monedasDto));
    }
    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(monedaService.getMoneda());
    }
}
