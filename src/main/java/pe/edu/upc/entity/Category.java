package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;

	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Column(name = "nameCategory", length = 60, nullable = false)
	private String nameCategory;

	@Size(min = 5, max = 5)
	@Column(name = "codeCategory", length = 5, nullable = false)
	private String codeCategory;

	public Category() {
		super();
	}

	public Category(int idCategory, String nameCategory, String codeCategory) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.codeCategory = codeCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getCodeCategory() {
		return codeCategory;
	}

	public void setCodeCategory(String codeCategory) {
		this.codeCategory = codeCategory;
	}
}
