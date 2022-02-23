package org.tain.working.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.load.tables.TblBuildingLoader;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadWorking {

	public void doing() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			if (Boolean.TRUE) loadTblBuilding();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblBuildingLoader tblBuildingLoader;
	
	private void loadTblBuilding() throws Exception {
		log.info("HANLIM-20220220 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblBuildingLoader.loading();
	}
}
