package com.report.carreport

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ErrorController)
class ErrorControllerSpec extends Specification {

    void "got exception"(){
        setup:
        ErrorLogService errorLogService = Mock(ErrorLogService)
        errorLogService.logError(_) >> {
            return
        }
        controller.errorLogService = errorLogService

        when:
        controller.handleException()

        then:
        1 * errorLogService.logError(_) >> {
            return
        }
    }
}
