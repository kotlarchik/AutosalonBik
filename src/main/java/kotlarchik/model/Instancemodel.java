package kotlarchik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dom4j.rule.Mode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Instancemodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "color")
    private String color;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "image")
    private String image;

    @OneToOne
    @JoinColumn(name = "pts_id")
    private PTS pts;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "instancemodel", fetch = FetchType.EAGER)
    private Set<Contract> contractSet;

    @Override
    public String toString() {
        return "Instancemodel{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cost=" + cost +
                ", image='" + image + '\'' +
                ", pts=" + pts;
    }
}
