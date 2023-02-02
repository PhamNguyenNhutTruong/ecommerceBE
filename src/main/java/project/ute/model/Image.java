package project.ute.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private byte[] filePath;
    
  //bi-directional many-to-one association to Product
  	@ManyToOne
  	private Product product;
    
	public Image() {
		super();
	}
	
	public Image(String name, String type, byte[] filePath, Product product) {
		super();
		this.name = name;
		this.type = type;
		this.filePath = filePath;
		this.product = product;
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

	public byte[] getFilePath() {
		return filePath;
	}

	public void setFilePath(byte[] filePath) {
		this.filePath = filePath;
	}
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
