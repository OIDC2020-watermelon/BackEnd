package kr.or.watermelon.show.config;

import kr.or.watermelon.show.converter.CategoryConverter;
import kr.or.watermelon.show.converter.ReqReleaseStatusConverter;
import kr.or.watermelon.show.converter.ThemeTypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CategoryConverter());
        registry.addConverter(new ReqReleaseStatusConverter());
        registry.addConverter(new ThemeTypeConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
