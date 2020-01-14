package com.example.demo.mapper;

import com.example.demo.entity.TracingRecord;
import com.example.demo.entity.TracingRecordQuery;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface TracingRecordMapper {
    @SelectProvider(type=TracingRecordSqlProvider.class, method="countByExample")
    long countByExample(TracingRecordQuery example);

    @DeleteProvider(type=TracingRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(TracingRecordQuery example);

    @Delete({
        "delete from tracing_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tracing_record (am_location_str, create_time, ",
        "user_info, imei)",
        "values (#{amLocationStr,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{userInfo,jdbcType=VARCHAR}, #{imei,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TracingRecord record);

    @InsertProvider(type=TracingRecordSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TracingRecord record);

    @SelectProvider(type=TracingRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="am_location_str", property="amLocationStr", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_info", property="userInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="imei", property="imei", jdbcType=JdbcType.VARCHAR)
    })
    List<TracingRecord> selectByExample(TracingRecordQuery example);

    @Select({
        "select",
        "id, am_location_str, create_time, user_info, imei",
        "from tracing_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="am_location_str", property="amLocationStr", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_info", property="userInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="imei", property="imei", jdbcType=JdbcType.VARCHAR)
    })
    TracingRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TracingRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TracingRecord record, @Param("example") TracingRecordQuery example);

    @UpdateProvider(type=TracingRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TracingRecord record, @Param("example") TracingRecordQuery example);

    @UpdateProvider(type=TracingRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TracingRecord record);

    @Update({
        "update tracing_record",
        "set am_location_str = #{amLocationStr,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "user_info = #{userInfo,jdbcType=VARCHAR},",
          "imei = #{imei,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TracingRecord record);
}