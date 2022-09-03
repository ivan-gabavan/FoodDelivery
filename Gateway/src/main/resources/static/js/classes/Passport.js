class Passport{
    #series
    #number
    #dateOfIssue
    #placeOfIssue
    constructor(series, number, dateOfIssue, placeOfOIssue) {
        this.series = series
        this.number = number
        this.dateOfIssue = dateOfIssue
        this.placeOfIssue = placeOfOIssue
    }
    toJSON() {
        return {
            series: this.series,
            number: this.number,
            dateOfIssue: this.dateOfIssue,
            placeOfIssue: this.placeOfIssue
        }
    }
    get series(){
        return this.#series
    }
    set series(value){
        if (passportSeriesValidation(value)) {
            this.#series = passportSeriesStandardization(value)
            return
        }
        throw new Error("Wrong passport series!")
    }
    get number(){
        return this.#number
    }
    set number(value){
        if (passportNumberValidation(value)) {
            this.#number = passportNumberStandardisation(value)
            return
        }
        throw new Error("Wrong passport number!")
    }
    get dateOfIssue(){return this.#dateOfIssue}
    set dateOfIssue(value){
        if (dateValidation(value)){
            this.#dateOfIssue = dateStandardisation(value)
            return
        }
        throw new Error("Wrong date of issue!")
    }
    get placeOfIssue(){return this.#placeOfIssue}
    set placeOfIssue(value){
        if (placeOfIssueValidation(value)){
            this.#placeOfIssue = placeOfIssueStandardization(value)
            return
        }
        throw new Error("Wrong place of issue!")
    }

}