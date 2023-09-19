package com.fruit.mall_admin.notifications;

import com.fruit.common.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsService {
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public void commentNotificationCreate(String userId, String message) {
        NotificationMessage notificationMessage = new NotificationMessage(userId , message);
        log.info("리뷰 답글 알림 전송. userId : {}, message : {}",userId, message);
        kafkaTemplate.send("comment-notifications", notificationMessage);
    }
}