package com.kien.smalltest.domain.biz.job;

import com.kien.smalltest.infras.dto.Response;
import com.kien.smalltest.job.quartz.JobInfo;
import com.kien.smalltest.job.quartz.QuartzJobService;
import com.kien.smalltest.ui.Job.IStopJobService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kienvt
 */
@Service
public class StopJobService implements IStopJobService {

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

        quartzJobService.stopJob(jobInfo);
        return new Response<>(true, "Record job is stopped", null, null);
    }

}
