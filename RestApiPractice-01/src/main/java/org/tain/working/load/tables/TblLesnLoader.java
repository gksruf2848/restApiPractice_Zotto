package org.tain.working.load.tables;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.jpa.domain.TblLesn;
import org.tain.jpa.repository.TblLesnRepository;
import org.tain.tools.properties.ProjEnvParam;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TblLesnLoader {

	@Autowired
	private ProjEnvParam projEnvParam;
	
	@Autowired
	private TblLesnRepository tblLesnRepository;
	long id = 1L;
	
	public void loading() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.tblLesnRepository.deleteAll();
		}
		
		if (Boolean.TRUE) {
			String fileName = this.projEnvParam.getBasePath() + File.separator + this.projEnvParam.getLesnFile();
			String jsonData = StringTools.stringFromFile(fileName);
			List<TblLesn> lst = new ObjectMapper().readValue(jsonData, new TypeReference<List<TblLesn>>() {});
			lst.forEach(itm -> {
				itm.setId(id);
				this.tblLesnRepository.save(itm);
				this.id ++;
			});
		}
	}
}
