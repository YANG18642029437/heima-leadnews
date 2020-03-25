package com.heima.article.test;

import com.heima.article.ArticleJarApplication;
import com.heima.article.service.ArticleHomeService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ArticleJarApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Autowired
    private ArticleHomeService articleHomeService;

    @Test
    public void testArticle(){
//        ApUser apUser = new ApUser();
//        apUser.setId(2104l);
//        AppThreadLocalUtils.setUser(apUser);
        ResponseResult result = articleHomeService.load(null, ArticleConstans.LOADTYPE_LOAD_MORE);
        System.out.println(result.getData());
    }

//    Map<Character, Map<Character,String>> map = new HashMap<>();
//
//    public void addword(String word){
//        char c1 = word.charAt(0);
//        char c2 = word.charAt(1);
//
//
//    }
//
//    public boolean isMath(String input){
//
//    }
}
