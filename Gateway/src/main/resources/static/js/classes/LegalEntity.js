class LegalEntity extends User{
    #inn
    #address
    #name
    constructor(phone, email, password, inn, name, address) {
        super(phone, email, password)
        if (this.constructor == LegalEntity) {
            throw new Error("Abstract classes can't be instantiated.")
        }
        this.inn = inn;
        this.address = address
        this.name = name
    }
    toJSON() {
        let jsonObj = super.toJSON()
        jsonObj["inn"] = this.inn
        jsonObj["address"] = this.address
        jsonObj["name"] = this.name
        return jsonObj
    }
    get inn(){return this.#inn}
    set inn(value){
        if (innValidation(value)){
            this.#inn = innStandardization(value)
            return
        }
        throw new Error("Wrong inn")
    }
    get address(){return this.#address}
    set address(value){
        if (geoObjectsCash.get(value) == undefined){throw new Error("Address can be invalid.")}
        this.#address = geoObjectsCash.get(value).getAddressLine()
    }
    get name(){return this.#name}
    set name(value){
        if (shopNameValidation(value)){
            this.#name = shopNameStandardization(value)
            return
        }
        throw new Error("Wrong shop name!")
    }
}