import javax.persistence.*;

@Entity
@Table(name = "order_article")
public class OrderArticle {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
