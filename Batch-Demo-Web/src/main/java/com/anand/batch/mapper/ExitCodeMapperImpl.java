package com.anand.batch.mapper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ExitCodeMapperImpl implements ExitCodeMapper , InitializingBean {

	private Map<String,Integer> exitCodes;
	
	@Override
	public int intValue(String exitCode) {
		return exitCodes.get(exitCode);
	}

	public void setExitCodes(Map<String, Integer> exitCodes) {
		this.exitCodes = exitCodes;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		exitCodes = new LinkedHashMap<String,Integer>();
		exitCodes.put(ExitStatus.COMPLETED.toString(), 0);
		exitCodes.put(ExitStatus.EXECUTING.toString(), 1);
		exitCodes.put(ExitStatus.FAILED.toString(), 2);
		exitCodes.put(ExitStatus.NOOP.toString(), 3);
		exitCodes.put(ExitStatus.STOPPED.toString(), 4);
		exitCodes.put(ExitStatus.UNKNOWN.toString(), 5);
	}

}
