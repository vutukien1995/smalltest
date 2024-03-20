package com.kien.smalltest.domain.biz.record;

import com.kien.smalltest.application.model.record.InputSaveRecord;
import com.kien.smalltest.domain.entity.Record;
import com.kien.smalltest.infras.dto.Response;
import com.kien.smalltest.infras.repository.RecordRepository;
import com.kien.smalltest.ui.Record.ICreateRecordService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author kienvt
 */
@Service
public class CreateRecordService implements ICreateRecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void validate(InputSaveRecord request) {
    }

    @SneakyThrows
    @Override
    public Object process(InputSaveRecord request, Object principal) {
        Record newRecord = Record.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdDate(LocalDateTime.now())
                .build();
        recordRepository.save(newRecord);

        return new Response<>(true, "Saved a record !!", newRecord, null);
    }

}
