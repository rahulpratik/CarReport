package com.report.carreport

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CarService)
@Mock([Car])
class CarServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create New Car Record"() {
        when:
        def newCarRecord = service.createNewCarRecord()

        then:
        newCarRecord != null
    }
     void "test save car"() {
        setup:
        def newCarRecord = service.createNewCarRecord()
        newCarRecord.vin = "ABC-111-1234"
        newCarRecord.make = "Honda"
        newCarRecord.color = "Blue"
        newCarRecord.model = "Civic"
        newCarRecord.year = 2003
        newCarRecord.price = 3000

        when:
        def result = service.saveCarRecord(newCarRecord)

        then:
        result.vin != null
        "ABC-111-1234".equals(result.vin)
        newCarRecord.year == 2003
    }
     void "test delete"(){
        setup:
        def carRecord = new Car(vin: "ABC-111-234", make: "Honda", model: "Civic", year: 2014, price: 1000, color: 'blue', mileage: 3000).save(flush: true,failOnError: true)
        when:
        service.deleteCarRecord(carRecord)

        then:
        Car.get(carRecord.vin) == null
    }
}
