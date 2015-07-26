package com.report.carreport

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ActionLog)
class ActionLogSpec extends Specification {
    ActionLog actionLog

    def setup() {
        actionLog = new ActionLog(actionName: 'Test', controllerName: 'Test', url: 'Test', date: new Date())
    }

    def cleanup() {
        actionLog = null
    }

    void "test constraints success"() {
        when:
        boolean isValid = actionLog.validate()

        then:
        isValid == true
    }

    void "test constraints date is null"() {
        setup:
        actionLog.date = null

        when:
        boolean isValid = actionLog.validate()

        then:
        isValid == false
    }
}
