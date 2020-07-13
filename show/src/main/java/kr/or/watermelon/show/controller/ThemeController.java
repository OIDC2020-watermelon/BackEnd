package kr.or.watermelon.show.controller;

import kr.or.watermelon.show.dto.ResProductDto;
import kr.or.watermelon.show.entity.ThemeType;
import kr.or.watermelon.show.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping("/products/themes/{themeType}")
    public List<ResProductDto> getProductsByTheme(@PathVariable ThemeType themeType,
                                                  @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return themeService.getProductsByTheme(themeType, pageable);
    }
}
