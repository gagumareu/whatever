package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.ArticleImage;
import com.junghwan.springbootdeveloper.dto.ArticleImageDTO;
import com.junghwan.springbootdeveloper.repository.ArticleImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    public List<ArticleImage> findByArticleId(long id){

        return articleImageRepository.findByArticleId(id);
    }

}
