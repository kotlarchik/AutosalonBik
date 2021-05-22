package kotlarchik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class PTS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "VIN")
    private String VIN;

    @Column(name = "seria")
    private int seria;

    @Column(name = "number")
    private int number;

    @Column(name = "maxWeight")
    private String maxWeight;

    @Column(name = "weightNorma")
    private String weightNorma;

    @OneToOne(mappedBy = "pts", fetch = FetchType.LAZY)
    private Instancemodel instancemodel;

    @ManyToOne
    @JoinColumn(name = "bodytype_id")
    private Bodytype bodytype;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Override
    public String toString() {
        return "PTS{" +
                "id=" + id +
                ", VIN='" + VIN + '\'' +
                ", seria=" + seria +
                ", number=" + number +
                ", maxWeight='" + maxWeight + '\'' +
                ", weightNorma='" + weightNorma;
    }
}
