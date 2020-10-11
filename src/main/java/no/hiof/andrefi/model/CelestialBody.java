package no.hiof.andrefi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Comparator;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star")
})

public abstract class CelestialBody implements Comparable<CelestialBody> {
    private String name, pictureUrl;
    private double radius, mass;

    public CelestialBody(){}

    public CelestialBody(String name, double mass, double radius) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public CelestialBody(String name, double mass, double radius, String pictureUrl) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public static Comparator<Planet> comparedByRadius = new Comparator<Planet>() {
        @Override
        public int compare(Planet o1, Planet o2) {
            return (int)(o1.getRadius() - o2.getRadius());
        }
    };

    public static Comparator<Planet> comparedByNumber = new Comparator<Planet>() {
        @Override
        public int compare(Planet o1, Planet o2) {
            return (int)(o1.getOrbitalPeriod() - o2.getOrbitalPeriod());
        }
    };

    @Override
    public String toString() {
        return "Celestial body'en " + getName() + "har en masse på " + getMass() + "kg og en radius på " + getRadius() + "km";
    }

    @Override
    public int compareTo(CelestialBody sammenligningsLegemet) {
        return (int)(this.getMass() - sammenligningsLegemet.getMass());
    }
}
