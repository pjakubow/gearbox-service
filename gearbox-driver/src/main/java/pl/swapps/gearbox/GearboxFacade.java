package pl.swapps.gearbox;

import java.util.Map;

import com.gearbox.Gearbox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GearboxFacade {

    private static final Logger logger = LoggerFactory.getLogger(GearboxFacade.class);

    private static final Map<Integer, GearboxState> stateMapping = Map.of(
            1, new DriveState(),
            2, new ParkState(),
            3, new ReverseState(),
            4, new NeutralState()
    );

    private final Gearbox gearbox;

    public GearboxFacade(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public GearboxState getState() {
        Integer originalState = (int) gearbox.getState();
        return stateMapping.get(originalState);
    }

    public void shift(int gearsCount) {
        logger.info("Shifting gears by {}", gearsCount);
        int result = getCurrentGear() + gearsCount;
        if (result > gearbox.getMaxDrive() || result < 1) {
            logger.warn("Cannot shift to {}", result);
            return;
        }
        logger.debug("Setting current gear to {}", result);
        setCurrentGear(result);
    }

    private int getCurrentGear() {
        return (int) gearbox.getCurrentGear();
    }

    private void setCurrentGear(int gear) {
        gearbox.setCurrentGear(gear);
    }
}
