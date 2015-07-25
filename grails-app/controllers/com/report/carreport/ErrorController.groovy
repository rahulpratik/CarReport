package com.report.carreport
import org.apache.commons.lang.exception.ExceptionUtils

class ErrorController {

    def errorLogService

    /**
     * call the error service to log and email the error
     * @return
     */
    def handleException(){
        try {
            errorLogService.logError(request.exception)
        } catch(ex) {
            //log error
            log.error(ExceptionUtils.getFullStackTrace(ex))
        }

    }
}
