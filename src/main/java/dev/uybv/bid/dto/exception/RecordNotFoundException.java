package dev.uybv.bid.dto.exception;

public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super("No data matching");
	}
}
