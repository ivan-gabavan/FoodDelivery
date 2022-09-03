class User{
    #phone
    #email
    #password

    constructor(phone, email, password) {
        if (this.constructor == User) {
            throw new Error("Abstract classes can't be instantiated.")
        }
        this.phone = phone
        this.email = email
        this.password = password
    }
    toJSON() {
        return {
            phone: this.phone,
            email: this.email,
            password: this.password
        }
    }
    get phone(){
        return this.#phone;
    }
    set phone(value){
        if (phoneValidation(value)){
            this.#phone = phoneStandardization(value)
            return
        }
        throw new Error("Wrong phone number")
    }
    get email(){return this.#email}
    set email(value){
        if (emailValidation(value)){
            this.#email = emailStandardization(value)
            return
        }
        throw new Error("Wrong email")
    }
    get password(){return this.#password}
    set password(value){
        if (passwordValidation(value)){
            this.#password = passwordStandardization(value)
            return
        }
        throw new Error("Wrong password")
    }
}