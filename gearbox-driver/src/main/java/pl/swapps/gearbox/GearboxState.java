package pl.swapps.gearbox;

interface GearboxState {

    void shift(GearboxFacade gearboxFacade, int gearsCount);
}
