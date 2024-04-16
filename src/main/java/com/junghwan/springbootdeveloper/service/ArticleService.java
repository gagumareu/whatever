package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.dto.*;
import com.junghwan.springbootdeveloper.repository.ArticleRepository;
import com.junghwan.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    private ArticleViewResponse entityToDTO(Article article){

        ArticleViewResponse response = ArticleViewResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .writer(article.getWriter())
                .category(article.getCategory())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();

        List<String> fileNames = article.getImageSet().stream().sorted().map(articleImage ->
                articleImage.getUuid() + "_" + articleImage.getFileName()).collect(Collectors.toList());;

        response.setFileNames(fileNames);

        return response;
    }

    public Article save(AddArticleRequest request){
        log.info(request);
        return articleRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public ArticleViewResponse findById(long id){

        Article article = articleRepository.findByIdWithImages(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        return entityToDTO(article);
    }

    @Transactional
    public void delete(long id){

        commentRepository.deleteByArticle_id(id);

        articleRepository.deleteById(id);
    }

    @Transactional
    public Article update(UpdateArticleRequest request, long id){

        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent(), request.getWriter(), request.getCategory());

        article.clearImages();

        if (request.getFileNames() != null){
            for (String fileName : request.getFileNames()){
                String uuid = fileName.substring(55, 91);
                String name = fileName.substring(92);
                article.addImage(uuid, name, fileName);
            }
        }

        return articleRepository.save(article);
    }

    public PageResponseDTO<ArticleViewResponse> list(PageRequestDTO pageRequestDTO){

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("id");

        Page<Article> result = articleRepository.searchAll(types, keyword, pageable);

        List<ArticleViewResponse> dtoList = result.getContent().stream()
                .map(ArticleViewResponse::new).toList();

        return PageResponseDTO.<ArticleViewResponse>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    public PageResponseDTO<ArticleListCommentCountDTO> listWithCommentCount(PageRequestDTO pageResponseDTO){

        String[] types = pageResponseDTO.getTypes();
        String keyword = pageResponseDTO.getKeyword();
        Pageable pageable = pageResponseDTO.getPageable("id");

        Page<ArticleListCommentCountDTO> result = articleRepository.searchWithCommentCount(types, keyword, pageable);

        return PageResponseDTO.<ArticleListCommentCountDTO>withAll()
                .pageRequestDTO(pageResponseDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }

    public PageResponseDTO<ArticleListAllDTO> listWithAll(PageRequestDTO pageRequestDTO){

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("id");

        Page<ArticleListAllDTO> result = articleRepository.searchWithAll(types, keyword, pageable);

        return PageResponseDTO.<ArticleListAllDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }



}
