package br.com.klok.desafio.mssale.presetation.controller;


import br.com.klok.desafio.mssale.business.JobSchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/scheduler")
public class JobSchedulerController {

    private final JobSchedulerService jobSchedulerService;

    @PutMapping("/update/{cron}")
    public ResponseEntity<String> updateScheduler(@PathVariable("cron") String cron){
        var cronResponse = jobSchedulerService.updateScheduler(cron);
        return ResponseEntity.status(200).body("Successfully updated to " + cronResponse);
    }

}
