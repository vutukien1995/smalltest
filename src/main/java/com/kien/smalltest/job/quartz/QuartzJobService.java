package com.kien.smalltest.job.quartz;

import com.kien.smalltest.job.listener.JobListenerImpl;
import com.kien.smalltest.job.listener.TriggerListenerImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kienvt
 */
@Slf4j
@Component
public class QuartzJobService  {

    List<JobInfo> jobInfoList = new ArrayList<>();

    @Autowired
    private Scheduler scheduler;

    @Bean(initMethod="init")
    private void init () {
        try {
            Properties jobsProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("jobs.properties"));
            String listJob = jobsProperties.getProperty("list.job.schedule");
            String[] listJobArr = listJob.split(",");

            for (String jobName : listJobArr) {
                JobInfo jobInfo = JobInfo.builder()
                        .name(jobsProperties.getProperty(jobName + ".name"))
                        .cronExpression(jobsProperties.getProperty(jobName + ".cron.expression"))
                        .jobClass(jobsProperties.getProperty(jobName + ".job.class"))
                        .build();
                jobInfoList.add(jobInfo);
                log.info("## Job:");
                log.info(jobInfo.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            scheduler.clear();

            for (JobInfo jobInfo : jobInfoList) {
                JobDetail jobDetail = buildJobDetail(jobInfo);
                Trigger trigger = buildTrigger(jobDetail, jobInfo);

                scheduler.getListenerManager().addTriggerListener(new TriggerListenerImpl());
                scheduler.getListenerManager().addJobListener(new JobListenerImpl());
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }

    public List<JobInfo> getJobInfoList() {
        return jobInfoList;
    }

    @SneakyThrows
    public Set<JobKey> getAllJobs() {
        return scheduler.getJobKeys(GroupMatcher.anyGroup());
    }

    public List<TimerInfo> getAll() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup())
                    .stream()
                    .map(jobKey -> {
                        try {
                            final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                        } catch (final SchedulerException e) {
                            log.error(e.getMessage(), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (final SchedulerException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @SneakyThrows
    public void startJob(JobInfo jobInfo) {
        scheduler.resumeTrigger(new TriggerKey(jobInfo.getName()+"-trigger"));
    }

    @SneakyThrows
    public void stopJob(JobInfo jobInfo) {
        scheduler.pauseTrigger(new TriggerKey(jobInfo.getName()+"-trigger"));
    }

    @SneakyThrows
    public Trigger.TriggerState getTriggerState(JobInfo jobInfo) {
        return scheduler.getTriggerState(new TriggerKey(jobInfo.getName()+"-trigger"));
    }



    // ============================== PRIVATE FUNCTION ================================
    private JobDetail buildJobDetail(JobInfo jobInfo) {

        JobDataMap jobDataMap = new JobDataMap();

        try {
            return JobBuilder.newJob((Class<? extends Job>) Class.forName(jobInfo.getJobClass()))
                    .withIdentity(jobInfo.getName())
                    .usingJobData(jobDataMap)
                    .storeDurably()
                    .build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Trigger buildTrigger(JobDetail jobDetail, JobInfo jobInfo) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobInfo.getName()+"-trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression()))
                .build();
    }

}
