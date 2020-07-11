package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResProductDto;
import kr.or.watermelon.show.dto.ResPromotionDto;
import kr.or.watermelon.show.dto.ResThemeDto;
import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.repository.PromotionRepository;
import kr.or.watermelon.show.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PromotionRepository promotionRepository;
    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;


    public List<ResPromotionDto> getPromotionProducts() {
        List<Promotion> promotions = promotionRepository.findAll();
        List<ResPromotionDto> resPromotions = promotions.stream()
                .map(p -> modelMapper.map(p, ResPromotionDto.class))
                .collect(Collectors.toList());
        return resPromotions;
    }

    public Map<String, List<ResProductDto>> getThemeRepresentativeProducts() {
        List<Theme> themes = themeRepository.findAll();//TODO RAW query쓸지 고민해야함
        Map<String, List<ResProductDto>> themeProducts = themes.stream()
                .map(t->modelMapper.map(t, ResThemeDto.class))
                .collect(Collectors.groupingBy(ResThemeDto::getThemeType,Collectors.mapping(ResThemeDto::getProduct,Collectors.toList())));
        return themeProducts;
    }
}
