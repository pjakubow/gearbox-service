package pl.swapps.gearbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ComfortDriveMode implements DriveMode {

    private final static Logger logger = LoggerFactory.getLogger(ComfortDriveMode.class);

    private final double rpmToDropWhileAccel = 1000d; //rpm czy redukować bieg przy przyspieszaniu
    private final double rpmToIncWhileAccel = 2500d; // rpm czy podbić bieg przy przyspieszaniu
    private final double rpmToDropOnKickdown = 4500d; // rpm czy zrzucić bieg w kickdown
    private final double rpmToDropOnBreak = 2000d; // rpm zrzucić bieg przy hamowaniu
    private final float kickdownThreshold = 0.5f; // threshold naciśnięcia pedału gazu, żeby jeszcze to nie był kickdown

    @Override
    public void onGas(GearboxFacade gearboxFacade, double rpm, float threshold) {
        int i = getGearsToShift(rpm, threshold);
        if (i == 0) {
            logger.debug("No shifting");
            return;
        }
        gearboxFacade.getState().shift(gearboxFacade, i);
    }

    private int getGearsToShift(double rpm, float threshold) {
        if (isKickdown(threshold) && rpm <= rpmToDropOnKickdown)
            return -2;
        if (rpm <= rpmToDropWhileAccel)
            return -1;
        if (rpm >= rpmToIncWhileAccel)
            return 1;
        return 0;
    }

    private boolean isKickdown(float threshold) {
        return threshold > kickdownThreshold;
    }

    @Override
    public void onBreak(GearboxFacade gearboxFacade, double rpm) {
        if (rpm <= rpmToDropOnBreak)
            gearboxFacade.getState().shift(gearboxFacade, -1);
    }
}
