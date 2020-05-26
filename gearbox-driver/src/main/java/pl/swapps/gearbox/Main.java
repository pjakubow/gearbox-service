package pl.swapps.gearbox;

import com.gearbox.ExternalSystems;
import com.gearbox.Gearbox;

public class Main {

    private final ExternalSystems externalSystems;
    private final GearboxDriver gearboxDriver;

    public Main(ExternalSystems externalSystems, GearboxDriver gearboxDriver) {
        this.externalSystems = externalSystems;
        this.gearboxDriver = gearboxDriver;
    }

    public static void main(String[] args) {

        Gearbox gearbox = new Gearbox();
        gearbox.setMaxDrive(8);
        gearbox.setGearBoxCurrentParams(new Object[] {1, 1});
        GearboxFacade gearboxFacade = new GearboxFacade(gearbox);
        ExternalSystems externalSystems = new ExternalSystems();
        GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystems, new ComfortDriveMode());

        Main main = new Main(externalSystems, gearboxDriver);
        main.gas(1000d, 0.4f);
        main.gas(2000d, 0.4f);
        main.gas(3000d, 0.4f);
    }

    private void gas(double currentRpm, float threshold) {
        rpm(currentRpm);
        gearboxDriver.onGas(threshold);
    }

    private void rpm(double currentRpm) {
        externalSystems.setCurrentRpm(currentRpm);
    }
}
