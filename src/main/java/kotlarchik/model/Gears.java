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
public class Gears {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "gears", fetch = FetchType.EAGER)
    private Set<Instancetransmission> instancetransmissionSet;

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
