package org.ronda.db.repository;

import java.util.Optional;
import org.ronda.db.model.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
  Optional<OrderState> findByDescription(String description);
}
