package com.kien.smalltest.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author kienvt
 */
@Slf4j
public class JobListenerImpl implements JobListener {
    @Override
    public String getName() {
        return "JobListenerImpl";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("Job to Be executed");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("Job execution done");
    }
}
