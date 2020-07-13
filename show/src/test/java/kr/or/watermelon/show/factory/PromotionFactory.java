package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PromotionFactory {

    private final PromotionRepository promotionRepository;

    public <T> List<Promotion> savePromotions(Function<T, Promotion> f, List<T> ts) {
        List<Promotion> promotions = ts.stream()
                .map(f)
                .collect(Collectors.toList());

        promotionRepository.saveAll(promotions);
        Collections.reverse(promotions);
        return promotions;
    }
}
