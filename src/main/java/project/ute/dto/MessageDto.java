package project.ute.dto;

public class MessageDto {
	private String title;
	private String message;
	private String status;
	
	public MessageDto() {
		super();
	}

	public MessageDto(String title, String message, String status) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
