package com.report.carreport

class ActionLog {

    String actionName
    String controllerName
    String url
    Date date
    static constraints = {
        actionName(nullable: true)
        controllerName(nullable: true)
        url(nullable: true)
    }
}
