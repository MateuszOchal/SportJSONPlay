package com.example.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competitor implements Comparable<Competitor> {

private int id;
private String name;
private String country;
private String nameAbbreviation;


    @Override
    public int compareTo(Competitor o) {
            return (this.name.compareTo(o.name));
    }

    @Override
    public String toString() {
        return "\n" + "Team id: "+ id +
                ", name: " + name  +
                "(" + country+")"+
                "(" + nameAbbreviation+")"
                ;
    }
}

