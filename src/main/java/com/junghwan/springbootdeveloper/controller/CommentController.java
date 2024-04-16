package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.domain.Comment;
import com.junghwan.springbootdeveloper.dto.*;
import com.junghwan.springbootdeveloper.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/api/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> save(@Valid @RequestBody AddCommentRequest request,
                                                  BindingResult bindingResult) throws BindException {

        log.info(request);

        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();

        Long id = commentService.save(request);

        resultMap.put("id", id);

        return resultMap;
    }

    @GetMapping(value = "/api/comment/list/{articleId}")
    public PageResponseDTO<CommentResponse> getList(@PathVariable("articleId") Long articleId, PageRequestDTO pageRequestDTO){

        log.info(articleId);
        log.info(pageRequestDTO);

        return commentService.getListOfArticle(articleId, pageRequestDTO);
    }

    @GetMapping(value = "/api/comment/{id}")
    public CommentResponse getComment(@PathVariable("id") Long id){

        Comment comment = commentService.findById(id);

        return new CommentResponse(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public Map<String, Long> remove(@PathVariable Long id){

        commentService.delete(id);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("id", id);

        return resultMap;

    }

    @PutMapping(value = "/api/comment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> remove(@PathVariable Long id, @RequestBody UpdateCommentRequest request){

        request.setId(id);

        commentService.update(request);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("id", id);

        return resultMap;
    }



}
