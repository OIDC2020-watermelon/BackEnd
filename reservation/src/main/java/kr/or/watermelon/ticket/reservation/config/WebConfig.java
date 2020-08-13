package kr.or.watermelon.ticket.reservation.config;

import kr.or.watermelon.ticket.reservation.interceptor.PerformanceInterceptor;
import kr.or.watermelon.ticket.reservation.interceptor.ReservationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ReservationInterceptor reservationInterceptor;
    private final PerformanceInterceptor performanceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reservationInterceptor)
                .addPathPatterns("/reservation");

        registry.addInterceptor(performanceInterceptor)
                .addPathPatterns("/reservation/performance/*");
    }
}
