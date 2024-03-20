package com.kien.smalltest.application.controller;

import com.kien.smalltest.application.model.record.InputGetRecord;
import com.kien.smalltest.application.model.record.InputSaveRecord;
import com.kien.smalltest.ui.Record.ICreateRecordService;
import com.kien.smalltest.ui.Record.IGetRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kienvt
 */
@RestController
@RequestMapping("records")
public class RecordController {

    @Autowired
    private ICreateRecordService createRecord;

    @Autowired
    private IGetRecordService getRecord;

    @PostMapping("")
    public Object create(@RequestBody @Valid InputSaveRecord inputSaveRecord) {
        String principal = "kienvt";
        return createRecord.execute(inputSaveRecord, principal);
    }

    @GetMapping("")
    public Object getRecord(@Valid InputGetRecord inputGetRecord) {
        return getRecord.execute(inputGetRecord);
    }
}
