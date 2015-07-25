package com.report.carreport

class ActionLogFilters {
    def errorLogService //platform-core event is not injected in filters, so injecting the service directly

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
