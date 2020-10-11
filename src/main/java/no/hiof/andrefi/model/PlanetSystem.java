package no.hiof.andrefi.model;

import java.util.ArrayList;

public class PlanetSystem implements Comparable<Planet> {
    private String name, pictureUrl;
    private Star centerStar;
    private ArrayList<Planet> planets;

    public PlanetSystem(){}

    public PlanetSystem(String name, Star centerStar, ArrayList<Planet> planets, String pictureUrl) {
        this.name = name;
        this.centerStar = centerStar;
        this.planets = planets;
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Planet largestPlanet() {

        Planet largestPlanet = planets.get(0);
        double max = planets.get(0).getRadius();


        for (int i = 1; i < planets.size(); i++) {
            if (max < planets.get(i).getRadius()) {
                max = planets.get(i).getRadius();
                largestPlanet = planets.get(i);
            } else if (max == planets.get(i).getRadius()) {
                if (largestPlanet.getMass() < planets.get(i).getMass()){
                    largestPlanet = planets.get(i);
                }
            }
        }


        return largestPlanet;
    }

    public Planet smallestPlanet() {

        Planet smallestPlanet = planets.get(0);
        double min = planets.get(0).getRadius();

        for (int i = 0; i < planets.size(); i++) {
            if (min > planets.get(i).getRadius()){
                min = planets.get(i).getRadius();
                smallestPlanet = planets.get(i);
            } else if (min == planets.get(i).getRadius()){
                if (smallestPlanet.getMass() > planets.get(i).getMass()){
                    smallestPlanet = planets.get(i);
                }
            }
        }
        return smallestPlanet;
    }

    public Planet requestedPlanet(String searchedName) {
        Planet foundPlanet = null;
        for (int i = 0; i < planets.size(); i++) {
            if (searchedName.equals(planets.get(i).getName().toLowerCase())){
                foundPlanet = planets.get(i);
            }
        }
        return foundPlanet;
    }

    @Override
    public String toString() {
        String names = "";

        for (int i = 0; i < planets.size(); i++) {
            names += planets.get(i).getName() + ", ";
        }

        return "Planetsystemet " + getName() + " har stjernen " + getCenterStar().getName() + " og planetene " + names;
    }

    @Override
    public int compareTo(Planet sammenligningsPlanet) {
        return name.compareTo(sammenligningsPlanet.getName());
    }
}


