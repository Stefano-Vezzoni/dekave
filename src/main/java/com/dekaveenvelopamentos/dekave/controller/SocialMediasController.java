package com.dekaveenvelopamentos.dekave.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dekaveenvelopamentos.dekave.domain.entity.SocialMedias;
import com.dekaveenvelopamentos.dekave.dto.ActiveDTO;
import com.dekaveenvelopamentos.dekave.dto.SocialMediaDTO;
import com.dekaveenvelopamentos.dekave.service.SocialMediaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/socialmedias")
public class SocialMediasController {

    final String socialMediaTag = "Social Medias";

    @Autowired
    private SocialMediaService service;

    @Operation(summary = "Get by id.", tags = socialMediaTag)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SocialMedias getSocialMediaById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all per page and size.", tags = socialMediaTag)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SocialMedias> getAllSocialMedias(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "50") Integer size) {
        return service.getSocialMedias(page, size);
    }

    @Operation(summary = "Save new social media.", tags = socialMediaTag)
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSocialMedia(@RequestBody @Valid SocialMediaDTO socialMediaDTO) {
        service.saveSocialMedia(socialMediaDTO);
    }

    @Operation(summary = "Update by id.", tags = socialMediaTag)
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSocialMedia(@PathVariable UUID id, @RequestBody SocialMediaDTO socialMediaDTO) {
        service.updateSocialMedia(id, socialMediaDTO);
    }

    @Operation(summary = "Activate/Disable by id.", tags = socialMediaTag)
    @PutMapping("/active/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void activeById(@PathVariable UUID id, @RequestBody @Valid ActiveDTO activeDTO) {
        service.activeById(id, activeDTO);
    }

    @Operation(summary = "Delete by id.", tags = socialMediaTag)
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSocialMediaById(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
