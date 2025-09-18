package entity;

// import javax.persistence.*;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // rende la classe una entity per aderire ad una tabella sul DB
@Table(name="studenti")
public class Studente {

    @Id // Rende la colonna una primary key per aderire ad una tabella sul db
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto incrementante
    private Long id;

    @Column(length = 50, nullable = false) // aggiunge i vincoli lunghezza max 50 caratteri e not nullable
    private String nome;

    @Column(length = 50, nullable = false)
    private String cognome;

    @Column(unique = true ) // aggiunge vincolo unicità di specifico valore nella tabella
    private String matricola;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( // many to many necessita di una tabella di join intermedia
            name = "iscizione", // Specifica il nome della tabella join
            joinColumns = @JoinColumn(name = "id_studente", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_corso", referencedColumnName = "id")
    )
    private Set<Corso> corsiFrequentati = new HashSet<>();


    @OneToMany(mappedBy = "studente", fetch = FetchType.EAGER) // nome della proprietà Studente in Esame
    private Set<Esame> esami = new HashSet<>();

    // Devo avere 3 costrutotri: uno vuoto

    public Studente(){}

    // Uno con tutti gli argomenti
    public Studente(Long id, String nome, String cognome, String matricola, Set<Corso> corsiFrequentati, Set<Esame> esami) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.corsiFrequentati = corsiFrequentati;
        this.esami = esami;
    }

    // Uno con un sottoinsieme
    public Studente(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
    }

    // Metodo Helper che aggiunge un corso ad uno studente
    public void addCorso(Corso corso){
        this.corsiFrequentati.add(corso);
        corso.getStudentiIscritti().add(this); // Collego il corso allo studente
        corso.getStudentiIscritti().add(this); // Collego lo studente al corso
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public Set<Corso> getCorsiFrequentati() {
        return corsiFrequentati;
    }

    public void setCorsiFrequentati(Set<Corso> corsiFrequentati) {
        this.corsiFrequentati = corsiFrequentati;
    }

    public Set<Esame> getEsami() {
        return esami;
    }

    public void setEsami(Set<Esame> esami) {
        this.esami = esami;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", matricola='" + matricola + '\'' +
                ", corsiFrequentati=" + corsiFrequentati +
                ", esami=" + esami +
                '}';
    }
}
