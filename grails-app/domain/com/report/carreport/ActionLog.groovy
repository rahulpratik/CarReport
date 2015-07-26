package com.report.carreport

/**
 * Created by rahulpratik on 7/25/2015.
 */
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
