package project.ute.dto;

public class ImageDto {
	private String name;
	private String filePath;
	
	public ImageDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageDto(String filePath) {
		super();
		this.filePath = filePath;
	}

	public String getfilePath() {
		return filePath;
	}

	public void setfilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
