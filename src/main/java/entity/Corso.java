package entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "corsi") // creando noi le tabelle, specifichiamo un nome più simile in uso dul DB
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) //aggiunge vincolo unicità e non nullable
    private String codice;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "crediti_cfu") // stesso discorso della tabella vale per le colonne
    private Integer creditiCFU;

    @ManyToMany
    @JoinTable( // many to many necessita di una tabella di join intermedia
            name = "iscizione", // Specifica il nome della tabella join
            joinColumns = @JoinColumn(name = "id_corso", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_studente", referencedColumnName = "id")
    )
    private Set<Studente> studentiIscritti = new HashSet<>();

    @OneToMany(mappedBy = "corso")
    private Set<Esame> esami = new HashSet<>();

    public Corso(){}


    // Costruttore con codice, nome e crediti
    public Corso(String codice, String nome, Integer creditiCFU) {
        this.codice = codice;
        this.nome = nome;
        this.creditiCFU = creditiCFU;
        this.studentiIscritti = studentiIscritti;
        this.esami = esami;
    }

    public Long getId() {
        return id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCreditiCFU() {
        return creditiCFU;
    }

    public void setCreditiCFU(Integer creditiCFU) {
        this.creditiCFU = creditiCFU;
    }

    public Set<Studente> getStudentiIscritti() {
        return studentiIscritti;
    }

    public void setStudentiIscritti(Set<Studente> studentiIscritti) {
        this.studentiIscritti = studentiIscritti;
    }

    public Set<Esame> getEsami() {
        return esami;
    }

    public void setEsami(Set<Esame> esami) {
        this.esami = esami;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", creditiCFU=" + creditiCFU +
                ", studentiIscritti=" + studentiIscritti +
                ", esami=" + esami +
                '}';
    }
}
