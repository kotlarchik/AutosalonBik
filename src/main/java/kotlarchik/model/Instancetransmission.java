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

    @Column(name = "numberGears")
    private int numberGears;

    @OneToMany(mappedBy = "instancetransmission")
    private Set<Options> optionsSet;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @Override
    public String toString() {
        return String.valueOf(numberGears);
    }
}
