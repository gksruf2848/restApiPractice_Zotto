package org.tain.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_stud_lesn")
@Data
public class TblStudLesn {

	@Id
	private Long id;
	
	@Column(name = "stud_code")
	private String studCode;
	
	@Column(name = "lesn_code")
	private String lesnCode;
	
	@Column(name = "score")
	private String score;
}
