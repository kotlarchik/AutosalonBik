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
public class Instanceengine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "power")
    private String power;

    @Column(name = "volume")
    private String volume;

    @Column(name = "km")
    private String km;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToMany(mappedBy = "instanceengine", fetch = FetchType.EAGER)
    private Set<Options> optionsSet;

    @Override
    public String toString() {
        return "Instanceengine{" +
                "id=" + id +
                ", power='" + power + '\'' +
                ", volume='" + volume + '\'' +
                ", km='" + km;
    }
}
