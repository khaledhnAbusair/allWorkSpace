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

	@Column(name = "first_name", nullable = false)
	@NotNull(message = "First name is required")
	@Size(min = 1, max = 30, message = "First name size must be less than 30")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotNull(message = "Last name is required")
	@Size(min = 1, max = 30, message = "Last name size must be less than 30")
	private String lastName;

	@Column(name = "position")
	private String position;

	@OneToOne
	@JoinColumn(name = "pic_id")
	private Attachment picture;

	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Department department;

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

	public Attachment getPicture() {
		return picture;
	}

	public void setPicture(Attachment picture) {
		this.picture = picture;
	}

}
