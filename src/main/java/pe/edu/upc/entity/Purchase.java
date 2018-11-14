package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Purchase")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPurchase;

	@NotNull
	@Past(message = "No puede seleccionar un día que todavía no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "datePurchase")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datePurchase;

	@ManyToOne
	@JoinColumn(name = "idClient", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "idProduct", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "idTypePurchase", nullable = false)
	private TypePurchase typePurchase;

	@NotNull
	@Past(message = "No puede seleccionar un día que todavía no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "dateDispatch")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDispatch;

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(int idPurchase, Date datePurchase, Client client, Product product, TypePurchase typePurchase,
			Date dateDispatch) {
		super();
		this.idPurchase = idPurchase;
		this.datePurchase = datePurchase;
		this.client = client;
		this.product = product;
		this.typePurchase = typePurchase;
		this.dateDispatch = dateDispatch;
	}

	public int getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Date getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public TypePurchase getTypePurchase() {
		return typePurchase;
	}

	public void setTypePurchase(TypePurchase typePurchase) {
		this.typePurchase = typePurchase;
	}

	public Date getDateDispatch() {
		return dateDispatch;
	}

	public void setDateDispatch(Date dateDispatch) {
		this.dateDispatch = dateDispatch;
	}

}
