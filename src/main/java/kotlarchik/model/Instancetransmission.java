package kotlarchik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Instancetransmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "instancetransmission")
    private Set<Options> optionsSet;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "gears_id")
    private Gears gears;

    @Override
    public String toString() {
        return "Instancetransmission{" +
                "id=" + id;
    }
}
