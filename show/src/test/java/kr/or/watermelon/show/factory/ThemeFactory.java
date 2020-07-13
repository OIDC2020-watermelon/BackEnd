package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ThemeFactory {

    private final ThemeRepository themeRepository;

    public <T> List<Theme> saveThemes(Function<T, Theme> f, List<T> ts) {
        List<Theme> themes = ts.stream()
                .map(f)
                .collect(Collectors.toList());

        themeRepository.saveAll(themes);
        Collections.reverse(themes);
        return themes;
    }
}
