package br.com.klok.desafio.msproduct.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
		super(message);
	}

}