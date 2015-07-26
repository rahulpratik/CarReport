package com.report.carreport

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AccidentRecords)
@Mock([AccidentRecords, Car, Accident])
class AccidentRecordsSpec extends Specification {

    def accidentRecord
    def accident
    def car

    def setup() {
        accident = new Accident(type: "Minor").save(flush: true)
        accidentRecord = new AccidentRecords(reported: new Date(), accident: accident)
        car = new Car(vin: "ABC-111-234", make: "Honda", model: "Civic", year: 2014, price: 1000, color: 'blue', mileage: 3000)
    }

    def cleanup() {
    }

    void "test save accident record without car"() {
        expect:
        !accidentRecord.validate()
    }

    void "test save accident record with a car"() {
        given:
        accidentRecord.car = car

        expect:
        accidentRecord.validate()

    }
}
