package com.jwj.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
public class JobRunner implements ApplicationRunner {

	private JobLauncher jobLauncher;
	private Job job;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("name", "user2")
				.toJobParameters();
		jobLauncher.run(job, jobParameters);
	}
}
