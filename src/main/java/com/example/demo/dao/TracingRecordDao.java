package com.example.demo.dao;

import com.example.demo.dto.QueryParam;
import com.example.demo.entity.TracingRecord;
import com.example.demo.entity.TracingRecordQuery;
import com.example.demo.mapper.TracingRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TracingRecordDao {
    @Autowired
    TracingRecordMapper tracingRecordMapper;

    public List<TracingRecord> getRecord(QueryParam param) {
        TracingRecordQuery tracingRecordQuery = new TracingRecordQuery();

        TracingRecordQuery.Criteria criteria = tracingRecordQuery.createCriteria();
        if (param.getBathSize() != null) {
            //todo
        }
        if (param.getHours() != null && param.getHours() != 0) {
            criteria.andCreateTimeBetween(getBeforeByHourTime(param.getId()), new Date());
        }
        if (param.getId() != null && param.getId() != 0) {
            criteria.andIdEqualTo(param.getId());
        }
        if (param.getUserInfo() != null && !param.getUserInfo().isEmpty()) {
            criteria.andUserInfoEqualTo(param.getUserInfo());
        }
        if (param.getImei() != null && !param.getImei().isEmpty()) {
            criteria.andImeiEqualTo(param.getImei());
        }

        return tracingRecordMapper.selectByExample(tracingRecordQuery);
    }

    public static Date getBeforeByHourTime(int ihour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        return calendar.getTime();
    }

    public int saveRecord(TracingRecord tracingRecord) {
        return tracingRecordMapper.insert(tracingRecord);
    }
}
