package org.ronda.db.repository;

import org.ronda.db.model.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
    Optional<OrderState> findByDescription(String description);
}
