package no.hiof.andrefi.controller;

import io.javalin.http.Context;
import no.hiof.andrefi.model.Planet;
import no.hiof.andrefi.model.PlanetSystem;
import no.hiof.andrefi.repository.IUniverseRepository;

import java.util.ArrayList;

public class PlanetSystemController {
    private IUniverseRepository universeRepository;

    public PlanetSystemController(IUniverseRepository universeRepository){
        this.universeRepository = universeRepository;
    }

    public void getAllPlanetSystems(Context context){
        ArrayList<PlanetSystem> allePlanetSystem = universeRepository.getAllePlanetSystemer();
        context.json(allePlanetSystem);


    }

    public void getPlanetSystem(Context context){
        String planetSystemId = context.pathParam(":planet-system-id");

        PlanetSystem planetSystem = universeRepository.getPlanetSystem(planetSystemId);

        //System.out.println(context.queryParam("sort_by"));

        context.json(planetSystem);

    }

}
