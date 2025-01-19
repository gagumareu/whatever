package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Comment;
import com.junghwan.springbootdeveloper.dto.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QCommentRepository {

    Page<CommentResponse> listWithUser(long articleId, Pageable pageable);

    Page<Comment> list(Pageable pageable);

    List<Comment> listWithUser2();

}
