package com.junghwan.springbootdeveloper.repository.search;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.QArticle;
import com.junghwan.springbootdeveloper.domain.QComment;
import com.junghwan.springbootdeveloper.dto.ArticleImageDTO;
import com.junghwan.springbootdeveloper.dto.ArticleListAllDTO;
import com.junghwan.springbootdeveloper.dto.ArticleListCommentCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ArticleSearchImpl extends QuerydslRepositorySupport implements ArticleSearch{
    public ArticleSearchImpl() {
        super(Article.class);
    }


    @Override
    public Page<Article> searchAll(String[] types, String keyword, Pageable pageable) {

//        Arrays.stream(types).toList();
        log.info(keyword);

        QArticle article = QArticle.article;
        JPQLQuery<Article> query = from(article);

        if ((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(article.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(article.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(article.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        query.where(article.id.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Article> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ArticleListCommentCountDTO> searchWithCommentCount(String[] types, String keyword, Pageable pageable) {

        QArticle article = QArticle.article;
        QComment comment = QComment.comment1;

        JPQLQuery<Article> query = from(article);
        query.leftJoin(comment).on(comment.article.eq(article));

        query.groupBy(article);

        if ((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(article.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(article.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(article.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        query.where(article.id.gt(0L));

        JPQLQuery<ArticleListCommentCountDTO> dtoQuery = query.select(Projections.bean(ArticleListCommentCountDTO.class,
                article.id,
                article.title,
                article.writer,
                article.category,
                article.createdAt,
                comment.count().as("commentCounting")
        ));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<ArticleListCommentCountDTO> list = dtoQuery.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ArticleListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {

        QArticle article = QArticle.article;
        QComment comment = QComment.comment1;

        JPQLQuery<Article> articleJPQLQuery = from(article);
        articleJPQLQuery.leftJoin(comment).on(comment.article.eq(article));

        if ((types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(article.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(article.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(article.writer.contains(keyword));
                        break;
                }
            }  // for end
            articleJPQLQuery.where(booleanBuilder);
        } // if end

        articleJPQLQuery.groupBy(article);

        getQuerydsl().applyPagination(pageable, articleJPQLQuery);  // paging


        JPQLQuery<Tuple> tupleJPQLQuery = articleJPQLQuery.select(article, comment.countDistinct());

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        List<ArticleListAllDTO> dtoList = tupleList.stream().map(tuple -> {

            Article article1 = (Article) tuple.get(article);
            long commentCount = tuple.get(1, Long.class);

            ArticleListAllDTO dto = ArticleListAllDTO.builder()
                    .id(article1.getId())
                    .title(article1.getTitle())
                    .writer(article1.getWriter())
                    .category(article1.getCategory())
                    .createdAt(article1.getCreatedAt())
                    .commentCounting(commentCount)
                    .build();

            // articleImage 를 ArticleImageDTO 처리할 부분
            List<ArticleImageDTO> imageDTOList = article1.getImageSet().stream().sorted()
                            .map(articleImage -> ArticleImageDTO.builder()
                                    .uuid(articleImage.getUuid())
                                    .fileName(articleImage.getFileName())
                                    .ord(articleImage.getOrd())
                                    .s3url(articleImage.getS3Url())
                                    .build()
                            ).collect(Collectors.toList());

            dto.setArticleImages(imageDTOList);

            return dto;

        }).collect(Collectors.toList());

        long totalCount = articleJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, totalCount);
    }


}
