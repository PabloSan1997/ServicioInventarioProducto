package com.project.list.products.send.serviceproducts.repository;

import com.project.list.products.send.serviceproducts.models.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Producto, Long> {
    List<Producto> findAll(Pageable pageable);
    @Query("select p from Producto p where p.descripcion like %?1%")
    List<Producto> findBySearch(String name, Pageable pageable);

    @Query("select p from Producto p order by p.precioDolar asc")
    List<Producto> findAllOrderByPriceAsc(Pageable pageable);

    @Query("select p from Producto p order by p.precioDolar desc")
    List<Producto> findAllOrderByPriceDesc(Pageable pageable);
}
