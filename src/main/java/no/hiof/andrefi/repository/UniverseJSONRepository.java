package no.hiof.andrefi.repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.hiof.andrefi.model.Planet;
import no.hiof.andrefi.model.PlanetSystem;
import no.hiof.andrefi.model.Star;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UniverseJSONRepository implements IUniverseRepository {
    private ArrayList<PlanetSystem> planetSystems = new ArrayList<>();


    public UniverseJSONRepository(String filnavn){

        ArrayList<PlanetSystem> planetSystemsFromJsonFile = readFromJson(filnavn);

        planetSystems.addAll(planetSystemsFromJsonFile);

    }

    public static ArrayList<PlanetSystem> readFromJson(String filnavn){
        ArrayList<PlanetSystem> readPlanetSystems = new ArrayList<>();

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            PlanetSystem[] planetSystemsArray = objectMapper.readValue(new File(filnavn), PlanetSystem[].class);

            readPlanetSystems.addAll(Arrays.asList(planetSystemsArray));

        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readPlanetSystems;
    }

    public static void writeToJson(String filnavn, ArrayList<PlanetSystem> planetSystem){

        Thread thread = new Thread(() -> {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                objectMapper.writeValue(new File(filnavn), planetSystem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();

    }

    @Override
    public ArrayList<PlanetSystem> getAllePlanetSystemer(){
        return planetSystems;
    }

    @Override
    public PlanetSystem getPlanetSystem(String name) {
        for (PlanetSystem planetsystem : planetSystems) {
            if (planetsystem.getName().equals(name)){
                return planetsystem;
            }
        }
        return null;
    }

    @Override
    public Planet getPlanet(String planetSystemName, String name) {
        PlanetSystem valgtPlanetSystem = getPlanetSystem(planetSystemName);

        for (Planet planet : valgtPlanetSystem.getPlanets()){
            if (planet.getName().equals(name)){
                return planet;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Planet> getAllePlaneter(String name) {
        PlanetSystem valgtPlanetSystem = getPlanetSystem(name);

        if (valgtPlanetSystem != null) {
            return valgtPlanetSystem.getPlanets();
        }

        return new ArrayList<>();
    }

    @Override
    public void deletePlanet(String planetSystemName, String planetName, String filnavn) {

        for (PlanetSystem planetSystem : planetSystems) {
            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {
                if (planetSystem.getPlanets().get(i).getName().equals(planetName)){

                    System.out.println("Found planet");
                    planetSystem.getPlanets().remove(i);

                    writeToJson(filnavn, planetSystems);
                }
            }
        }

    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystemName) {

        Planet planet = new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl);


        for (PlanetSystem planetSystem : planetSystems) {
            for (int i = 0; i < planetSystem.getPlanets().size(); i++) {
                if (planetSystem.getName().equals(planetSystemName) && !planetSystem.getPlanets().get(i).getName().equals(name)) {

                    Star centralStar = planetSystem.getCenterStar();
                    Planet newPlanet = new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralStar, pictureUrl);
                    ArrayList<Planet> planets = planetSystem.getPlanets();
                    planets.add(newPlanet);

                    writeToJson("planets_4000.json", planetSystems);
                    break;

                } else if (planetSystem.getPlanets().get(i).getName().equals(name)) {
                    System.out.println("Planet does exist");
                }
            }
        }
    }


}
