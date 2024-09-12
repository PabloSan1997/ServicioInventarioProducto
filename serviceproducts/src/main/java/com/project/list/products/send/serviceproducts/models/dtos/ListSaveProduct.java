package com.project.list.products.send.serviceproducts.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSaveProduct {
    private List<SaveProduct> products;
}
