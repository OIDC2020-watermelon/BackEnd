package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.entity.Promotion;
import kr.or.watermelon.show.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PromotionFactory {

    private final PromotionRepository promotionRepository;
    private final Factory factory;

    public <T> List<Promotion> savePromotions(Function<T, CustomBuilder> func, List<T> args) {
        List<Promotion> promotions = (List<Promotion>) factory.makeItems(func, args);
        return promotionRepository.saveAll(promotions);
    }

    public <T> Promotion saveItem(Function<T, CustomBuilder> func, T arg) {
        Promotion promotion = (Promotion) factory.makeItem(func, arg);
        return promotionRepository.save(promotion);
    }
}
