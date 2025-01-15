package org.ronda.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id private String orderId;

  private Date orderDate;

  @ManyToOne
  @JoinColumn(name = "card_number")
  private Guest guest;

  @ManyToOne
  @JoinColumn(name = "state_id", nullable = false)
  private OrderState state;

  @Override
  public final boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    Class<?> oEffectiveClass =
        other instanceof HibernateProxy
            ? ((HibernateProxy) other).getHibernateLazyInitializer().getPersistentClass()
            : other.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Order order = (Order) other;
    return getOrderId() != null && Objects.equals(getOrderId(), order.getOrderId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
