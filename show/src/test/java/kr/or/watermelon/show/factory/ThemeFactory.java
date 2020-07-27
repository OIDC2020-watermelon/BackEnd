package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.CustomBuilder;
import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ThemeFactory {

    private final ThemeRepository themeRepository;
    private final Factory factory;

    public <T> List<Theme> saveItems(Function<T, CustomBuilder> func, List<T> args) {
        List<Theme> themes = (List<Theme>) factory.makeItems(func, args);
        return themeRepository.saveAll(themes);
    }

    public <T> Theme saveItem(Function<T, CustomBuilder> func, T arg) {
        Theme theme = (Theme) factory.makeItem(func, arg);
        return themeRepository.save(theme);
    }
}
