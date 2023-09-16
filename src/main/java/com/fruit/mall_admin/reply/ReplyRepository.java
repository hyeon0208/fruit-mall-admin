package com.fruit.mall_admin.reply;

import com.fruit.mall_admin.reply.dto.ReplySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ReplyRepository implements ReplyMapper {
    private final ReplyMapper replyMapper;

    @Override
    public void insertReply(Reply reply) {
        replyMapper.insertReply(reply);
    }

    @Override
    public void updateReplied(Long reviewId) {
        replyMapper.updateReplied(reviewId);
    }

    @Override
    public Optional<String> selectReplyByReviewId(Long reviewId) {
        return replyMapper.selectReplyByReviewId(reviewId);
    }

    @Override
    public void updateComments(Reply reply) {
        replyMapper.updateComments(reply);
    }
}
