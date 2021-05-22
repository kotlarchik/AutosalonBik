package kotlarchik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "seria")
    private String seria;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "instancemodel_id")
    private Instancemodel instancemodel;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", seria='" + seria + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date;
    }
}
