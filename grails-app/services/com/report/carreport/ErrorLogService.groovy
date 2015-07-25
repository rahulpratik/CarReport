package com.report.carreport

import grails.transaction.Transactional
import org.apache.commons.lang.exception.ExceptionUtils

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR


@Transactional
class ErrorLogService {

    def logError(Exception ex) {
        String hostName = InetAddress.getLocalHost().getHostName()
        try {
            new ErrorLog(host: hostName,
                    type: ExceptionUtils.getRootCause(ex).getClass().getCanonicalName(),
                    message: ExceptionUtils.getRootCauseMessage(ex),
                    detailMessage: ExceptionUtils.getFullStackTrace(ex),
                    statusCode: INTERNAL_SERVER_ERROR,
                    time: new Date()
            ).save(flush: true, failOnError: true)
        } catch(e) {
            log.error(ExceptionUtils.getFullStackTrace(e))
        }
    }
}
