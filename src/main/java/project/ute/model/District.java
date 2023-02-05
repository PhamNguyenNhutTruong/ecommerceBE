package project.ute.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="District")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="district")
	private List<Address> addresses;

	//bi-directional many-to-one association to Provice
	@ManyToOne
	@JoinColumn(name="provice_code")
	private Provice provice;

	//bi-directional many-to-one association to Ward
	@OneToMany(mappedBy="district")
	private List<Ward> wards;

	public District() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setDistrict(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setDistrict(null);

		return address;
	}

	public Provice getProvice() {
		return this.provice;
	}

	public void setProvice(Provice provice) {
		this.provice = provice;
	}

	public List<Ward> getWards() {
		return this.wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public Ward addWard(Ward ward) {
		getWards().add(ward);
		ward.setDistrict(this);

		return ward;
	}

	public Ward removeWard(Ward ward) {
		getWards().remove(ward);
		ward.setDistrict(null);

		return ward;
	}

}