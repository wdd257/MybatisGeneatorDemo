package com.example.demo.dao;

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

    public List<TracingRecord> getRecord(int hours, String userInfo) {
        TracingRecordQuery tracingRecordQuery = new TracingRecordQuery();
        if (hours == 0) {
            hours = 2;
        }
        TracingRecordQuery.Criteria criteria = tracingRecordQuery.createCriteria().andCreatTimeBetween(getBeforeByHourTime(hours), new Date());
        if (userInfo != null && !userInfo.isEmpty()) {
            criteria.andUserInfoEqualTo(userInfo);
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
