package com.hxht.logprocess.logbulk.dao;

import com.hxht.logprocess.logbulk.model.RulesTemplate;
import com.hxht.logprocess.logbulk.model.RulesTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RulesTemplateMapper {
    long countByExample(RulesTemplateExample example);

    int deleteByExample(RulesTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(RulesTemplate record);

    int insertSelective(RulesTemplate record);

    List<RulesTemplate> selectByExample(RulesTemplateExample example);

    RulesTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RulesTemplate record, @Param("example") RulesTemplateExample example);

    int updateByExample(@Param("record") RulesTemplate record, @Param("example") RulesTemplateExample example);

    int updateByPrimaryKeySelective(RulesTemplate record);

    int updateByPrimaryKey(RulesTemplate record);

    List<RulesTemplate> selectByConditions(@Param("from")int from,@Param("size")int size);


}