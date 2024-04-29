package com.junghwan.springbootdeveloper.controller;

import com.junghwan.springbootdeveloper.dto.*;

import com.junghwan.springbootdeveloper.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ArticleViewController {

    private final ArticleService articleService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/newArticle")
    public String newArticle(@RequestParam(required = false) Long id, Model model){

        log.info("id: " + id);

        if (id == null){

            model.addAttribute("article", new ArticleViewResponse());

        }else {

            ArticleViewResponse article = articleService.findById(id);
            model.addAttribute("article", article);

        }

        return "article/newArticle";


    }

//    @GetMapping("/articles")
//    public String articles(Model model){
//
//        List<ArticleListViewResponse> articles = articleService.findAll()
//                .stream()
//                .map(ArticleListViewResponse::new).toList();
//
//        model.addAttribute("articles", articles);
//
//        return "article/articles";
//
//    }

//    @PreAuthorize("permitAll()")
    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable long id, PageRequestDTO pageRequestDTO){

        ArticleViewResponse article = articleService.findById(id);

        model.addAttribute("article", article);

        return "article/article";
    }

    @GetMapping({"/articles", "/"})
    public String articleList(PageRequestDTO pageRequestDTO, Model model){

//        PageResponseDTO<ArticleViewResponse> responseDTO = articleService.list(pageRequestDTO);

        PageResponseDTO<ArticleListAllDTO> responseDTO = articleService.listWithAll(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

        return "article/articles";

    }

    @PostMapping("/newArticle")
    public String newArticle(AddArticleRequest request){

        log.info(request);
        articleService.save(request);

        return "redirect:/articles";
    }





}
