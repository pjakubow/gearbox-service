package pl.swapps.gearbox;

import com.gearbox.ExternalSystems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GearboxDriver {

    private static final Logger logger = LoggerFactory.getLogger(GearboxDriver.class);

    private final GearboxFacade gearboxFacade;

    private final ExternalSystems externalSystems;

    private DriveMode driveMode;

    public GearboxDriver(GearboxFacade gearboxFacade, ExternalSystems externalSystems, DriveMode defaultDriveMode) {
        this.gearboxFacade = gearboxFacade;
        this.externalSystems = externalSystems;
        this.driveMode = defaultDriveMode;
    }

    public void changeDriveMode(DriveMode driveMode) {
        this.driveMode = driveMode;
    }

    public void onGas(float threshold) {
        double currentRpm = externalSystems.getCurrentRpm();
        logger.debug("Pressing gas with threshold {}, current rpm: {}", threshold, currentRpm);
        driveMode.onGas(gearboxFacade, currentRpm, threshold);
    }

    public void onBreak() {
        driveMode.onBreak(gearboxFacade, externalSystems.getCurrentRpm());
    }
}
