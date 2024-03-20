package com.kien.smalltest.domain.biz.job;

import com.kien.smalltest.job.quartz.QuartzJobService;
import com.kien.smalltest.ui.Job.IGetJobService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kienvt
 */
@Slf4j
@Service
public class GetJobService implements IGetJobService {

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void validate(Object request) {
    }

    @SneakyThrows
    @Override
    public Object process(Object request) {
        return quartzJobService.getAllJobs();
    }

}
