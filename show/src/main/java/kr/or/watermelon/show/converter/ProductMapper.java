package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.ProductInfoDto;
import kr.or.watermelon.show.entity.Product;
import org.modelmapper.PropertyMap;

public class ProductMapper extends PropertyMap<ProductInfoDto, Product> {
    @Override
    protected void configure() {
        map().setPlace(null);
        map().setArtists(null);
    }
}
