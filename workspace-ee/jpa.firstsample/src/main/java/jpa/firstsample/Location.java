/**
 * 
 */
package jpa.firstsample;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author PSLPT 147
 *
 */
@Entity(name = "LocationEntity")
@Table(name = "locations")
public class Location implements Serializable {
	 private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "loc_lat", nullable = false, scale = 2, precision = 4)
	private Double latitude;

	@Column(name = "loc_lon", nullable = false, scale = 2, precision = 4)
	private Double longitude;

	@Column(name = "loc_name", nullable = false, length = 40)
	private String name;

	@Column(name = "loc_desc", nullable = false, length = 150)
	private String description;

	private ActiveFlag activeFlag;

	public ActiveFlag getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(ActiveFlag activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
