package org.gdgjss.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

/**
 * Entity for accessing field values like timer, marking scheme etc.
 * @author Tilhari
 *
 */

@Entity
@Repository
public class Constraints {
	@Id
	private String field;
	private String value;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
