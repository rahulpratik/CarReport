package com.report.carreport

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
        id generator:'assigned', name: "vin", type: 'string'
        accidentRecords sort: 'reported', order: 'desc'
    }

}
