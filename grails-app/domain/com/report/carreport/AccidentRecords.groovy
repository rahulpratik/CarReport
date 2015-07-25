package com.report.carreport

class AccidentRecords {

    Date reported
    Accident accident

    static belongsTo = [car: Car]

}
