package pl.swapps.gearbox;

public class DriveState implements GearboxState {

    @Override
    public void shift(GearboxFacade gearboxFacade, int gearsCount) {
        gearboxFacade.shift(gearsCount);
    }
}
