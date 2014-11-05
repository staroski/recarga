package br.com.staroski.recarga.persistence;

public final class DatabaseException extends Exception {

	DatabaseException() {
		super();
	}

	DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	DatabaseException(String message) {
		super(message);
	}

	DatabaseException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1;

}
