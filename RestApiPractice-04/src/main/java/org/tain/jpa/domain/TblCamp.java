package org.tain.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_camp")
@Data
public class TblCamp {

	@Id
	private Long id;
	
	private String campCode;
	
	private String campContent;
}
