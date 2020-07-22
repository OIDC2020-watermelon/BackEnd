package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.PromotionDto;
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

    public List<PromotionDto> getPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream().
                map((p) -> modelMapper.map(p, PromotionDto.class))
                .collect(Collectors.toList());
    }
}
