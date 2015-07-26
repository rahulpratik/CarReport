package com.report.carreport

/**
 * Created by rahulpratik on 7/25/2015.
 */
class ActionLogFilters {
    def errorLogService

    def filters = {
        apiURIs(uri: '/api/**') {
            before = {
                try {
                    new ActionLog(actionName: actionName, controllerName: controllerName,
                            url: request.requestURI, date: new Date()).save(flush: true, failOnError: true)
                } catch (Exception e) {
                    errorLogService.logError(e)
                }
            }

        }
    }
}
