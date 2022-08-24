let geoObjectsCash = new Map()

const cyrillicPattern = /^\p{Script=Cyrillic}+$/u

function phoneValidation(phone) {
    phone = phone.replace(/[-+()\s]/g, '')
    if (phone.length == 11) {
        if ((phone[0] == "8") || (phone[0] == "7")) {
            return true
        }
    }
    if ((phone.length == 10) && (phone[0] == "9")) {
        return true
    }
    return false
}

function emailValidation(email) {
    email = email.toLowerCase()
    const reg = /\S+@\S+\.\S+/
    return reg.test(email)
}

function passwordValidation(password) {
    const reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/
    return reg.test(password)
}

function nameValidation(name) {
    return cyrillicPattern.test(name)
}

function surnameValidation(surname) {
    return cyrillicPattern.test(surname)
}

function passportSeriesValidation(series) {
    const possibleSeries = [1, 3, 4, 5, 7, 8, 11, 12, 14, 15, 17, 18, 19, 20, 22, 24, 25, 26, 27, 28, 29, 32, 33, 34, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 60, 61, 63, 64, 65, 66, 68, 69, 73, 75, 76, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 91, 92, 93, 94, 95, 96, 97, 98, 99]
    series = series.replace(/[-()\s]/g, '')
    if (possibleSeries.indexOf(parseInt(series.substring(0, 2))) != -1) {
        const reg = /\d{4}/
        return reg.test(series)
    }
    return false
}

function passportNumberValidation(number) {
    number = number.replace(/[-()\s]/g, '')
    const reg = /\d{6}/
    return reg.test(number)
}

function dateValidation(date) {
    const reg = /(\d{1,2}[\s-\/.]){2}\d{4}/
    if (!reg.test(date)) {
        return false
    }

    date = date.replace(/[-/_.\s]/g, "-")
    let parts = date.split("-");
    let day = parseInt(parts[0], 10);
    let month = parseInt(parts[1], 10);
    let year = parseInt(parts[2], 10);

    if (year < 1900 || year > 2030 || month <= 0 || month > 12)
        return false;

    const monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;
    return (day > 0) && (day <= monthLength[month - 1]);
}

function placeOfIssueValidation(place) {
    const reg = /[^а-яА-ЯЁё"-'\s]/
    return !reg.test(place)
}

function cityValidation(city) {
    return new Promise(function (resolve, reject) {
        let geoObject
        if (geoObjectsCash.get(city) != undefined) {
            geoObject = geoObjectsCash.get(city)
            geoObject = geoObject.getLocalities().length ? geoObject.getLocalities() : geoObject.getAdministrativeAreas()
            resolve(geoObject[0].length != 0)
        } else {
            let geocoder = window.ymaps.geocode(city, {
                kind: "locality",
                results: 1
            })
            geocoder.then(
                function (res) {
                    let resGeoObject = res.geoObjects.get(0)
                    geoObjectsCash.set(city, resGeoObject)
                    if (resGeoObject == undefined){resolve(false)}
                    resGeoObject = resGeoObject.getLocalities().length ? resGeoObject.getLocalities() : resGeoObject.getAdministrativeAreas()
                    resolve(resGeoObject[0].length != 0)
                },
                function (err) {
                    reject(false)
                }
            )
        }
    })
}

function addressValidation(address){
    return new Promise(function (resolve, reject) {
        let geoObject
        if (geoObjectsCash.get(address) != undefined) {
            geoObject = geoObjectsCash.get(address)
            resolve(geoObject.getPremiseNumber() != undefined)
        } else {
            let geocoder = window.ymaps.geocode(address, {
                results: 1
            })
            geocoder.then(
                function (res) {
                    let resGeoObject = res.geoObjects.get(0)
                    geoObjectsCash.set(address, resGeoObject)
                    if (resGeoObject == undefined){resolve(false)}
                    resolve(resGeoObject.getPremiseNumber() != undefined)
                },
                function (err) {
                    reject(false)
                }
            )
        }
    })
}

function innValidation(inn){
    inn = inn.replace(/\D/g, '')
    const numberPattern = /\d{10}/g;
    return numberPattern.test(inn)
}

function shopNameValidation(shop){
    const reg = /[\w"\s-]+/g
    return reg.test(shop)
}