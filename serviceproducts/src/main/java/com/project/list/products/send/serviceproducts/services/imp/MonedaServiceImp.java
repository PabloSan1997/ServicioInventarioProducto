package com.project.list.products.send.serviceproducts.services.imp;

import com.project.list.products.send.serviceproducts.models.Monedas;
import com.project.list.products.send.serviceproducts.models.dtos.MonedasDto;
import com.project.list.products.send.serviceproducts.repository.MonedaRepository;
import com.project.list.products.send.serviceproducts.services.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MonedaServiceImp implements MonedaService {
    @Autowired
    private MonedaRepository monedaRepository;


    @Override
    @Transactional
    public void editMoneda(MonedasDto monedasDto) {
        Monedas monedas = ((List<Monedas>) monedaRepository.findAll()).stream().findFirst().orElseThrow();
        monedas.setIva(monedasDto.getIva());
        monedas.setDolar(monedas.getDolar());
        monedas.setPorcentajeGanancia(monedas.getPorcentajeGanancia());
        monedaRepository.save(monedas);
    }
}
