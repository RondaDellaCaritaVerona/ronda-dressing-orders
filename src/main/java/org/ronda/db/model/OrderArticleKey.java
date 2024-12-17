package org.ronda.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderArticleKey {

  @Column(name = "order_id")
  private String orderId;

  @Column(name = "article_id")
  private String articleId;
}
