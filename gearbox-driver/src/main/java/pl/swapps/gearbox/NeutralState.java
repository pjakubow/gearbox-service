package pl.swapps.gearbox;

public class NeutralState implements GearboxState {

    @Override
    public void shift(GearboxFacade gearboxFacade, int gearsCount) {
        throw new UnsupportedOperationException("Cannot shift while on neutral");
    }
}
