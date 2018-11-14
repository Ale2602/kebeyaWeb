package pe.edu.upc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Voucher")
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVoucher;

	@Column(name = "dateVoucher")
	@Temporal(TemporalType.DATE)
	private Date dateVoucher;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idClient", nullable = false)
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idVoucherDetail")
	private List<VoucherDetail> voucherdetail;

	public Voucher() {
		this.voucherdetail = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		dateVoucher = new Date();
	}

	public int getIdVoucher() {
		return idVoucher;
	}

	public void setIdVoucher(int idVoucher) {
		this.idVoucher = idVoucher;
	}

	public Date getDateVoucher() {
		return dateVoucher;
	}

	public void setDateVoucher(Date dateVoucher) {
		this.dateVoucher = dateVoucher;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<VoucherDetail> getVoucherdetail() {
		return voucherdetail;
	}

	public void setVoucherdetail(List<VoucherDetail> voucherdetail) {
		this.voucherdetail = voucherdetail;
	}

	public void addVoucherDetail(VoucherDetail item) {
		this.voucherdetail.add(item);
	}

	public Double getTotal() {
		return voucherdetail.stream().collect(Collectors.summingDouble(VoucherDetail::calculateAmount));
	}
}
