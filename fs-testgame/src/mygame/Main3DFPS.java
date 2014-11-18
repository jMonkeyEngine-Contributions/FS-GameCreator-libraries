package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.ractoc.fs.appstates.FlightAppState;
import com.ractoc.fs.appstates.FlightControlAppState;
import com.ractoc.fs.appstates.SceneAppState;
import com.ractoc.fs.components.es.CanMoveComponent;
import com.ractoc.fs.components.es.ControlledComponent;
import com.ractoc.fs.components.es.Controls;
import com.ractoc.fs.components.es.FollowComponent;
import com.ractoc.fs.components.es.LocationComponent;
import com.ractoc.fs.components.es.MovementComponent;
import com.ractoc.fs.components.es.RenderComponent;
import com.ractoc.fs.components.es.SpeedComponent;
import com.ractoc.fs.es.Entities;
import com.ractoc.fs.es.componentstorages.InMemoryComponentStorage;

public class Main3DFPS extends SimpleApplication {
    
    private static Entities entities = Entities.getInstance();

    public static void main(String[] args) {
        Main3DFPS app = new Main3DFPS();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        rootNode.addLight(new DirectionalLight());
        setupKeys();
        setupEntitySystem();
        setupAppStates();
        spawnPlane();
    }

    private void setupEntitySystem() {         
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                RenderComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                LocationComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                FollowComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                CanMoveComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                ControlledComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                SpeedComponent.class);
        Entities.getInstance().registerComponentTypesWithComponentStorage(new InMemoryComponentStorage(),
                MovementComponent.class);
    }

    private void setupAppStates() {
        stateManager.attachAll(new SceneAppState("Scenes/testSceneFPS.j3o"),
                new FlightControlAppState(),
                new FlightAppState());
    }

    private void spawnPlane() {
        entities.createEntity(new RenderComponent("Models/Plane1.j3o"),
                new LocationComponent(Vector3f.ZERO, new Quaternion(), new Vector3f(1, 1, 1)),
                new FollowComponent(new Vector3f(0, 15, -25)),
                new CanMoveComponent(15f, 5f, 2f, 5f, 2.5f),
                new ControlledComponent());
    }

    private void setupKeys() {
        inputManager.addMapping(Controls.MOVE_FORWARD.name(),
                                new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(Controls.MOVE_BACKWARDS.name(),
                                new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(Controls.STRAFE_LEFT.name(),
                                new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping(Controls.STRAFE_RIGHT.name(),
                                new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping(Controls.ROTATE_LEFT.name(),
                                new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(Controls.ROTATE_RIGHT.name(),
                                new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(Controls.SHOOT_MAIN.name(),
                                new KeyTrigger(KeyInput.KEY_SPACE));
    }
}
