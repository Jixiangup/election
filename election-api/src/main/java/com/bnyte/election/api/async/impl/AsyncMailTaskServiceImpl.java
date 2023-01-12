package com.bnyte.election.api.async.impl;

import com.bnyte.election.api.async.IAsyncMailTaskService;
import com.bnyte.election.api.common.constant.MailConstant;
import com.bnyte.election.api.common.util.StringUtils;
import com.bnyte.election.api.dao.ElectionDetailDAO;
import com.bnyte.election.api.entity.ElectionCandidate;
import com.bnyte.election.api.service.IElectionCandidateDetailService;
import com.bnyte.election.api.service.IElectionCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * 邮箱异步任务接口实现
 * @author bnyte
 * @since 1.0.0
 */
@Service
public class AsyncMailTaskServiceImpl implements IAsyncMailTaskService {

    @Autowired
    IElectionCandidateService electionCandidateService;

    @Autowired
    IElectionCandidateDetailService electionCandidateDetailService;

    @Autowired
    MailSender mailSender;

    @Async
    @Override
    public void senderElectionNotice(Long id) {

        List<ElectionDetailDAO> electionDetailDAOS = electionCandidateDetailService.queryVoteByElectionId(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MailConstant.FROM_MAIL);
        message.setSubject(MailConstant.ELECTION_NOTIFICATION_SUBJECT);
        StringJoiner mailContent = new StringJoiner("\n");
        Properties properties = new Properties();
        properties.setProperty("electionId", id.toString());
        mailContent.add(StringUtils.replacePlaceholders(MailConstant.ELECTION_CONTENT_TEMPLATE, properties));

        // 第一次循环组装消息文本
        List<ElectionCandidate> electionCandidates = electionCandidateService.listByElectionId(id);
        for (ElectionCandidate electionCandidate : electionCandidates) {
            properties.setProperty("candidateId", electionCandidate.getCandidateId().toString());
            properties.setProperty("count", electionCandidate.getCount().toString());
            mailContent.add(StringUtils.replacePlaceholders(MailConstant.ELECTION_CANDIDATE_VOTE_RESULT_TEMPLATE, properties));
        }
        for (ElectionDetailDAO electionDetailDAO : electionDetailDAOS) {
            message.setTo(electionDetailDAO.getEmail());
            message.setText(mailContent.toString());
            mailSender.send(message);
        }

    }
}
