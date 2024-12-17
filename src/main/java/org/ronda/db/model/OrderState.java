package org.ronda.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderState {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderStateId;

  @Column(nullable = false)
  private String description;
}
