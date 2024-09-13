package com.project.list.products.send.serviceproducts.services.imp;

import com.project.list.products.send.serviceproducts.exceptions.MyBadRequestException;
import com.project.list.products.send.serviceproducts.models.Monedas;
import com.project.list.products.send.serviceproducts.models.Producto;
import com.project.list.products.send.serviceproducts.models.dtos.ListSaveProduct;
import com.project.list.products.send.serviceproducts.models.dtos.SaveProduct;
import com.project.list.products.send.serviceproducts.repository.MonedaRepository;
import com.project.list.products.send.serviceproducts.repository.ProductRepository;
import com.project.list.products.send.serviceproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MonedaRepository monedaRepository;


    @Override
    @Transactional
    public List<Producto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Producto> findBySearch(String name, Pageable pageable) {
        return productRepository.findBySearch(name, pageable);
    }

    @Override
    @Transactional
    public List<Producto> findByPriceDesc(Pageable pageable) {
        return productRepository.findAllOrderByPriceDesc(pageable);
    }

    @Override
    @Transactional
    public List<Producto> findByPriceAsc(Pageable pageable) {
        return productRepository.findAllOrderByPriceAsc(pageable);
    }

    @Override
    @Transactional
    public List<Producto> save(ListSaveProduct saveProduct) {
        List<SaveProduct> listsaveProducts = saveProduct.getProducts();
        Monedas monedas = monedaRepository.findById(1L).orElseThrow();
        if(listsaveProducts.size()>0){
            List<Producto> productos = listsaveProducts.stream().map(p -> {
                Producto pro = convertToProducto(p);
                pro.setMonedas(monedas);
                return pro;
            }).toList();
            return (List<Producto>) productRepository.saveAll(productos);
        }
        return new ArrayList<>();

    }

    @Override
    @Transactional
    public Producto edit(Long id, SaveProduct saveProduct) {
        Producto theprodctos = productRepository.findById(id).orElseThrow(()->{
            throw new MyBadRequestException("id invalido");
        });
        Producto producto = convertToProducto(saveProduct);
        producto.setId(theprodctos.getId());
        producto.setMonedas(theprodctos.getMonedas());

        return productRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private Producto convertToProducto(SaveProduct saveProduct){
        return Producto.builder()
                .linea(saveProduct.getLinea())
                .UCP(saveProduct.getUCP())
                .marca(saveProduct.getMarca())
                .serie(saveProduct.getSerie())
                .descripcion(saveProduct.getDescripcion())
                .seccion(saveProduct.getSeccion())
                .existencias(saveProduct.getExistencias())
                .urlImages(saveProduct.getUrlImages())
                .garantia(saveProduct.getGarantia())
                .clavePCH(saveProduct.getClavePCH())
                .precioDolar(saveProduct.getPrecioDolar())
                .estado(saveProduct.getEstado())
                .build();

    }
}
