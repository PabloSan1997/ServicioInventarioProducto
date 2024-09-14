package com.project.list.products.send.serviceproducts.services;

import com.project.list.products.send.serviceproducts.models.Monedas;
import com.project.list.products.send.serviceproducts.models.dtos.MonedasDto;

public interface MonedaService {
    Monedas editMoneda(MonedasDto monedasDto);
    Monedas getMoneda();
}
