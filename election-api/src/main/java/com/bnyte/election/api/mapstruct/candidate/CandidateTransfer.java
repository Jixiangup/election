package com.bnyte.election.api.mapstruct.candidate;

import com.bnyte.election.api.dto.candidate.EditorCandidateDTO;
import com.bnyte.election.api.param.candidate.EditorCandidateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 *     候选人数据转换
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface CandidateTransfer {
    CandidateTransfer INSTANCE = Mappers.getMapper(CandidateTransfer.class);

    EditorCandidateDTO param2DTO(EditorCandidateParam param);

}
