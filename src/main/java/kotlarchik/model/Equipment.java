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
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private Set<Options> optionsSet;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private Set<Instancemodel> instancemodelSet;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name;
    }
}
