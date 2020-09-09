package ru.bersenev.miner.hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс сущности таблицы User
 */
@Entity
@Table (name = "users")
public class UsersTable {

    /**
     *
     */
    @Id
    @Column(name = "name")
    private String name;

    @Column ( name = "length")
    private int length;

    @Column (name = "kolBomb")
    private int kolBomb;

    @Column ( name = "percent")
    private int percent;

    @Column (name = "nomRadioButton")
    private int nomRadioButton;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultTable> resultTables = new ArrayList<ResultTable>();

    public UsersTable(){}

    /**
     * @param name имя пользователя
     * @param length
     * @param kolBomb
     * @param percent
     * @param nomRadioButton
     */
    public UsersTable(String name, int length, int kolBomb, int percent, int nomRadioButton){
        this.length=length;
        this.kolBomb=kolBomb;
        this.name=name;
        this.percent = percent;
        this.nomRadioButton = nomRadioButton;
        resultTables = new ArrayList();
    }

    public void addResult(ResultTable result){
        result.setUser(this);
        resultTables.add(result);
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getKolBomb() {
        return kolBomb;
    }

    public void setKolBomb(int kolBomb) {
        this.kolBomb = kolBomb;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getNomRadioButton() {
        return nomRadioButton;
    }

    public void setNomRadioButton(int nomRadioButton) {
        this.nomRadioButton = nomRadioButton;
    }

    public List<ResultTable> getResultTables() {
        return resultTables;
    }

    public void setResultTables(List<ResultTable> resultTables) {
        this.resultTables = resultTables;
    }
    public void deletResult(){
        resultTables.removeAll(resultTables);
    }

    @Override
    public String toString(){
        return "name : " + name +
                "\nSettings :\n"+
                "length : "+ length +
                "\nkolBomb : " + kolBomb +
                "\nkol record :" + resultTables.size();
    }
}
