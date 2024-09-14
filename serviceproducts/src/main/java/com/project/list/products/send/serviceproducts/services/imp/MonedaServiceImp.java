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
    public Monedas editMoneda(MonedasDto monedasDto) {
        Monedas monedas = getMoneda();
        monedas.setIva(monedasDto.getIva());
        monedas.setDolar(monedasDto.getDolar());
        monedas.setPorcentajeGanancia(monedasDto.getPorcentajeGanancia());
        return monedaRepository.save(monedas);
    }

    @Override
    @Transactional
    public Monedas getMoneda() {
        return ((List<Monedas>) monedaRepository.findAll()).stream().findFirst().orElseThrow();
    }
}
