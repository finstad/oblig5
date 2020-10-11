package no.hiof.andrefi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Planet extends NaturalSatellite {
    public static final double JUPITER_MASS = 1.898E27;
    public static final double JUPITER_RADIUS = 71492;
    public static final double EARTH_MASS = 5.972E24;
    public static final double EARTH_RADIUS = 6371;

    public Planet() {
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl);
    }

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody, String pictureUrl) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody, pictureUrl);
    }

    @JsonIgnore
    public double mJup() {
        return getMass() / JUPITER_MASS;
    }

    @JsonIgnore
    public double rJup() {
        return getRadius() / JUPITER_RADIUS;
    }

    @JsonIgnore
    public double mEarth() {
        return getMass() / EARTH_MASS;
    }

    @JsonIgnore
    public double rEarth() {
        return getRadius() / EARTH_RADIUS;
    }

    @JsonIgnore
    public double surfaceGravity() {
        return (6.67408E-11 * getMass()) / Math.pow(getRadius() * 1000, 2);
    }


    @Override
    public String toString() {
        return "Planeten " + getName() + " har en radius på " + getRadius() + " km og en masse på " + getMass() + " kg";
    }
}

