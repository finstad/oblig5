package no.hiof.andrefi.repository;

import no.hiof.andrefi.model.Planet;
import no.hiof.andrefi.model.PlanetSystem;

import java.util.ArrayList;

public interface IUniverseRepository {
    ArrayList<PlanetSystem> getAllePlanetSystemer();
    PlanetSystem getPlanetSystem(String name);
    Planet getPlanet(String planetSystemName, String name);
    ArrayList<Planet> getAllePlaneter(String name);
    void deletePlanet(String planetSystemName, String planetName, String filnavn);
    void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystemName);
}
