package com.report.carreport

import grails.transaction.Transactional

@Transactional
class CarService {

    def createNewCarRecord() {
        Car newCar = new Car()
        return newCar
    }

    def deleteCarRecord(Car car){
        car.delete(flush: true, failOnError: true)
    }

    def saveCarRecord(Car car){
        def savedCar
        savedCar = car.save(failOnError: true, flush: true)
        return savedCar
    }
}
