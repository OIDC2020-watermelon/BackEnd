package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.ResProductForListDto;
import kr.or.watermelon.show.entity.Product;
import org.modelmapper.PropertyMap;

public class ProductForListMapper extends PropertyMap<Product, ResProductForListDto> {
    @Override
    protected void configure() {
        map().setArtists(source.getArtistNames());
        map().setPlace(source.getPlace().getName());
    }
}
