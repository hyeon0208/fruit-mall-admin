package com.fruit.mall_admin.reply;

import com.fruit.mall_admin.reply.dto.ReplySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;
    @PostMapping("/reply")
    public ResponseEntity<Void> insertReply(@RequestBody ReplySaveDto dto) {
        replyService.insertReplyAndReviewStatusUpdate(dto);
        return ResponseEntity.ok().build();
    }
}
