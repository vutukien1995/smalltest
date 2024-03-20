package com.kien.smalltest.domain.biz.job;

import com.kien.smalltest.application.model.job.OutputGetStatus;
import com.kien.smalltest.infras.dto.Response;
import com.kien.smalltest.infras.enums.JobStatus;
import com.kien.smalltest.infras.handle.BadRequestException;
import com.kien.smalltest.job.quartz.JobInfo;
import com.kien.smalltest.job.quartz.QuartzJobService;
import com.kien.smalltest.ui.Job.IGetStatusService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author kienvt
 */
@Slf4j
@Service
public class GetStatusService implements IGetStatusService {

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

        Trigger.TriggerState triggerState = quartzJobService.getTriggerState(jobInfo);
        JobStatus status = Trigger.TriggerState.NORMAL.equals(triggerState) ? JobStatus.STARTING : JobStatus.STOPPING;

        OutputGetStatus outputGetStatus = OutputGetStatus.builder()
                .status(status.name())
                .build();

        return new Response<>(true, "Success", outputGetStatus, null);
    }

}
