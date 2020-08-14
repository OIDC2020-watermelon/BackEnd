package kr.or.watermelon.show.service;

import kr.or.watermelon.show.dto.*;
import kr.or.watermelon.show.entity.Category;
import kr.or.watermelon.show.entity.Product;
import kr.or.watermelon.show.proxy.ReservationServiceProxy;
import kr.or.watermelon.show.repository.ArtistRepository;
import kr.or.watermelon.show.repository.PlaceRepository;
import kr.or.watermelon.show.repository.ProductRepository;
import kr.or.watermelon.show.repository.elasticsearch.EReservationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final EReservationRepository elasticRepository;
    private final ReservationServiceProxy reservationServiceProxy;
    private final ArtistRepository artistRepository;
    private final PlaceRepository placeRepository;

    public List<ProductForListDto> searchProductsReleased(String keyword, Category category) {
        List<Product> products;
        LocalDateTime now = LocalDateTime.now();
        if (keyword == null && category == null) {
            products = productRepository.findByReleaseStartTimeBeforeAndReleaseEndTimeAfter(now, now);
        } else if (keyword == null) {
            products = productRepository.findByCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(category, now, now);
        } else if (category == null) {
            products = productRepository.findByTitleContainingAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, now, now);
        } else {
            products = productRepository.findByTitleContainingAndCategoryAndReleaseStartTimeBeforeAndReleaseEndTimeAfter(keyword, category, now, now);
        }

        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }

    public List<ProductForListDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByTitleContaining(keyword);
        return products.stream()
                .map(p -> modelMapper.map(p, ProductForListDto.class))
                .collect(Collectors.toList());
    }

    public List<BucketDto> getReservationTraffic(Long id, TrafficTypeDto trafficType) throws IOException {
        Product product = productRepository.getOne(id);
        List<BucketDto> buckets;
        if (trafficType == TrafficTypeDto.RESERVATION) {
            buckets = elasticRepository.countLogByServiceAndInterceptor(product, "interceptor.ReservationInterceptor");
        } else {
            buckets = elasticRepository.countLogByServiceAndInterceptor(product, "interceptor.PerformanceInterceptor");
        }
        return buckets;
    }

    //TODO Transactional 공부,JPA영속성 공부
    public ProductInfoDto createProduct(ProductInfoDto productInfo) {
        Product product = modelMapper.map(productInfo, Product.class);
        Product productSaved = productRepository.save(product);

        PerformanceInfoDto performanceInfoDto = modelMapper.map(productInfo, PerformanceInfoDto.class);
        performanceInfoDto.setProductId(productSaved.getId());
        performanceInfoDto.setAvailableDate(productInfo.getAvailableDates().get(0).toString());
        reservationServiceProxy.add(performanceInfoDto);
        return productInfo;
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.getOne(id);
        return modelMapper.map(product, ProductDto.class);
    }
}
