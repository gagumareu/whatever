package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.Comment;
import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.dto.*;
import com.junghwan.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentService {

    private final CommentRepository commentRepository;

    public Long save(AddCommentRequest request){

        log.info(request);

        Comment comment = commentRepository.save(request.toEntity());

        return comment.getId();
    }

    public Comment findById(Long id){
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
    @Transactional
    public Comment update(UpdateCommentRequest request){

        Comment comment = commentRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + request.getId()));

        comment.update(request.getComment());

        return comment;
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

    public PageResponseDTO<CommentResponse> getListOfArticle(Long articleId, PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(), Sort.by("id").ascending());

        Page<Object[]> result = commentRepository.listOfArticle2(articleId, pageable);


        List<CommentResponse> responses = result.stream().map(objects -> new CommentResponse(
                (Comment) objects[0],
                (User) objects[1]
        )).collect(Collectors.toList());


        return PageResponseDTO.<CommentResponse>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(responses)
                .total((int)result.getTotalElements())
                .build();

    }


}
