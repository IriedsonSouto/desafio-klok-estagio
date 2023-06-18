package br.com.klok.desafio.mssale.presetation.controller;


import br.com.klok.desafio.mssale.business.JobSchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/scheduler")
public class JobSchedulerController {

    private final JobSchedulerService jobSchedulerService;

    @PostMapping
    public ResponseEntity<String> updateScheduler(@RequestBody String cron){
        var cronResponse = jobSchedulerService.updateScheduler(cron);
        return ResponseEntity.status(200).body("Successfully updated to " + cronResponse);
    }

}
