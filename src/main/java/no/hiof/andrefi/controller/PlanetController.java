package no.hiof.andrefi.controller;

import io.javalin.http.Context;
import no.hiof.andrefi.model.Planet;
import no.hiof.andrefi.repository.IUniverseRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlanetController {
    private IUniverseRepository universeRepository;

    public PlanetController(IUniverseRepository universeRepository){
        this.universeRepository = universeRepository;
    }

    public void getAllPlanets(Context context) {
        String planetSystemId = context.pathParam(":planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> allePlaneter = universeRepository.getAllePlaneter(planetSystemId);

        if (sortBy != null) {
            switch (sortBy){
                case "name":
                    allePlaneter.sort((planet1, planet2) -> planet1.getName().compareTo(planet2.getName()));
                    break;
                case "mass":
                    Collections.sort(allePlaneter);
                    break;
                case "radius":
                    allePlaneter.sort(Planet.comparedByRadius);
                    break;
                case "num":
                    allePlaneter.sort(Planet.comparedByNumber);
                    break;
            }
        }

        context.json(allePlaneter);
    }

    public void getPlanet(Context context) {
        String planetSystemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam("planet-id");

        Planet planet = universeRepository.getPlanet(planetSystemId, planetId);

        context.json(planet);
    }

    public void deletePlanet(Context context) {
        String planetSystemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam(":planet-id");

        universeRepository.deletePlanet(planetSystemId, planetId, "planets_4000.json");

        context.redirect("/planet-systems/" + planetSystemId);

    }

    public void createPlanet(Context context){
        String planetSystemId = context.pathParam(":planet-system-id");

        String planetName = context.formParam("name");
        double planetMass = Double.parseDouble(context.formParam("mass"));
        double planetRadius = Double.parseDouble(context.formParam("radius"));
        double planetSemiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        double planetEccentricity = Double.parseDouble(context.formParam("eccentricity"));
        double planetOrbitalPeriod = Double.parseDouble(context.formParam("orbitalPeriod"));
        String planetPictureUrl = context.formParam("pictureUrl");


        universeRepository.createPlanet(planetName, planetMass, planetRadius, planetSemiMajorAxis, planetEccentricity, planetOrbitalPeriod, planetPictureUrl, planetSystemId);

        context.redirect("/planet-systems/" + planetSystemId);
    }

    public void updatePlanet (Context context){

        deletePlanet(context);

        createPlanet(context);
    }

}
