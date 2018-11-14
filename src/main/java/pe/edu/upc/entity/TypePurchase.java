package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TypePurchase")
public class TypePurchase implements Serializable {
	private static final long serialVersionUID = 1L;

	// id tipo consumo
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTypePurchase;

	// nombre tipo
	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Column(name = "nameTypePurchase", length = 15, nullable = false)
	private String nameTypePurchase;

	// Constructor
	public TypePurchase() {
		super();
	}

	public TypePurchase(int idTypePurchase, String nameTypePurchase) {
		super();
		this.idTypePurchase = idTypePurchase;
		this.nameTypePurchase = nameTypePurchase;
	}

	// Gets and Sets
	public int getIdTypePurchase() {
		return idTypePurchase;
	}

	public void setIdTypePurchase(int idTypePurchase) {
		this.idTypePurchase = idTypePurchase;
	}

	public String getNameTypePurchase() {
		return nameTypePurchase;
	}

	public void setNameTypePurchase(String nameTypePurchase) {
		this.nameTypePurchase = nameTypePurchase;
	}

}
