package com.kien.smalltest.domain.biz.record;

import com.kien.smalltest.application.model.record.InputGetRecord;
import com.kien.smalltest.domain.entity.Record;
import com.kien.smalltest.infras.dto.PageResponse;
import com.kien.smalltest.infras.repository.RecordRepository;
import com.kien.smalltest.ui.Record.IGetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author kienvt
 */
@Service
public class GetRecordService implements IGetRecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void validate(InputGetRecord request) {
    }

    @Override
    public Object process(InputGetRecord request) {
        Pageable paging = PageRequest.of(request.getPage(), request.getLimit(), Sort.by("id"));
        Page<Record> recordPage = recordRepository.findAll(paging);

        return new PageResponse<>(true, recordPage.getTotalPages(), recordPage.getNumber(), recordPage.getSize(), recordPage.get());
    }

}
