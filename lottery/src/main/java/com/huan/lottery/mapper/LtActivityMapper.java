package com.huan.lottery.mapper;

import com.huan.lottery.pojo.LtActivity;
import com.huan.lottery.pojo.LtActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LtActivityMapper {
    int countByExample(LtActivityExample example);

    int deleteByExample(LtActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LtActivity record);

    int insertSelective(LtActivity record);

    List<LtActivity> selectByExample(LtActivityExample example);

    LtActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LtActivity record, @Param("example") LtActivityExample example);

    int updateByExample(@Param("record") LtActivity record, @Param("example") LtActivityExample example);

    int updateByPrimaryKeySelective(LtActivity record);

    int updateByPrimaryKey(LtActivity record);
}