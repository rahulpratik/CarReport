package com.report.carreport

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ErrorLogService)
@Mock([ErrorLog])
class ErrorLogServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test save error log success"() {
        setup:
        String message = "Test"
        Exception ex = new Exception(message)

        when:
        service.logError(ex)

        then:
        def error = ErrorLog.findByStatusCode(INTERNAL_SERVER_ERROR)
        error.message.contains(message)
    }
}
