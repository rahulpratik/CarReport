package com.report.carreport

/**
 * Created by rahulpratik on 7/25/2015.
 */
class AccidentRecords {

    Date reported
    Accident accident

    static belongsTo = [car: Car]

}
