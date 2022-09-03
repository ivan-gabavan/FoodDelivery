class Courier extends IndividualEntity{
    #passport
    constructor(phone, email, password, name, surname, city, passport) {
        super(phone, email, password, name, surname, city)
        this.#passport = passport
    }
    toJSON() {
        let jsonObj = super.toJSON()
        jsonObj["passport"] = this.passport.toJSON()
        jsonObj["surname"] = this.surname
        jsonObj["city"] = this.city
        return jsonObj
    }
    get passport(){return this.#passport}
    set passport(value){
        if (value.class == Passport){
            this.#passport.series = value.series
            this.#passport.number = value.number
            this.#passport.placeOfIssue = value.placeOfIssue
            this.#passport.dateOfIssue = value.dateOfIssue
            return
        }
        throw new Error("Wrong Passport!")
    }
}