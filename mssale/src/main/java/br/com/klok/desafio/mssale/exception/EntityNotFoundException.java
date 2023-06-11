package br.com.klok.desafio.mssale.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
		super(message);
	}

}