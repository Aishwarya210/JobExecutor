package org.kp.samdl.biz.metadataloader.scheduler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author A670691
 *
 */
@Component
@EnableScheduling
public class MetadataLoaderScheduler {
	
	private static Logger log = LoggerFactory.getLogger(MetadataLoaderScheduler.class);
	
	
	/**
	 * method execute after every configured fixedDelay/fixedrate
	 * to run the scheduler. 
	 */
	
		@Scheduled(fixedRate = 30000)
			//@Scheduled(cron="0 52 21 * * *")
			//@Scheduled(cron="${app.salesconnect.oncall.scheduler.cron}")
			public void test() throws IOException {
				//ReadFiles();
				//MoveFile();
				System.out.println("Calling");
			}
		 	
	
}
	
