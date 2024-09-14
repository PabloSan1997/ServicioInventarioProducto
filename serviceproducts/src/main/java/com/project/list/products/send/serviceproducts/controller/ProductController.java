package com.project.list.products.send.serviceproducts.controller;

import com.project.list.products.send.serviceproducts.models.dtos.ListSaveProduct;
import com.project.list.products.send.serviceproducts.models.dtos.SaveProduct;
import com.project.list.products.send.serviceproducts.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        return ResponseEntity.ok().body(productService.findAll(pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<?> findBySearch(@RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(productService.findBySearch(name, pageable));
    }

    @GetMapping("/price-desc")
    public ResponseEntity<?> findByPriceDecx(Pageable pageable){
        return ResponseEntity.ok(productService.findByPriceDesc(pageable));
    }
    @GetMapping("/price-asc")
    public ResponseEntity<?> findByPriceasc(Pageable pageable){
        return ResponseEntity.ok(productService.findByPriceAsc(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ListSaveProduct saveProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(saveProduct));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editById(@RequestBody @Valid SaveProduct saveProduct, @PathVariable Long id){
        return ResponseEntity.ok(productService.edit(id, saveProduct));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
