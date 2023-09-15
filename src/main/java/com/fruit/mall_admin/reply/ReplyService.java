package com.fruit.mall_admin.reply;

import com.fruit.mall_admin.reply.dto.ReplySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public void insertReplyAndReviewStatusUpdate(ReplySaveDto dto) {
        Reply reply = dto.toEntity(dto);
        replyRepository.insertReply(reply);
        replyRepository.updateReplied(dto.getReviewId());
    }
}
