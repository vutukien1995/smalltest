package com.kien.smalltest.domain.biz.job;

import com.kien.smalltest.infras.dto.Response;
import com.kien.smalltest.infras.handle.BadRequestException;
import com.kien.smalltest.job.listener.JobListenerImpl;
import com.kien.smalltest.job.listener.TriggerListenerImpl;
import com.kien.smalltest.job.quartz.JobInfo;
import com.kien.smalltest.job.quartz.QuartzJobService;
import com.kien.smalltest.ui.Job.IStartJobService;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author kienvt
 */
@Service
public class StartJobService implements IStartJobService {

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void validate(String request) {
    }

    @SneakyThrows
    @Override
    public Object process(String request) {

        List<JobInfo> infoList = quartzJobService.getJobInfoList();
        JobInfo jobInfo = infoList.stream()
                .filter(x -> request.equals(x.getName()))
                .findAny()
                .orElse(null);

        if (ObjectUtils.isEmpty(jobInfo))
            throw new BadRequestException("job.jobName.not.found");

        quartzJobService.startJob(jobInfo);
        return new Response<>(true, "Record job is started", null, null);
    }

}
