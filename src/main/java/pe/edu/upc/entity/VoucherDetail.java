package pe.edu.upc.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VoucherDetail")
public class VoucherDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVoucherDetail;

	private int quantityVoucherDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduct")
	private Product product;

	public VoucherDetail() {
		super();
	}

	public VoucherDetail(int idVoucherDetail, int quantityVoucherDetail, Product product) {
		super();
		this.idVoucherDetail = idVoucherDetail;
		this.quantityVoucherDetail = quantityVoucherDetail;
		this.product = product;
	}

	public int getIdVoucherDetail() {
		return idVoucherDetail;
	}

	public void setIdVoucherDetail(int idVoucherDetail) {
		this.idVoucherDetail = idVoucherDetail;
	}

	public int getQuantityVoucherDetail() {
		return quantityVoucherDetail;
	}

	public void setQuantityVoucherDetail(int quantityVoucherDetail) {
		this.quantityVoucherDetail = quantityVoucherDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double calculateAmount() {
		return quantityVoucherDetail * product.getCostProduct();
	}
}
