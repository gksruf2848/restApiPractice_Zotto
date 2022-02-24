package org.tain.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_lesn")
@Data
public class TblLesn {

	@Id
	private Long id;
	
	private String code;
	
	private String name;
}
