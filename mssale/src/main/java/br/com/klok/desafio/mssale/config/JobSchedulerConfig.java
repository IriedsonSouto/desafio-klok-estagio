package br.com.klok.desafio.mssale.config;


import br.com.klok.desafio.mssale.business.SaleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Getter
@Setter
@RequiredArgsConstructor
public class JobSchedulerConfig {

    private String cronPeriod;
    private final SaleService saleService;

    @Scheduled(cron = "${schedule.cron}")
    public void scheduleTask(){
        saleService.salesCharge();
    }

}
