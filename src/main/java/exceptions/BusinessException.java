package exceptions;

public class BusinessException extends RuntimeException {

	BusinessException(String message){
		super(message);
	}

	BusinessException(String message, Throwable e){
		super(message, e);
	}
}
