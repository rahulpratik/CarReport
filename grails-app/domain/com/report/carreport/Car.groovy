package com.report.carreport

/**
 * Created by rahulpratik on 7/25/2015.
 */
class Car {

    String vin
    String make
    String model
    int year
    long price
    String color
    int mileage

    List<AccidentRecords> accidentRecords = []

    static hasMany = [accidentRecords: AccidentRecords]

    static mapping = {
        id generator:'assigned', name: 'vin', type: 'string'
        accidentRecords(column: "car_id", joinTable: false, cascade: 'all-delete-orphan')
        accidentRecords sort: 'reported', order: 'desc'
    }

    static constraints = {
        accidentRecords(nullable: true)
    }

}
