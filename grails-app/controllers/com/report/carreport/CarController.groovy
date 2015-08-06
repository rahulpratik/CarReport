package com.report.carreport

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.FORBIDDEN

class CarController {

    def carService
    static responseFormats = ['json']

    def create() {
        respond carService.createNewCarRecord()
    }

    /**
     * Retrieves a car object with the given vin
     * @params vin
     */
    def show(){
        Car carObject = Car.get(params?.vin)
        if(!carObject){
            render status : BAD_REQUEST
            return
        }
        // checkPermissions : check whether user has authorization to get any car record or not
        // can call a permissionService , use traits to check
        boolean checkPermissions = true
        if(checkPermissions){
            respond carObject
        }else{
            render status : FORBIDDEN
            return
        }
    }

    /**
     * Deletes a car record.
     * @return
     */
    def delete() {
        log.info("in delete")
        Car carObject = Car.get(params?.vin)
        if(!carObject) {
            render status: BAD_REQUEST
            return
        }
        // checkPermissions : check whether user has authorization to get any car record or not
        // can call a permissionService , use traits to check
        boolean checkPermissions = true
        if(checkPermissions) {
            carService.deleteCarRecord(carObject)
            respond status : OK
        } else {
            render status: FORBIDDEN
            return
        }
    }

    /**
     * creates and saves a new car record
     * @param car
     * @return
     */
    def save(){
        log.info("in save")
        saveOrUpdate()
    }

    /**
     * update existing car record
     * @param car
     * @return
     */

    def update(){
        log.info("in save")
        saveOrUpdate()
    }

    /**
     * Binds the params and saves/updates the car object.
     * @return
     */
    private def saveOrUpdate() {
        Car carRecord = bindCarParams(request.JSON)
        if(!carRecord || !carRecord.validate()) {
            render status: BAD_REQUEST
            return
        }
        respond carService.saveCarRecord(carRecord)
    }

    private bindCarParams(req){
        Car carRecord = new Car()
        if (req.vin) {
            carRecord = Car.get(req.vin)
        }
        bindData(carRecord, req)
        if(carRecord.accidentRecords && carRecord.accidentRecords.size() >0){
            changeCarValue(carRecord)
        }
        carRecord
    }

    private def changeCarValue(Car car){
        def value = car.price
        def lastReportedAccident = car.accidentRecords.get(0)
        switch (lastReportedAccident.accident.type){
            case Accident.MINOR:
                if(value > 100){
                    value = value - 100/*(Accident.findByType(Accident.MINOR))*/
                }else{
                    value = 0
                }
                break
            case Accident.MAJOR:
                if(value > 100){
                    value = value - 200
                }else{
                    value = 0
                }
                break
            case Accident.TOTALLED:
                value = 0
            default:
                break
        }
        car.price = value
    }

}
