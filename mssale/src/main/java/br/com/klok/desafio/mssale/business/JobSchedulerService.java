package br.com.klok.desafio.mssale.business;


import br.com.klok.desafio.mssale.config.JobSchedulerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSchedulerService {

    private final JobSchedulerConfig jobSchedulerConfig;
    private final ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor;

    public String updateScheduler(String cron){
        jobSchedulerConfig.setCronPeriod(cron);
        scheduledAnnotationBeanPostProcessor.postProcessAfterInitialization(jobSchedulerConfig
                                                                          , JobSchedulerConfig.class.getSimpleName() );
        return cron;
    }

}
