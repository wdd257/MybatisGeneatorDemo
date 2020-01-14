package com.example.demo.controller;

import com.example.demo.dao.TracingRecordDao;
import com.example.demo.dto.QueryParam;
import com.example.demo.entity.TracingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TracingRecordController {

    @Autowired
    TracingRecordDao tracingRecordDao;

    @RequestMapping("/saveRecord")
    public void saveRecord(@RequestBody List<TracingRecord> records) {
        for (TracingRecord record : records) {
            tracingRecordDao.saveRecord(record);
        }
    }

    @RequestMapping("/getRecord")
    public List<TracingRecord> getRecord(@RequestBody QueryParam param) {
        List<TracingRecord> records = tracingRecordDao.getRecord(param);
        return records;
    }
}
