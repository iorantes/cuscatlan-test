package cuscatlan.test.demo.model.dto;

public class Response {

	private String messageError;

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public Response(String messageError) {
		super();
		this.messageError = messageError;
	}

}
