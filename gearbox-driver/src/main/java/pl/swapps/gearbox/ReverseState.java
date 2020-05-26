package pl.swapps.gearbox;

public class ReverseState implements GearboxState {

    @Override
    public void shift(GearboxFacade gearboxFacade, int gearsCount) {
        throw new UnsupportedOperationException("Cannot shift while on reverse");
    }
}
