package org.tain.working.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.load.tables.TblLesnLoader;
import org.tain.working.load.tables.TblStudLesnLoader;
import org.tain.working.load.tables.TblStudLoader;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadWorking {

	public void doing() throws Exception {
		log.info("HANLIM-20220227 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			if (Boolean.TRUE) loadTblStud();
			if (Boolean.TRUE) loadTblLesn();
			if (Boolean.TRUE) loadTblStudLesn();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblStudLoader tblStudLoader;
	
	private void loadTblStud() throws Exception {
		log.info("HANLIM-20220227 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblStudLoader.loading();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblLesnLoader tblLesnLoader;
	
	private void loadTblLesn() throws Exception {
		log.info("HANLIM-20220227 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblLesnLoader.loading();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblStudLesnLoader tblStudLesnLoader;
	
	private void loadTblStudLesn() throws Exception {
		log.info("HANLIM-20220227 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblStudLesnLoader.loading();
	}
}
