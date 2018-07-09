package com.huan.lottery.mapper;

import com.huan.lottery.pojo.LtUser;
import com.huan.lottery.pojo.LtUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LtUserMapper {
    int countByExample(LtUserExample example);

    int deleteByExample(LtUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LtUser record);

    int insertSelective(LtUser record);

    List<LtUser> selectByExample(LtUserExample example);

    LtUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LtUser record, @Param("example") LtUserExample example);

    int updateByExample(@Param("record") LtUser record, @Param("example") LtUserExample example);

    int updateByPrimaryKeySelective(LtUser record);

    int updateByPrimaryKey(LtUser record);
}