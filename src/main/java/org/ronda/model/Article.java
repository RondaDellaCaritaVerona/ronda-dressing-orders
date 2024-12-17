import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "description")
    private String description;

    public String getArticleId() { return articleId; }
    public void setArticleId(String articleId) { this.articleId = articleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
