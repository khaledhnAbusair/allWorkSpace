/**
 * 
 */
package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author PSLPT 147
 *
 */
@Entity
@Table(name = "department")
public class Department {

	@Id
	@Column(name = "dept_no")
	private String deptNo;

	@Column(name = "dept_name")
	private String name;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
