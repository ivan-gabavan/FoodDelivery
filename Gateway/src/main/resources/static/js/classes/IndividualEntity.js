class IndividualEntity extends User{
    #name;
    #surname;
    #city
    constructor(phone, email, password, name, surname, city) {
        super(phone, email, password)
        if (this.constructor == IndividualEntity) {
            throw new Error("Abstract classes can't be instantiated.")
        }
        this.name = name
        this.surname = surname
        this.city = city
    }
    get name(){return this.#name}
    set name(value){
        if(nameValidation(value)){
            this.#name = nameStandardization(value)
            return
        }
        throw new Error("Wrong name!")
    }
    get surname(){return this.#surname}
    set surname(value){
        if(surnameValidation(value)){
            this.#name = surnameStandardization(value)
            return
        }
        throw new Error("Wrong surname!")
    }
    get city(){return this.#city}
    set city(value){
        if (geoObjectsCash.get(value) == undefined){throw new Error("City can be invalid.")}
        let geoObject = geoObjectsCash.get(value)
        this.#city = (geoObject.getLocalities().length ? geoObject.getLocalities() : geoObject.getAdministrativeAreas())
    }
}