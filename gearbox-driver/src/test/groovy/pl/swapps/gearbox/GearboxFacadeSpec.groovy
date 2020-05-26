package pl.swapps.gearbox

import com.gearbox.Gearbox
import spock.lang.Specification

class GearboxFacadeSpec extends Specification {

    private def gearbox = Mock(Gearbox)
    private def gf = new GearboxFacade(gearbox)

    def "should get active state"() {
        given:
            gearbox.getState() >> originalState
        when:
            def state = gf.getState()
        then:
            state == expectedState
        where:
            originalState | expectedState
            1             | GearboxState.DRIVE
            2             | GearboxState.PARK
            3             | GearboxState.REVERSE
            4             | GearboxState.NEUTRAL
    }

    def "should get current gear"() {
        given:
            gearbox.getCurrentGear() >> originalGear
        when:
            def currentGear = gf.getCurrentGear()
        then:
            currentGear == expectedGear
        where:
            originalGear | expectedGear
            -1           | -1
            1            | 1
            2            | 2
            3            | 3
            4            | 4
            5            | 5
            6            | 6
            7            | 7
            8            | 8
    }

    def "should set current gear"() {
        when:
            gf.setCurrentGear(1)
        then:
            1 * gearbox.setCurrentGear(1)
            0 * _
    }
}
