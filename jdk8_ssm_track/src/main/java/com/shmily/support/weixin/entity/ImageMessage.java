package com.shmily.support.weixin.entity;

import java.util.List;

/**
 * Created by wuxubiao on 2017/5/10.
 */
public class ImageMessage extends BaseMessage {

    private int ArticleCount;

    private List<Image> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Image> getArticles() {
        return Articles;
    }

    public void setArticles(List<Image> articles) {
        Articles = articles;
    }
}
