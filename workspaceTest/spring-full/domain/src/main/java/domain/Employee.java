/**
 * 
 */
package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author PSLPT 147
 *
 */
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@NotNull(message = "firstName is required")
	@Size(min = 1, max = 30, message = "First name size must be less than 30")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull(message = "lastName is required")
	@Size(min = 1, max = 30, message = "last name size must be less than 30")

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotNull(message = "position is required")
	@Column(name = "position", nullable = false)
	private String position;

	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Department department;

	@OneToOne
	@JoinColumn(name = "picId")
	private Attachment picature;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Attachment getPicature() {
		return picature;
	}

	public void setPicature(Attachment picature) {
		this.picature = picature;
	}
}
