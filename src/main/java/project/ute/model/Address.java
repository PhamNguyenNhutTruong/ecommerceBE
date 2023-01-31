package project.ute.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name="Address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="address_text")
	private String addressText;

	//bi-directional many-to-one association to District
	@ManyToOne
	@JoinColumn(name="district_code")
	private District district;

	//bi-directional many-to-one association to Provice
	@ManyToOne
	@JoinColumn(name="provice_code")
	private Provice provice;

	//bi-directional many-to-one association to Ward
	@ManyToOne
	@JoinColumn(name="ward_code")
	private Ward ward;

	//bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy="address")
	private List<CustomerAddress> customerAddresses;

	//bi-directional many-to-one association to Shipment
	@OneToMany(mappedBy="address")
	private List<Shipment> shipments;

	public Address() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressText() {
		return this.addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Provice getProvice() {
		return this.provice;
	}

	public void setProvice(Provice provice) {
		this.provice = provice;
	}

	public Ward getWard() {
		return this.ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setAddress(this);

		return customerAddress;
	}

	public CustomerAddress removeCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setAddress(null);

		return customerAddress;
	}

	public List<Shipment> getShipments() {
		return this.shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public Shipment addShipment(Shipment shipment) {
		getShipments().add(shipment);
		shipment.setAddress(this);

		return shipment;
	}

	public Shipment removeShipment(Shipment shipment) {
		getShipments().remove(shipment);
		shipment.setAddress(null);

		return shipment;
	}

}