package kr.or.watermelon.ticket.reservation.service;

import kr.or.watermelon.ticket.reservation.domain.Performance;
import kr.or.watermelon.ticket.reservation.domain.Ticket;
import kr.or.watermelon.ticket.reservation.dto.PerformanceDto;
import kr.or.watermelon.ticket.reservation.dto.PerformanceInfoDto;
import kr.or.watermelon.ticket.reservation.repository.PerformanceRepository;
import kr.or.watermelon.ticket.reservation.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<PerformanceDto> getList(Long productId) {
        return performanceRepository.findByProductId(productId)
                .stream()
                .map(performance -> modelMapper.map(performance, PerformanceDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public PerformanceDto add(PerformanceInfoDto performanceInfo) {
        Performance performance = Performance.builder()
                                    .availableDate(performanceInfo.getAvailableDate())
                                    .startAt(performanceInfo.getStartAt())
                                    .duration(performanceInfo.getDuration())
                                    .session(performanceInfo.getSession())
                                    .productId(performanceInfo.getProductId())
                                    .build();

        Performance newPerformance = performanceRepository.save(performance);

        List<Ticket> ticketList = new ArrayList<>();

        // create VIP ticket
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.println(i + " " + j);
                Ticket ticket = Ticket.builder()
                                    .grade(Ticket.Grade.VIP)
                                    .rowNum(i)
                                    .colNum(j)
                                    .price(performanceInfo.getVipPrice())
                                    .isSold(false)
                                    .performance(newPerformance)
                                    .build();

                ticketList.add(ticket);
            }
        }

        // create S ticket
        for (int i = 3; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.println(i + " " + j);
                Ticket ticket = Ticket.builder()
                        .grade(Ticket.Grade.S)
                        .rowNum(i)
                        .colNum(j)
                        .price(performanceInfo.getSPrice())
                        .isSold(false)
                        .performance(newPerformance)
                        .build();

                ticketList.add(ticket);
            }
        }

        // save all
        ticketRepository.saveAll(ticketList);

        return modelMapper.map(newPerformance, PerformanceDto.class);
    }

    @Transactional
    public void delete(Long productId) {
        List<Performance> performances = performanceRepository.findByProductId(productId);
        performances.forEach(p->{
            ticketRepository.deleteALlByPerformanceId(p.getId());//TODO 쿼리 최적화 필요
            performanceRepository.delete(p);
        });
    }
}
