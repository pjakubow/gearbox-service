package pl.swapps.gearbox

import com.gearbox.ExternalSystems
import com.gearbox.Gearbox
import spock.lang.Specification

class GearboxDriverSpec extends Specification {

    def "should change gear"() {
        given:
            def gearbox = Mock(Gearbox)
            gearbox.getState() >> 1
            gearbox.getCurrentGear() >> 1
            gearbox.getMaxDrive() >> 8
            def externalSystems = Mock(ExternalSystems)
            externalSystems.getCurrentRpm() >> 3000
            def driver = new GearboxDriver(new GearboxFacade(gearbox), externalSystems, new ComfortDriveMode())
        when:
            driver.onGas(0.1f)
        then:
            0 * gearbox.setCurrentGear(_ as int)
    }
}
