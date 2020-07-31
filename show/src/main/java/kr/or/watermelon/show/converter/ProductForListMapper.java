package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.entity.Product;
import org.modelmapper.PropertyMap;

public class ProductForListMapper extends PropertyMap<Product, ProductForListDto> {
    @Override
    protected void configure() {
        map().setArtists(source.getArtistNames());
        map().setPlace(source.getPlace().getName());
    }
}
