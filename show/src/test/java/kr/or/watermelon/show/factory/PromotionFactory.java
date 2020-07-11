package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PromotionFactory {

    private final PromotionRepository promotionRepository;

    public List<Promotion> savePromotions(int count) {
        Promotion.PromotionBuilder pBuilder = Promotion.builder();
        List<Promotion> promotions = Stream.generate(() -> pBuilder.build()).limit(count).collect(Collectors.toList());
        promotionRepository.saveAll(promotions);
        return promotions;
    }
}
