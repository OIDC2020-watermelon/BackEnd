package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ResPromotionDto;
import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    public List<ResPromotionDto> getPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream().
                map((p) -> modelMapper.map(p, ResPromotionDto.class))
                .collect(Collectors.toList());
    }
}
