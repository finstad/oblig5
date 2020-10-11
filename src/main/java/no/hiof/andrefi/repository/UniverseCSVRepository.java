package no.hiof.andrefi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.andrefi.model.Planet;
import no.hiof.andrefi.model.PlanetSystem;
import no.hiof.andrefi.model.Star;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UniverseCSVRepository implements IUniverseRepository{

    private ArrayList<PlanetSystem> planetSystems = new ArrayList<>();


    public UniverseCSVRepository(String filnavn) {
        HashMap<String, PlanetSystem> planetSystemsFromCSV = readFromCSV(filnavn);

        //System.out.println(readFromCSV(filnavn));

        planetSystems.addAll(planetSystemsFromCSV.values());

        writeToCSV("planetsystemtest.csv", planetSystemsFromCSV);
    }

    public static HashMap<String, PlanetSystem> readFromCSV(String filnavn){
        HashMap<String, PlanetSystem> planetSystemsCSV = new HashMap<>();
        Star star = null;
        PlanetSystem planetSystem = null;
        ArrayList<Planet> planets = null;

        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filnavn));

            while ((line = br.readLine()) != null){

                String[] splitter = line.split(",");

                if (planetSystemsCSV.containsKey(splitter[0])){
                    Planet planet = new Planet(splitter[7], Double.parseDouble(splitter[8]), Double.parseDouble(splitter[9]), Double.parseDouble(splitter[10]), Double.parseDouble(splitter[11]), Double.parseDouble(splitter[12]), star , splitter[13]);
                    planets.add(planet);
                } else {
                    planets = new ArrayList<>();

                    star = new Star(splitter[2], Double.parseDouble(splitter[4]), Double.parseDouble(splitter[3]), Double.parseDouble(splitter[5]), splitter[6]);
                    planetSystem = new PlanetSystem(splitter[0], star, planets, splitter[1]);
                    Planet planet = new Planet(splitter[7], Double.parseDouble(splitter[8]), Double.parseDouble(splitter[9]), Double.parseDouble(splitter[10]), Double.parseDouble(splitter[11]), Double.parseDouble(splitter[12]), star , splitter[13]);
                    planets.add(planet);
                }

                planetSystemsCSV.put(splitter[0], planetSystem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return planetSystemsCSV;
    }

    public static void writeToCSV(String filnavn, HashMap<String, PlanetSystem> planetSystem){

        try (FileWriter filewriter = new FileWriter(filnavn, false)){
            for (PlanetSystem planetSystem1 : planetSystem.values()) {
                for (Planet planet : planetSystem1.getPlanets()){
                    filewriter.write(planetSystem1.getName() + ","
                    + planetSystem1.getPictureUrl() + ","
                    + planetSystem1.getCenterStar().getName() + ","
                    + planetSystem1.getCenterStar().getMass() + ","
                    + planetSystem1.getCenterStar().getRadius() + ","
                    + planetSystem1.getCenterStar().getEffectiveTemperature() + ","
                    + planetSystem1.getCenterStar().getPictureUrl() + ","
                    + planet.getName() + ","
                    + planet.getMass() + ","
                    + planet.getRadius() + ","
                    + planet.getSemiMajorAxis() + ","
                    + planet.getEccentricity() + ","
                    + planet.getOrbitalPeriod() + ","
                    + planet.getPictureUrl() + "\n");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


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

    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystemName) {

    }

}
