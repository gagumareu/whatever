package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.domain.ArticleImage;
import com.junghwan.springbootdeveloper.dto.ArticleListAllDTO;
import com.junghwan.springbootdeveloper.dto.ArticleListCommentCountDTO;
import com.junghwan.springbootdeveloper.repository.ArticleRepository;
import com.junghwan.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

//    @Test
//    public void testInsertWithImages(){
//
//        Article article = Article.builder()
//                .title("image test")
//                .content("첨부파일 테스트")
//                .writer("tester")
//                .category("이런저런")
//                .build();
//
//        for (int i = 0; i < 3; i++){
//            article.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg", null);
//        }
//        articleRepository.save(article);
//    }
//
//    @Test
//    public void testReadWithImages(){
//
//        Optional<Article> result = articleRepository.findByIdWithImages(16L);
//
//        Article article = result.orElseThrow();
//
//        log.info(article);
//        log.info("---------------");
//        for (ArticleImage articleImage : article.getImageSet()) {
//            log.info(articleImage);
//        }
//    }
//
//    @Test
//    public void testSearchAll(){
//
//        String[] types = {"t", "c", "w"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
//
//        Page<Article> result = articleRepository.searchAll(types, keyword, pageable);
//    }
//
//    @Test
//    public void testSearchAll2(){
//
//        String[] types = {"t", "c", "w"};
//
//        String keyword = "뉴진스";
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
//
//        Page<ArticleListCommentCountDTO> result = articleRepository.searchWithCommentCount(types, keyword, pageable);
//
//        // total page
//        log.info(result.getTotalPages());
//
//        // page size
//        log.info(result.getSize());
//
//        // pageNumber
//        log.info(result.getNumber());
//
//        // prev newt
//        log.info(result.hasPrevious() + ": " + result.hasNext());
//
//        for (ArticleListCommentCountDTO article : result.getContent()) {
//            log.info(article);
//        }
//    }
//    @Transactional
//    @Commit
//    @Test
//    public void tesModifyImages(){
//
//        Optional<Article> result = articleRepository.findByIdWithImages(28L);
//
//        Article article = result.orElseThrow();
//
//        // 기존 첨부파일 삭제
//        article.clearImages();
//
//        // 새로운 파일 첨부
//        for (int i = 0; i < 2; i++){
//            article.addImage(UUID.randomUUID().toString(), "updateFile" + i + ".jpg", null);
//        }
//
//        articleRepository.save(article);
//    }
//
//    @Test
//    @Transactional
//    @Commit
//    public void testRemoveAll(){
//
//        Long id = 27L;
//
//        commentRepository.deleteByArticle_id(id);
//
//        articleRepository.deleteById(id);
//    }
//
//    @Test
//    public void testInsertAll(){
//
//        for (int i = 0; i < 100; i++){
//
//            Article article = Article.builder()
//                    .title("제목 .. " + i)
//                    .content("내용..." + i)
//                    .writer("작성자 .. " + i)
//                    .category("이런저런")
//                    .build();
//
//            for (int j = 0; j < 3; j++){
//
//                if ((i & 5) == 0 ){
//                    continue;
//                }
//                article.addImage(UUID.randomUUID().toString(), i + "file" + "j" + ".jpg", null);
//            }
//            articleRepository.save(article);
//        }
//    }
//
//    @Transactional
//    @Test
//    public void testSearchImageCommentCount(){
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
//
////        articleRepository.searchWithAll(null, null, pageable);
//
//       Page<ArticleListAllDTO> result = articleRepository.searchWithAll(null, null, pageable);
//
//       log.info("----------------------------");
//       log.info(result.getTotalElements());
//
//       result.getContent().forEach(articleListAllDTO -> log.info(articleListAllDTO));
//
//    }



}
