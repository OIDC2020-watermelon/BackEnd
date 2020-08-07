package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.ProductForListDto;
import kr.or.watermelon.show.entity.Theme;
import kr.or.watermelon.show.entity.ThemeType;
import kr.or.watermelon.show.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    public List<ProductForListDto> getProductsByTheme(ThemeType type, Pageable pageable) {
        Page<Theme> themes = themeRepository.findByType(type, pageable);
        return themes.stream()
                .map(t -> modelMapper.map(t.getProduct(), ProductForListDto.class))
                .collect(Collectors.toList());
    }
}
