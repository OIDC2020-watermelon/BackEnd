package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.entity.ThemeType;
import kr.or.watermelon.show.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ThemeFactory {

    private final ThemeRepository themeRepository;
    private final Theme.ThemeBuilder tBuilder = Theme.builder();

    public List<Theme> saveThemes(int count) {
        List<Theme> themes = Arrays.stream(ThemeType.values())
                .map(getThemeTypeListFunction(count))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        themeRepository.saveAll(themes);
        return themes;
    }

    private Function<ThemeType, List<Theme>> getThemeTypeListFunction(int count) {
        return (themeType) -> Stream.generate(() -> tBuilder.themeType(themeType).build())
                .limit(count)
                .collect(Collectors.toList());
    }
}
