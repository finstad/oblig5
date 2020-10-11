package no.hiof.andrefi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Star extends CelestialBody{
    private double effectiveTemperature;
    public static final double SUN_MASS = 1.98892E30;
    public static final double SUN_RADIUS = 695700;

    public Star(){}

    public Star(String name, double radius, double mass, double effectiveTemperature) {
        super(name, mass, radius);
        this.effectiveTemperature = effectiveTemperature;
    }

    public Star(String name, double radius, double mass, double effectiveTemperature, String pictureURL) {
            super(name, mass, radius, pictureURL);
            this.effectiveTemperature = effectiveTemperature;
    }

    public double getEffectiveTemperature() {
        return effectiveTemperature;
    }

    public void setEffectiveTemperature(double effectiveTemperature) {
        this.effectiveTemperature = effectiveTemperature;
    }

    @JsonIgnore
    public double mSun(){
        return getMass() / SUN_MASS;
    }

    @JsonIgnore
    public double rSun(){
        return getRadius() / SUN_RADIUS;
    }

    @Override
    public String toString() {
        return "Stjernen " + getName() + " har en radius " + getRadius() + " km, og en masse på " + getMass() + " kg og en temperatur på " +
                getEffectiveTemperature() + " grader celcius";
    }
}
