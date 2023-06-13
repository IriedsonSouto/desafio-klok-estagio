package br.com.klok.desafio.mssale.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntityErrorDetails {
    
    private String title;
    private HttpStatus status;
    private List<String> errors;
    private LocalDateTime date;

    public EntityErrorDetails(HttpStatus status, String title, List<String> errors) {
        this.title = title;
        this.status = status;
        this.errors = errors;
        this.date = LocalDateTime.now();
    }

    public EntityErrorDetails(HttpStatus status, String title, String error) {
        this.title = title;
        this.status = status;
        errors = Arrays.asList(error);
        this.date = LocalDateTime.now();
    }
}