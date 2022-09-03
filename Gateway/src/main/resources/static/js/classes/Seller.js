class Seller extends LegalEntity{
    constructor(phone, email, password, inn, name, address) {
        super(phone, email, password, inn, name, address);
    }
    toJSON() {
        return super.toJSON()
    }
}