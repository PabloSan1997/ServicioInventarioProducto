package com.project.list.products.send.serviceproducts.services;

import com.project.list.products.send.serviceproducts.models.Producto;
import com.project.list.products.send.serviceproducts.models.dtos.ListSaveProduct;
import com.project.list.products.send.serviceproducts.models.dtos.SaveProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Producto> findAll(Pageable pageable);
    List<Producto> findBySearch(String name, Pageable pageable);
    List<Producto> findByPriceDesc(Pageable pageable);
    List<Producto> findByPriceAsc(Pageable pageable);
    List<Producto> save(ListSaveProduct saveProduct);
    Producto edit(Long id, SaveProduct saveProduct);
    void deleteById(Long id);
    Producto findById(Long id);
}
