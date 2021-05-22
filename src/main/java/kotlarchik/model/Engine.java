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
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "engine", fetch = FetchType.EAGER)
    private Set<PTS> ptsSet;

    @OneToMany(mappedBy = "engine", fetch = FetchType.EAGER)
    private Set<Instanceengine> instanceengineSet;

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", type='" + type;
    }
}
