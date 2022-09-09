package com.benyissa.feedbackpsapi.web;

import com.benyissa.feedbackpsapi.entities.Feedback;
import com.benyissa.feedbackpsapi.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping(path = "/feedbacks")
    public List<Feedback> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    /*find a feedback by id */
    @GetMapping(path = "/feedbacks/{id}")
    public Feedback GetFeedback(@PathVariable Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    // add a new feedback
    @PostMapping(path = "/feedbacks")
    public Feedback AddStudent(@RequestBody Feedback newfeedback) {
        return feedbackRepository.save(newfeedback);
    }


    // edit an old feedback
    @PutMapping(path = "/feedbacks/{id}")
    public Feedback UpdateFeedback(@RequestBody Feedback updatedFeedback, @PathVariable Long id) {

        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setText(updatedFeedback.getText());
            feedback.setRating(updatedFeedback.getRating());
            return feedbackRepository.save(feedback);
        }).orElseGet(() -> {
            updatedFeedback.setId(id);
            return feedbackRepository.save(updatedFeedback);
        });
    }


    /*delete a feedback by id */
    @DeleteMapping("/feedbacks/{id}")
    public void DeleteFeedback(@PathVariable Long id) {
        if (feedbackRepository.findById(id).isPresent()) {
            feedbackRepository.deleteById(id);
        }
    }


}
