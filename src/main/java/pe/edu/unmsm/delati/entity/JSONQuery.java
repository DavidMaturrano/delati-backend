/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

/**
 *
 * @author Lenovo Legion Y520
 */
public class JSONQuery {
    private String type;
    private String distance;
    private String query;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    
    @Override
    public String toString() {
        return "TIPO: "+getType()+"\nDistancia: "+getDistance()+"\nQuery: "+getQuery();
    }
}
