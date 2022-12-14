package com.dekaveenvelopamentos.dekave.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dekaveenvelopamentos.dekave.domain.entity.Feedbacks;
import com.dekaveenvelopamentos.dekave.dto.ActiveDTO;
import com.dekaveenvelopamentos.dekave.dto.FeedbacksDTO;
import com.dekaveenvelopamentos.dekave.exception.ReorderActionException;
import com.dekaveenvelopamentos.dekave.exception.ReorderPositionException;
import com.dekaveenvelopamentos.dekave.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/feedbacks")
public class FeedbacksController {

    final String feedbacksTag = "Feedbacks";

    @Autowired
    private FeedbackService service;

    @Operation(summary = "Get by id.", tags = feedbacksTag)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Feedbacks getFeedbackById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all per page and size.", tags = feedbacksTag)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Feedbacks> getAllFeedbacks(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "50") Integer size) {
        return service.getFeedbacks(page, size);
    }

    @Operation(summary = "Get image by id.", tags = feedbacksTag)
    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> getImageById(@PathVariable String fileName) throws IOException {
        return service.getImageById(fileName);
    }

    @Operation(summary = "Save new feedback.", tags = feedbacksTag)
    @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFeedback(@RequestPart("feedback") @Valid FeedbacksDTO feedbacksDTO,
            @RequestPart("file") MultipartFile file) throws IOException {
        service.saveFeedback(feedbacksDTO, file);
    }

    @Operation(summary = "Update by id.", tags = feedbacksTag)
    @PutMapping(value = "/update/{id}", consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFeedback(@PathVariable UUID id, @RequestPart("feedback") FeedbacksDTO feedbacksDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        service.updateFeedback(id, feedbacksDTO, file);
    }

    @Operation(summary = "Activate/Disable by id.", tags = feedbacksTag)
    @PutMapping("/active/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void activeById(@PathVariable UUID id, @RequestBody @Valid ActiveDTO activeDTO) {
        service.activeById(id, activeDTO);
    }

    @Operation(summary = "Reorder.", tags = feedbacksTag)
    @PutMapping("/reorder")
    @ResponseStatus(HttpStatus.OK)
    public void reorder(@RequestParam Long currentPosition, @RequestParam String action)
            throws ReorderPositionException, ReorderActionException {
        service.reorder(currentPosition, action);
    }

    @Operation(summary = "Delete by id.", tags = feedbacksTag)
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedbackById(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
