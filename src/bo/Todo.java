package bo;

import java.time.LocalDate;

public class Todo {
    private int id;
    private LocalDate date;
    private String texte;
    //////////////////////////Getters Setters/////////////////////////////
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getTexte() {
        return texte;
    }
    public void setTexte(String texte) {
        this.texte = texte;
    }
    //////////////////////////Constructeurs/////////////////////////////
    public Todo(int id, LocalDate date, String texte) {
        this.id = id;
        this.date = date;
        this.texte = texte;
    }
    public Todo(LocalDate date, String texte) {
        this.date = date;
        this.texte = texte;
    }
    public Todo(String texte) {
        this.texte = texte;
    }
    //////////////////////////toString/////////////////////////////
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", date=" + date +
                ", texte='" + texte + '\'' +
                '}';
    }
}
