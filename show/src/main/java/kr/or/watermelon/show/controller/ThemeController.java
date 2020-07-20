package kr.or.watermelon.show.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.or.watermelon.show.dto.ProductForListDto;
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

@Api(tags = {"테마API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ThemeController {

    private final ThemeService themeService;


    @GetMapping("/products/themes/{themeType}")
    @ApiOperation(value = "[메인페이지(p21)]: 테마별 상품 리스트 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "themeType", required = true, allowableValues = "HOT_ISSUE,NEW,COMMING_SOON"),
    })
    public List<ProductForListDto> getProductsByTheme(@PathVariable ThemeType themeType,
                                                      @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return themeService.getProductsByTheme(themeType, pageable);
    }
}
