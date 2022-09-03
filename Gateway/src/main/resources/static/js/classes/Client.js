class Client extends IndividualEntity{
    constructor(phone, email, password, name, surname, city) {
        super(phone, email, password, name, surname, city)
    }
    toJSON() {
        return super.toJSON()
    }
}