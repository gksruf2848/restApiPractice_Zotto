package org.tain.working.load.tables;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.jpa.domain.TblBuilding;
import org.tain.jpa.repository.TblBuildingRepository;
import org.tain.tools.properties.ProjEnvParam;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TblBuildingLoader {

	@Autowired
	private ProjEnvParam projEnvParam;
	
	@Autowired
	private TblBuildingRepository tblBuildingRepository;
	long id = 1L;
	
	public void loading() throws Exception {
		log.info("HANLIM-20220220 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.tblBuildingRepository.deleteAll();
		}
		
		if (Boolean.TRUE) {
			String fileName = this.projEnvParam.getBasePath() + File.separator + this.projEnvParam.getBuildingFile();
			String jsonData = StringTools.stringFromFile(fileName);
			List<TblBuilding> lst = new ObjectMapper().readValue(jsonData, new TypeReference<List<TblBuilding>>() {});
			lst.forEach(itm -> {
				itm.setId(id);
				this.tblBuildingRepository.save(itm);
				this.id ++;
			});
		}
	}
}
