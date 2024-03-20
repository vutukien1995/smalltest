package com.kien.smalltest.application.controller;

import com.kien.smalltest.ui.Job.IGetJobService;
import com.kien.smalltest.ui.Job.IGetStatusService;
import com.kien.smalltest.ui.Job.IStartJobService;
import com.kien.smalltest.ui.Job.IStopJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kienvt
 */
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    IGetJobService getJobService;

    @Autowired
    IGetStatusService getStatus;

    @Autowired
    IStartJobService startJob;

    @Autowired
    IStopJobService stopStatus;

    @GetMapping("/getJob")
    public Object getJob() {
        return getJobService.execute(null);
    }

    @GetMapping("/getStatus")
    public Object getRecord(@RequestParam(name = "jobName", defaultValue = "saveRecord") String jobName) {
        return getStatus.execute(jobName);
    }

    @GetMapping("/startJob")
    public Object startStatus(@RequestParam(name = "jobName", defaultValue = "saveRecord") String jobName) {
        return startJob.execute(jobName);
    }

    @GetMapping("/stopJob")
    public Object stopStatus(@RequestParam(name = "jobName", defaultValue = "saveRecord") String jobName) {
        return stopStatus.execute(jobName);
    }

}
