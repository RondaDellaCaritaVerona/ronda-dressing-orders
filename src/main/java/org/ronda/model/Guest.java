import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @Column(name = "guest_id")
    private String guestId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "add_date", nullable = false)
    private Date addDate;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "height")
    private Integer height;

    public String getGuestId() { return guestId; }
    public void setGuestId(String guestId) { this.guestId = guestId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Date getAddDate() { return addDate; }
    public void setAddDate(Date addDate) { this.addDate = addDate; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }
}
