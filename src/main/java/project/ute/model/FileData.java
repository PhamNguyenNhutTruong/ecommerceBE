package project.ute.model;

import jakarta.persistence.*;


@Entity
@Table(name = "FILE_DATA")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private String filePath;
    
	public FileData() {
		super();
	}
	
	public FileData(String name, String type, String filePath) {
		super();
		this.name = name;
		this.type = type;
		this.filePath = filePath;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
//	public static class  builder{
//		private long id;
//	    private String name;
//	    private String type;
//	    private String filePath;
//	    
//	    public builder() {
//			super();
//		}
//	     
//		public builder(long id, String name, String type, String filePath) {
//			super();
//			this.id = id;
//			this.name = name;
//			this.type = type;
//			this.filePath = filePath;
//		}
//
//		public long getId() {
//			return id;
//		}
//		public void setId(long id) {
//			this.id = id;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getType() {
//			return type;
//		}
//		public void setType(String type) {
//			this.type = type;
//		}
//		public String getFilePath() {
//			return filePath;
//		}
//		public void setFilePath(String filePath) {
//			this.filePath = filePath;
//		}
//		
//		public builder id(long id) {
//			this.id = id;
//			return this;
//		}
//		
//		public builder name(String name) {
//			this.name = name;
//			return this;
//		}
//		
//		public builder type(String type) {
//			this.type = type;
//			return this;
//		}
//		
//		public builder filePath(String filePath) {
//			this.filePath = filePath;
//			return this;
//		}
//		
//		public FileData build() {
//			FileData fileData = new FileData();
//			return fileData;
//		}
//	}
}
