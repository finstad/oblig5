package no.hiof.andrefi;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.andrefi.controller.PlanetController;
import no.hiof.andrefi.controller.PlanetSystemController;
import no.hiof.andrefi.repository.UniverseCSVRepository;
import no.hiof.andrefi.repository.UniverseJSONRepository;
import no.hiof.andrefi.repository.UniverseRepository;
import org.jetbrains.annotations.NotNull;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        app.config.enableWebjars();

        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));

        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-systems-detail"));

        app.get("/planet-systems", new VueComponent("planet-systems-overview"));

        app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));

        app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));


        /*UniverseRepository universeRepository = new UniverseRepository();
        PlanetSystemController planetSystemController = new PlanetSystemController(universeRepository);
        PlanetController planetController = new PlanetController(universeRepository);*/

        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository("planets_4000.json");
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);
        PlanetController planetController = new PlanetController(universeJSONRepository);

        /*UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository("src/main/resources/planets_4000.csv");
        PlanetSystemController planetSystemController = new PlanetSystemController(universeCSVRepository);
        PlanetController planetController = new PlanetController(universeCSVRepository);*/


        app.get("api/planet-systems/", ctx -> planetSystemController.getAllPlanetSystems(ctx));
        app.get("api/planet-systems/:planet-system-id", ctx -> planetSystemController.getPlanetSystem(ctx));

        app.get("/api/planet-systems/:planet-system-id/planets", ctx -> planetController.getAllPlanets(ctx));
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id", ctx -> planetController.getPlanet(ctx));

        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/delete", ctx -> planetController.deletePlanet(ctx));
        app.post("/api/planet-systems/:planet-system-id/createplanet", ctx -> planetController.createPlanet(ctx));

        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update", ctx -> planetController.updatePlanet(ctx));

    }
}
