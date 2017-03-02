package com.progressoft.jip.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.progressoft.jip.entity.view.UserView;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable, UserView {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name", nullable = false)
	@NotNull(message = "userName is required")
	private String userName;

	@Column(name = "full_name", nullable = false)
	@NotNull(message = "fullName is required")
	private String fullName;

	@Column(name = "password", nullable = false)
	@NotNull(message = "password is required")
	@Size(min = 5, message = "password size must be more than 5 ")
	private String password;

	@Column(name = "is_disable")
	private Boolean isDisable = true;

	@Transient
	private String confirmPassword;



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.UserView#getFullName()
	 */
	@Override
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.UserView#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.UserView#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.UserView#getIsDisable()
	 */
	@Override
	public Boolean getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

}
