package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testMain {
	private final static Logger logger = LoggerFactory.getLogger(testMain.class);
	
	public static void main(String[] args) {
		testMain tm = new testMain();
		tm.testSlf4j();

	}
	
	public void testSlf4j() {
		logger.debug("====debug===={}", "test测试");
		logger.info("====info===={}", "test测试");
		logger.warn("====warn===={}", "test测试");
		logger.error("====error===={}", "test测试");
	}
}
