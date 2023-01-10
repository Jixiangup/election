package com.bnyte.election.api.mapstruct.election;

import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.param.election.EditorElectionParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 选举模块转换器
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface ElectionTransfer {

    ElectionTransfer INSTANCE = Mappers.getMapper(ElectionTransfer.class);

    EditorElectionDTO payload2EditorDTO(EditorElectionParam param);

}
