package pl.swapps.gearbox;

interface DriveMode {

    void onGas(GearboxFacade gearboxFacade, double rpm, float threshold);

    void onBreak(GearboxFacade gearboxFacade, double rpm);
}
