package entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "esami")
public class Esame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer voto;

    @Column(name = "data_esame")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_corso")
    private Corso corso;


    @ManyToOne
    @JoinColumn(name="id_studente")
    private Studente studente;


    public Esame() {}

    public Esame(Integer voto, LocalDate date, Corso corso, Studente studente) {
        this.voto = voto;
        this.date = date;
        this.corso = corso;
        this.studente = studente;
    }

    public Long getId() {
        return id;
    }

    public Integer getVoto() {
        return voto;
    }

    public LocalDate getDate() {
        return date;
    }

    public Corso getCorso() {
        return corso;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    @Override
    public String toString() {
        return "Esame{" +
                "id=" + id +
                ", voto=" + voto +
                ", date=" + date +
                ", corso=" + corso +
                ", studente=" + studente +
                '}';
    }
}
