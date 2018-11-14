package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;

	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Column(name = "nameClient", length = 60, nullable = false)
	private String nameClient;

	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Column(name = "lastClient", length = 60, nullable = false)
	private String lastClient;

	@Size(min = 8, max = 8)
	@Column(name = "dniClient", length = 8, nullable = false)
	private String dniClient;

	@Size(min = 9, max = 9)
	@Column(name = "phoneClient", length = 9, nullable = false)
	private String phoneClient;

	@NotEmpty(message = "Este espacio no puede estar vacío")
	@NotBlank(message = "Este espacio no puede estar en blanco")
	@Email
	@Column(name = "emailClient", length = 60, nullable = false)
	private String emailClient;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Voucher> voucher;

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getLastClient() {
		return lastClient;
	}

	public void setLastClient(String lastClient) {
		this.lastClient = lastClient;
	}

	public String getDniClient() {
		return dniClient;
	}

	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}

	public String getPhoneClient() {
		return phoneClient;
	}

	public void setPhoneClient(String phoneClient) {
		this.phoneClient = phoneClient;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public List<Voucher> getVoucher() {
		return voucher;
	}

	public void setVoucher(List<Voucher> voucher) {
		this.voucher = voucher;
	}
}
