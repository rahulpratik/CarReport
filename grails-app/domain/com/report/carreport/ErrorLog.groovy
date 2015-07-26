package com.report.carreport

/**
 * Created by rahulpratik on 7/25/2015.
 */
class ErrorLog {

    String host
    String type
    String message
    String detailMessage
    String statusCode
    Date time
    static constraints = {
        type(nullable: true)
        message(nullable: true)
        detailMessage(nullable: true)
        statusCode(nullable: true)
    }

    static mapping = {
        message sqlType: 'text'
        detailMessage sqlType: 'text'
    }
}
