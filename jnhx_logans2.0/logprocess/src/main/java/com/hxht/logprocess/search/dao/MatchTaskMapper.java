package com.hxht.logprocess.search.dao;

import com.hxht.logprocess.search.model.MatchTaskEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchTaskMapper {

    void save(MatchTaskEntity matchTask);

    int isExist(String tableName);

    List<MatchTaskEntity> getList();

    void deleteById(String id);
}
