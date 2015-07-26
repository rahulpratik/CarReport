package com.report.carreport

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CarController)
@Mock([Car, AccidentRecords, Accident])
class CarControllerSpec extends Specification {

    def carService
    def minorAccident
    def majorAccident
    def totalledAccident
    AccidentRecords minorAccidentRecord
    AccidentRecords majorAccidentRecord
    AccidentRecords totalledAccidentRecord

    def setup() {
        minorAccident = new Accident(type: 'Minor').save(flush: true)
        majorAccident = new Accident(type: 'Major').save(flush: true)
        totalledAccident = new Accident(type: 'Totalled').save(flush: true)
        minorAccidentRecord = new AccidentRecords(reported: new Date(), accident: minorAccident)

        totalledAccidentRecord = new AccidentRecords(reported: new Date(), accident: totalledAccident)
        carService = Mock(CarService)
        controller.carService = carService
    }

    def cleanup() {
        carService = null
        minorAccident = null
        majorAccident = null
        totalledAccident = null
        minorAccidentRecord = null
        majorAccidentRecord = null
        totalledAccidentRecord = null
    }

    void "test car record create"() {
        setup:
        carService.createNewCarRecord()>>{
            def carRecord = new Car()
            return carRecord
        }

        when:
        controller.create()

        then:
        response.contentType == "application/json;charset=UTF-8"
        response.contentAsString != null
    }

    void "test show when no vin is passed "(){
        when:
        controller.show()

        then:
        response.status == 400
    }

    void "test car record delete"(){
        setup:
        boolean isDeleted = false

        def carRecord = new Car(vin: "ABC-111-2342", make: "Honda", model: "Civic", year: 2014, price: 1000, color: 'blue', mileage: 3000).save(flush: true)
        carService.deleteCarRecord(_  as Car)>>{
            isDeleted = true
        }
        params.vin = carRecord.vin

        when:
        controller.delete()

        then:
        isDeleted == true
        response.status == 200
    }

    void "test save car record with no params"(){
        when:
        controller.save()

        then:
        response.status == 400
    }

    /*void "test save car"(){
        given :
        Car carRecord = new Car(vin: "ABC-112-2343", make: "Honda", model: "Civic", year: 2014, price: 1000, color: 'blue', mileage: 3000)
        request.JSON = carRecord
        carService.saveCarRecord(_)>>{
            carRecord = carRecord.save(flush:true , failOnError: true)
            return carRecord
        }
        when:
        controller.save()

        then:
        carRecord != null
        carRecord.vin != null
    }*/

    void "test update mileage"(){
        given :
        Car carRecord = new Car(vin: "ABC-111-2342", make: "Honda", model: "Civic", year: 2014, price: 1000, color: 'blue', mileage: 3000).save(flush: true)
        carRecord.mileage = 8000
        request.JSON = carRecord
        carService.saveCarRecord(_)>>{
            carRecord = carRecord.save(flush:true , failOnError: true)
            return carRecord
        }
        when:
        controller.update()

        then:
        carRecord != null
        "ABC-111-2342".equals(carRecord.vin)
        carRecord.mileage == 8000
    }

    void "record a minor accident"(){
        setup:
        long price = 1000
        Car carRecord = new Car(vin: "ABC-111-2343", make: "Honda", model: "Civic", year: 2014, price: price, color: 'blue', mileage: 3000)
        carRecord.addToAccidentRecords(minorAccidentRecord)
        carRecord.save(flush: true)
        request.JSON = carRecord
        carService.saveCarRecord(_)>>{
            carRecord = carRecord.save(flush:true , failOnError: true)
            return carRecord
        }
        when:
        controller.update()

        then:
        carRecord != null
        "ABC-111-2343".equals(carRecord.vin)
        (carRecord.price +100) == price
    }

    void "record a major accident"(){
        setup:
        long price = 1000
        majorAccidentRecord = new AccidentRecords(reported: new Date(), accident: majorAccident)
        Car carRecord = new Car(vin: "ABC-111-2344", make: "Honda", model: "Civic", year: 2014, price: price, color: 'blue', mileage: 3000)
        carRecord.addToAccidentRecords(majorAccidentRecord)
        carRecord.save(flush: true)
        request.JSON = carRecord
        carService.saveCarRecord(_)>>{
            carRecord = carRecord.save(flush:true , failOnError: true)
            return carRecord
        }
        when:
        controller.update()

        then:
        carRecord != null
        "ABC-111-2344".equals(carRecord.vin)
        (carRecord.price +200) == price
    }

    void "record a totalled accident"(){
        setup:
        long price = 1000
        Car carRecord = new Car(vin: "ABC-111-2345", make: "Honda", model: "Civic", year: 2014, price: price, color: 'blue', mileage: 3000)
        carRecord.addToAccidentRecords(totalledAccidentRecord)
        carRecord.save(flush: true)
        request.JSON = carRecord
        carService.saveCarRecord(_)>>{
            carRecord = carRecord.save(flush:true , failOnError: true)
            return carRecord
        }
        when:
        controller.update()

        then:
        carRecord != null
        "ABC-111-2345".equals(carRecord.vin)
        carRecord.price  == 0
    }
}
