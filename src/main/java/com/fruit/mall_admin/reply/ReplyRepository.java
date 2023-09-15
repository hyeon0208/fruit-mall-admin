package com.fruit.mall_admin.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


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
}
