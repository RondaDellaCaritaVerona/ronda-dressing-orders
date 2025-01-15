package org.ronda.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

  @Id private String cardNumber;

  @Column(nullable = false)
  private Date addDate;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  private Date birthDate;

  private Integer height;

  @OneToMany(mappedBy = "guest")
  @Exclude
  private List<Order> orders = new ArrayList<>();

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
    Guest guest = (Guest) other;
    return getCardNumber() != null && Objects.equals(getCardNumber(), guest.getCardNumber());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
