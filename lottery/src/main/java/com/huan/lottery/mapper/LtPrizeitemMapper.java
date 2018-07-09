package com.huan.lottery.mapper;

import com.huan.lottery.pojo.LtPrizeitem;
import com.huan.lottery.pojo.LtPrizeitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LtPrizeitemMapper {
    int countByExample(LtPrizeitemExample example);

    int deleteByExample(LtPrizeitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LtPrizeitem record);

    int insertSelective(LtPrizeitem record);

    List<LtPrizeitem> selectByExample(LtPrizeitemExample example);

    LtPrizeitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LtPrizeitem record, @Param("example") LtPrizeitemExample example);

    int updateByExample(@Param("record") LtPrizeitem record, @Param("example") LtPrizeitemExample example);

    int updateByPrimaryKeySelective(LtPrizeitem record);

    int updateByPrimaryKey(LtPrizeitem record);
}