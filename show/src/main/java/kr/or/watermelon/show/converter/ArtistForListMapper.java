package kr.or.watermelon.show.converter;

import kr.or.watermelon.show.dto.ResArtistForListDto;
import kr.or.watermelon.show.entity.Artist;
import org.modelmapper.PropertyMap;

public class ArtistForListMapper extends PropertyMap<Artist, ResArtistForListDto> {
    @Override
    protected void configure() {
        map().setProducts(source.getProductTitles());
    }
}
