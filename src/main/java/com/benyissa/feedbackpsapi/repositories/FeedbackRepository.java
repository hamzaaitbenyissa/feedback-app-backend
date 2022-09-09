package com.benyissa.feedbackpsapi.repositories;

import com.benyissa.feedbackpsapi.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
