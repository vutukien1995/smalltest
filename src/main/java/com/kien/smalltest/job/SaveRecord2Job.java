package com.kien.smalltest.job;

import com.kien.smalltest.domain.entity.Record;
import com.kien.smalltest.infras.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author kienvt
 */
@Slf4j
public class SaveRecord2Job extends QuartzJobBean {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Record job 2 is executed !");
        Record record = Record.builder()
                .title("Record 2")
                .content("This is small test")
                .createdDate(LocalDateTime.now())
                .build();
        recordRepository.save(record);
        log.info("Record job 2 is done !");
    }
}
