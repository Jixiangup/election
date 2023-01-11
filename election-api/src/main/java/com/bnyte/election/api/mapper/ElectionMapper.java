package com.bnyte.election.api.mapper;

import com.bnyte.election.api.entity.Election;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * 选举ORM接口抽象
 * @author bnyte
 * @since 1.0.0
 */
public interface ElectionMapper {
    Election selectById(@Param("id") Serializable id);

    void updateById(@Param("item") Election election);

    void insert(@Param("item") Election election);
}
