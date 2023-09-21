package com.fruit.mall_admin.notifications;

import com.fruit.mall_admin.notifications.dto.NotificationsDto;
import com.fruit.mall_admin.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class NotificationsController {
    private final NotificationsService notificationsService;
    private final ReviewService reviewService;

    @PostMapping("/reply/notifications")
    public ResponseEntity<?> sendReplyNotifications(@RequestBody NotificationsDto dto) {
        Long userId = reviewService.selectUserIdByReviewId(dto.getReviewId());
        String message = dto.getProductName() + " 상품의 리뷰에 판매자가 댓글을 남겼습니다.";
        notificationsService.commentNotificationCreate(String.valueOf(userId), dto.getReviewId(), message);
        return ResponseEntity.ok().build();
    }
}
