package kr.or.watermelon.show.config;

import kr.or.watermelon.show.converter.ArtistForListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new ArtistForListMapper());
        return modelMapper;
    }
}
