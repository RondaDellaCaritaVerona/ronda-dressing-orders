package org.ronda.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderArticleKey {

  private String orderId;
  private String articleId;
}
