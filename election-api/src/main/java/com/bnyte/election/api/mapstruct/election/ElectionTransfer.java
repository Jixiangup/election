package com.bnyte.election.api.mapstruct.election;

import com.bnyte.election.api.dto.election.EditorElectionDTO;
import com.bnyte.election.api.dto.election.VoteDTO;
import com.bnyte.election.api.entity.ElectionCandidate;
import com.bnyte.election.api.param.election.EditorElectionParam;
import com.bnyte.election.api.param.election.VoteParam;
import com.bnyte.election.api.vo.election.ElectionResultVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 选举模块转换器
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface ElectionTransfer {

    ElectionTransfer INSTANCE = Mappers.getMapper(ElectionTransfer.class);

    EditorElectionDTO payload2EditorDTO(EditorElectionParam param);

    VoteDTO payload2VoteDTO(VoteParam param);

    ElectionResultVO toResultVO(ElectionCandidate electionCandidate);

    List<ElectionResultVO> toResultVO(List<ElectionCandidate> electionCandidate);

}
