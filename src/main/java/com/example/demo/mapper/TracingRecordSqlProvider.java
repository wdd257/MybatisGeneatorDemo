package com.example.demo.mapper;

import com.example.demo.entity.TracingRecord;
import com.example.demo.entity.TracingRecordQuery.Criteria;
import com.example.demo.entity.TracingRecordQuery.Criterion;
import com.example.demo.entity.TracingRecordQuery;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TracingRecordSqlProvider {

    public String countByExample(TracingRecordQuery example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("tracing_record");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TracingRecordQuery example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("tracing_record");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(TracingRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tracing_record");
        
        if (record.getAmLocationStr() != null) {
            sql.VALUES("am_location_str", "#{amLocationStr,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserInfo() != null) {
            sql.VALUES("user_info", "#{userInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getImei() != null) {
            sql.VALUES("imei", "#{imei,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(TracingRecordQuery example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("am_location_str");
        sql.SELECT("create_time");
        sql.SELECT("user_info");
        sql.SELECT("imei");
        sql.FROM("tracing_record");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        TracingRecord record = (TracingRecord) parameter.get("record");
        TracingRecordQuery example = (TracingRecordQuery) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("tracing_record");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAmLocationStr() != null) {
            sql.SET("am_location_str = #{record.amLocationStr,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserInfo() != null) {
            sql.SET("user_info = #{record.userInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getImei() != null) {
            sql.SET("imei = #{record.imei,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("tracing_record");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("am_location_str = #{record.amLocationStr,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("user_info = #{record.userInfo,jdbcType=VARCHAR}");
        sql.SET("imei = #{record.imei,jdbcType=VARCHAR}");
        
        TracingRecordQuery example = (TracingRecordQuery) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TracingRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("tracing_record");
        
        if (record.getAmLocationStr() != null) {
            sql.SET("am_location_str = #{amLocationStr,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserInfo() != null) {
            sql.SET("user_info = #{userInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getImei() != null) {
            sql.SET("imei = #{imei,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, TracingRecordQuery example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}