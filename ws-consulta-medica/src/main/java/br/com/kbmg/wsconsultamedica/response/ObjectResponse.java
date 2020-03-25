package br.com.kbmg.wsconsultamedica.response;

public class ObjectResponse {

	private Object data;
	private ErrorResponse errorDescription;
	
	public ObjectResponse() {
	}

	public ObjectResponse(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ErrorResponse getErrorDescription() {
		return errorDescription;
	}
	
	public void setErrorDescription(ErrorResponse errorDescription) {
		this.errorDescription = errorDescription;
	}
}
