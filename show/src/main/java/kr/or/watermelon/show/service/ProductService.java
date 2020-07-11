package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResPromotionDto;
import kr.or.watermelon.show.entity.Product;
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
    private final ModelMapper modelMapper;


    public List<ResPromotionDto> getPromotionProducts() {
        List<Promotion> promotions = promotionRepository.findAll();
        List<ResPromotionDto> resPromotions = promotions.stream()
                .map(p -> modelMapper.map(p, ResPromotionDto.class))
                .collect(Collectors.toList());
        return resPromotions;
    }
}
