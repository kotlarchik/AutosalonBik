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
public class Bodytype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "bodytype")
    private Set<PTS> ptsSet;

    @Override
    public String toString() {
        return "Bodytype{" +
                "id=" + id +
                ", type='" + type;
    }
}
