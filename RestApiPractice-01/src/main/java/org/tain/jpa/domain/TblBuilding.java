package org.tain.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_building")
@Data
public class TblBuilding {

	@Id
	private Long id;
	
	private String code;
	
	private String name;
	
	private String address;
	
	private Integer size;
	
	private String desc;
}
