import javax.persistence.*;

@Entity
@Table(name = "order_state")
public class OrderState {

    @Id
    @Column(name = "order_state_id")
    private String orderStateId;

    @Column(name = "description", nullable = false)
    private String description;

    public String getOrderStateId() { return orderStateId; }
    public void setOrderStateId(String orderStateId) { this.orderStateId = orderStateId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
