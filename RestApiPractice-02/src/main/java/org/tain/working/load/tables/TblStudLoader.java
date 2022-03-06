package org.tain.working.load.tables;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.jpa.domain.TblStud;
import org.tain.jpa.repository.TblStudRepository;
import org.tain.tools.properties.ProjEnvParam;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TblStudLoader {

	@Autowired
	private ProjEnvParam projEnvParam;
	
	@Autowired
	private TblStudRepository tblStudRepository;
	long id = 1L;
	
	public void loading() throws Exception {
		log.info("HANLIM-20220227 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.tblStudRepository.deleteAll();
		}
		
		if (Boolean.TRUE) {
			String fileName = this.projEnvParam.getBasePath() + File.separator + this.projEnvParam.getStudFile();
			String jsonData = StringTools.stringFromFile(fileName);
			List<TblStud> lst = new ObjectMapper().readValue(jsonData, new TypeReference<List<TblStud>>() {});
			lst.forEach(itm -> {
				itm.setId(id);
				this.tblStudRepository.save(itm);
				this.id ++;
			});
		}
	}
}
