package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import jakarta.persistence.*;

@Entity
@Table(name="Image")
@Builder
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Image() {
	}

	public Image(String name, String type, byte[] imageData) {
		super();
		this.name = name;
		this.type = type;
		this.imageData = imageData;
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

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public static class  builder{
//		private long id;
//	    private String name;
//	    private String type;
//	    private byte[] imageData;
//	    
//		public builder() {
//			super();
//		}
//		
//		public builder(long id, String name, String type, byte[] imageData) {
//			super();
//			this.id = id;
//			this.name = name;
//			this.type = type;
//			this.imageData = imageData;
//		}
//		
//		public long getId() {
//			return id;
//		}
//
//		public void setId(long id) {
//			this.id = id;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getType() {
//			return type;
//		}
//
//		public void setType(String type) {
//			this.type = type;
//		}
//
//		public byte[] getImageData() {
//			return imageData;
//		}
//
//		public void setImageData(byte[] imageData) {
//			this.imageData = imageData;
//		}
//		
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
//		public builder imageData(byte[] imageData) {
//			this.imageData = imageData;
//			return this;
//		}
//		
//		public Image build() {
//			Image image = new Image();
//			return image;
//		}
//	}
}