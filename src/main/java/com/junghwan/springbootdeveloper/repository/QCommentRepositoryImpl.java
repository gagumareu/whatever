package com.junghwan.springbootdeveloper.repository;
import com.junghwan.springbootdeveloper.domain.*;
import com.junghwan.springbootdeveloper.dto.CommentResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class QCommentRepositoryImpl extends QuerydslRepositorySupport implements QCommentRepository {
    public QCommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public Page<CommentResponse> listWithUser(long articleId, Pageable pageable) {

        log.info("==========================================");
       log.info(articleId);
       log.info(pageable);

        QComment comment = QComment.comment1;
        QUser user = QUser.user;

        JPQLQuery<Comment> commentJPQLQuery = from(comment);
        commentJPQLQuery.leftJoin(user).on(user.userId.eq(comment.author));

        getQuerydsl().applyPagination(pageable, commentJPQLQuery);

        JPQLQuery<Tuple> tupleJPQLQuery = commentJPQLQuery.select(comment, user);

        log.info("----------------- 1--------------------");

//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        BooleanExpression booleanExpression = comment.article.eq(Article.builder().id(articleId).build());
//        booleanBuilder.and(booleanExpression);

//        tupleJPQLQuery.where(comment.article.id.eq(articleId));

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        log.info("----------------- 2--------------------");

        List<CommentResponse> dtoList = tupleList.stream().map(tuple -> {


            log.info("----------------- 3--------------------");

            Comment comment1 = (Comment) tuple.get(comment);
            User user1 = (User) tuple.get(user);

            return CommentResponse.builder()
                    .id(comment1.getId())
                    .comment(comment1.getComment())
                    .author(comment1.getAuthor())
                    .articleId(articleId)
                    .createdAt(comment1.getCreatedAt())
                    .socialImg(user1.getSocialImg())
                    .profileImg(user1.getProfileImg())
                    .nickName(user1.getNickName())
                    .build();

        }).collect(Collectors.toList());

        log.info("----------------- 4 --------------------");

        long totalCount = commentJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    @Override
    public Page<Comment> list(Pageable pageable) {

        QComment qComment = QComment.comment1;

        JPQLQuery<Comment> commentJPQLQuery = from(qComment);

        log.info("----------------- 1--------------------");

        getQuerydsl().applyPagination(pageable, commentJPQLQuery);

        log.info("----------------- 2--------------------");

        JPQLQuery<Comment> tupleJPQLQuery = commentJPQLQuery.select(qComment);

        log.info("----------------- 3--------------------");

        List<Comment> commentList = tupleJPQLQuery.fetch();

        log.info("----------------- 4--------------------");

        log.info(commentList);

        List<CommentResponse> dtoList = commentList.stream().map(comment -> {

            CommentResponse dto = CommentResponse.builder()
                    .comment(comment.getComment())
                    .id(comment.getId())
                    .author(comment.getAuthor())
                    .createdAt(comment.getCreatedAt())
                    .articleId(comment.getArticle().getId())
                    .build();

            return dto;

        }).collect(Collectors.toList());

        long totalCount = commentJPQLQuery.fetchCount();

        log.info("----------------- 5--------------------");

        return new PageImpl<>(commentList, pageable, totalCount);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> listWithUser2() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QComment comment = QComment.comment1;
        QUser user = QUser.user;

        List<Comment> list = queryFactory.select(comment)
                .from(comment, user)
                .leftJoin(user).on(user.userId.eq(comment.author))
                .where(comment.article.id.eq(271L))
                .fetch();

        return list;
    }


}
