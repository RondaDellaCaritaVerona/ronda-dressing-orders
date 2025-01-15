package org.ronda.db.repository;

import org.ronda.db.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {}
