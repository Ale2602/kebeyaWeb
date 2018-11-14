package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;

	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Column(name = "nameProduct", length = 60, nullable = false)
	private String nameProduct;

	@Size(min = 5, max = 5)
	@Column(name = "codeProduct", length = 5, nullable = false)
	private String codeProduct;

	@DecimalMin("1.00")
	@Column(name = "costProduct", columnDefinition = "Decimal(8,2)", nullable = false)
	private Double costProduct;

	@Size(min = 1, max = 2)
	@Column(name = "stockProduct", length = 2, nullable = false)
	private String stockProduct;

	@ManyToOne
	@JoinColumn(name = "idCategory", nullable = false)
	private Category category;

	private String foto;

	public Product() {
		super();
	}

	public Product(int idProduct, String nameProduct, String codeProduct, Double costProduct, String stockProduct,
			Category category, String foto) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.codeProduct = codeProduct;
		this.costProduct = costProduct;
		this.stockProduct = stockProduct;
		this.category = category;
		this.foto = foto;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public Double getCostProduct() {
		return costProduct;
	}

	public void setCostProduct(Double costProduct) {
		this.costProduct = costProduct;
	}

	public String getStockProduct() {
		return stockProduct;
	}

	public void setStockProduct(String stockProduct) {
		this.stockProduct = stockProduct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
