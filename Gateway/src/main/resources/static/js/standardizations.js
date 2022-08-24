function phoneStandardization(validPhone){
    validPhone = validPhone.replace(/[-+()\s]/g, '')
    if (validPhone.length == 11){
        if (validPhone[0] == "8"){ validPhone = "7" + validPhone.substring(1)}
        if (validPhone[0] == "7"){return validPhone}
    }
    if ((validPhone.length == 10)&&(validPhone[0] == "9")){
        return "7" + validPhone
    }
    throw new Error("Can't standardize phone")
}

function emailStandardization(validEmail){
    return validEmail.toLowerCase()
}

function passwordStandardization(validPassword){
    return validPassword
}

function nameStandardization(validName){
    return validName.toLowerCase()
}

function surnameStandardization(validSurname){
    return validSurname.toLowerCase()
}

function passportSeriesStandardization(validSeries){
    return validSeries.replace(/[-+()\s]/g, '')
}

function passportNumberStandardisation(validNumber){
    return validNumber.replace(/[-()\s]/g, '')
}

function dateStandardisation(validDate){
    return date.replace(/[-/_.\s]/, "-")
}

function placeOfIssueStandardization(validPlace){
    return validPlace.toLowerCase()
}

function innStandardization(validInn){
    return validInn.replace(/\D/g, '')
}

function shopNameStandardization(validShop){
    return validShop
}