package com.fruit.mall_admin.reply;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyMapper {
    void insertReply(Reply reply);

    void updateReplied(@Param("reviewId") Long reviewId);
}
