package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.dto.*;
import com.junghwan.springbootdeveloper.service.ArticleService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ArticleViewResponse> resultDTO = articleService.list(pageRequestDTO);

        log.info(resultDTO);
    }

    @Test
    public void addArticleWithImages(){

        log.info(articleService.getClass().getName());

        AddArticleRequest request = AddArticleRequest.builder()
                .title("add article with images .. test2")
                .content("test content with adding images2")
                .category("이런저런")
                .writer("정환2")
                .build();

        request.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_ccc.jpg"
                )
        );

        Article article = articleService.save(request);

        log.info("id: " + article.getId());
    }

    @Test
    public void testReadALl(){

        long id = 129L;

        ArticleViewResponse article = articleService.findById(id);

        log.info(article);

        for (String fileName : article.getFileNames()){
            log.info(fileName);
        }

    }

    @Test
    public void testUpdate(){
        UpdateArticleRequest response = UpdateArticleRequest.builder()
                .id(129L)
                .title("update title,,")
                .content("updated content..")
                .writer("정환2")
                .category("버그")
                .build();

        response.setFileNames(Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));

        articleService.update(response, response.getId());
    }

    @Test
    public void testRemoveAll(){

        long id = 130L;

        articleService.delete(id);
    }

    @Transactional
    @Test
    public void testListWithAll(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(20)
                .build();

        PageResponseDTO<ArticleListAllDTO> responseDTO = articleService.listWithAll(pageRequestDTO);

        List<ArticleListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(articleListAllDTO -> {

            log.info(articleListAllDTO.getId() + ":" + articleListAllDTO.getTitle());

            if (articleListAllDTO.getArticleImages() != null){
                for (ArticleImageDTO articleImageDTO : articleListAllDTO.getArticleImages()){
                    log.info(articleImageDTO);
                }
            }
            log.info("-----------------------------------------------------------------");
        });

    }

}
