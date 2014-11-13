package globant.svp.core.domain;

import globant.svp.core.service.validation.CustomerCheck;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

@Repository(value = "CustomerDTOJDBC")
@CustomerCheck
public class Customer implements Entity, Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String ecn;
	private long account;
	@NotNull(message = "First Name Cannot be blank")
	@Length(max=50, message="Length Cannot be more than 50 Character")
	private String firstName;
	private String middleInitial;
	@Length(max=50, message="Length Cannot be more than 50 Character")
	private String lastName;
	private Date dob;
	private String phone;
	private String city;
	private String state;
	private long zip;

	public String getEcn() {
		if (ecn == null) {
			return "";
		}
		return ecn;
	}

	public void setEcn(String ecn) {
		this.ecn = ecn;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getFirstName() {
		if (firstName == null)
			return "";
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		if (middleInitial == null)
			return "";
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		if (lastName == null)
			return "";
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		if (dob == null)
			return new java.util.Date();
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		if (phone == null)
			return "";
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		if (city == null)
			return "";
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		if (state == null)
			return "";
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}
}
