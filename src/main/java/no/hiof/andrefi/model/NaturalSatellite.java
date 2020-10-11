package no.hiof.andrefi.model;

public abstract class NaturalSatellite extends CelestialBody{
    private double semiMajorAxis, eccentricity, orbitalPeriod;
    private CelestialBody centralCelestialBody;
    public static final double AU = 149597871;
    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    public NaturalSatellite(){}

    public NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody)  {
        super(name, mass, radius);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = centralCelestialBody;
    }

    public NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl)  {
        super(name, mass, radius, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
    }

    public NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody, String pictureUrl)  {
        super(name, mass, radius, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = centralCelestialBody;
    }

    public int distanceToCentralBody(double degrees) {
        return (int)(((getSemiMajorAxis()*(1-Math.pow(getEccentricity(), 2)))/(1+getEccentricity()*Math.cos(Math.toRadians(degrees)))) * AU);
    }

    public double orbitingVelocity(double distance){
        double G = GRAVITATIONAL_CONSTANT;
        double M = centralCelestialBody.getMass();

        return (Math.sqrt((G * M) / (distance * 1000))) / 1000;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }

    @Override
    public String toString(){
        return "Den naturlige sattelien " + getName() + " og en masse på " + getMass() + "kg og en radius på " + getRadius() + "km, den har en orbital periode på " + getOrbitalPeriod() + " dager rundt " + getCentralCelestialBody().getName();
    }
}
