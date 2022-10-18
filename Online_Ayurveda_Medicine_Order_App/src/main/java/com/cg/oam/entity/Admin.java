package com.cg.oam.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The Class Admin.
 */
@Entity
public class Admin {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	/** The password. */
	String password;
	
	/**
	 * Instantiates a new admin.
	 */
	public Admin() {}
	
	/**
	 * Instantiates a new admin.
	 *
	 * @param id the id
	 * @param password the password
	 */
	public Admin(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", password=" + password + "]";
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, password);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return id == other.id && Objects.equals(password, other.password);
	}
	
	
}
