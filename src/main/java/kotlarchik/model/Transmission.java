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
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "transmission", fetch = FetchType.EAGER)
    private Set<Instancetransmission> instancetransmissionSet;

    @Override
    public String toString() {
        return type;
    }
}
