package kr.or.watermelon.show.config;

import kr.or.watermelon.show.converter.ArtistForListMapper;
import kr.or.watermelon.show.converter.ProductForListMapper;
import kr.or.watermelon.show.converter.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new ProductForListMapper());
        modelMapper.addMappings(new ArtistForListMapper());
        modelMapper.addMappings(new ProductMapper());
        return modelMapper;
    }
}
