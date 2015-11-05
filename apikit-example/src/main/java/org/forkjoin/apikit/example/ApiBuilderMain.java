package org.forkjoin.apikit.example;

import java.io.File;
import java.io.IOException;

import org.forkjoin.apikit.ApiBuilder;
import org.forkjoin.apikit.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiBuilderMain {
	private static final Logger log = LoggerFactory
			.getLogger(ApiBuilderMain.class);

	/**
     *
     */
	public static void main(String[] args) throws IOException {
		final String version = "v1";
		File dir = new File("apikit-example/src/main/java/");
		if (!dir.exists()) {
			dir = new File("src/main/java/");
		}

		// TODO 修改下面的乱七八糟的路径
		log.info("代码路径:{}", dir.getAbsolutePath());

		Config cfg = new Config();
		cfg.setPath(dir.getAbsolutePath());
		cfg.setRootPackage("org.forkjoin.apikit.example");

		ApiBuilder builder = new ApiBuilder(cfg);
		builder.analyse();
		builder.check();

	}
}
