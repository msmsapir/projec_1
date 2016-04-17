package com.example.stas.project;

/**
 * Created by stas on 05/03/2016.
 */
public class Stop {

    private String rank;
    private String country;
    private String population;
    public String line;

    public Stop(String rank, String country, String population,String line) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.line=line;
    }

    public String getRank() { return this.rank;}

    public String getCountry() {
        return this.country;
    }

    public String getPopulation() {
        return this.population;
    }

    public String getLine() {return this.line;

    }
}



