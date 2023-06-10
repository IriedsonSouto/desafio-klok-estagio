package br.com.klok.desafio.msclient.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
		super(message);
	}

}