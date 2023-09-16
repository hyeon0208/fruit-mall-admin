package com.fruit.mall_admin.reply;

import com.fruit.mall_admin.reply.dto.ReplySaveDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ReplyMapper {
    void insertReply(Reply reply);

    void updateReplied(@Param("reviewId") Long reviewId);

    Optional<String> selectReplyByReviewId(@Param("reviewId") Long reviewId);

    void updateComments(Reply reply);
}
