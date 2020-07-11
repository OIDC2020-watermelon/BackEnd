package kr.or.watermelon.show.dto;

import kr.or.watermelon.show.entity.Product;
import org.modelmapper.PropertyMap;

public class ProductMap extends PropertyMap<Product,ResProductDto>{
    @Override
    protected void configure() {
        map().setPlace(source.getPlace().getName());
    }
}