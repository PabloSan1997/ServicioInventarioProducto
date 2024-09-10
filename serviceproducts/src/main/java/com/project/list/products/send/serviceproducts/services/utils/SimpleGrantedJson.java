package com.project.list.products.send.serviceproducts.services.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedJson {
    @JsonCreator
    public SimpleGrantedJson(@JsonProperty("authority") String role){}
}
