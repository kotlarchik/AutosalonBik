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
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER)
    private Set<Instancemodel> instancemodelSet;

    @ManyToOne
    @JoinColumn(name = "marka_id")
    private Marka marka;

    @Override
    public String toString() {
        return name;
    }
}
