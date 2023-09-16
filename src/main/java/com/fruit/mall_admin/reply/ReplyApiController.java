package com.fruit.mall_admin.reply;

import com.fruit.mall_admin.reply.dto.ReplySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping("/update-reply")
    public ResponseEntity<Void> updateReply(@RequestBody ReplySaveDto dto) {
        replyService.updateComments(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show-reply")
    public ResponseEntity<?> showReply(@RequestParam Long reviewId) {
        Optional<String> replyContents = replyService.selectReplyByReviewId(reviewId);
        return ResponseEntity.ok(replyContents.orElse("답글이 없습니다."));
    }
}
