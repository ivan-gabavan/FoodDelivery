function getHtmlClassTree(htmlClass) {
    switch (htmlClass) {
        case "courier":
            return ["user", "individual", "courier"]
        case "client":
            return ["user", "individual", "client"]
        case "seller":
            return ["user", "legal", "seller"]
    }
}

function hindAllExceptHtmlClasses(htmlClasses) {
    let notRoleClassSelector = 'div[class]:not(.form-row)'
    htmlClasses.forEach(htmlClass => {
        notRoleClassSelector += ":not(." + htmlClass + ")"
    })
    document.querySelectorAll(notRoleClassSelector).forEach(e => e.style.display = "none")
}

function showAllHtmlClasses(htmlClasses) {
    let roleClassSelector = ''
    htmlClasses.forEach(htmlClass => {
        roleClassSelector += "." + htmlClass + ","
    })
    roleClassSelector = roleClassSelector.slice(0, -1)
    document.querySelectorAll(roleClassSelector).forEach(e => e.style.display = "")
}

function changeRole() {
    let role = document.querySelector("input[name = role]:checked").value
    let htmlClassTree = getHtmlClassTree(role)
    hindAllExceptHtmlClasses(htmlClassTree)
    showAllHtmlClasses(htmlClassTree)
}

function sendRegForm() {
    let objReq
    const role = document.querySelector("input[name = role]:checked").value
    switch (role){
        case "client":{
            objReq = new Client(
                document.getElementById("phone").value,
                document.getElementById("email").value,
                document.getElementById("password").value,
                document.getElementById("name").value,
                document.getElementById("surname").value,
                document.getElementById("city").value
            )
            break
        }
        case "courier":{
            objReq = new Courier(
                document.getElementById("phone").value,
                document.getElementById("email").value,
                document.getElementById("password").value,
                document.getElementById("name").value,
                document.getElementById("surname").value,
                document.getElementById("city").value,
                new Passport(
                    document.getElementById("series").value,
                    document.getElementById("number").value,
                    document.getElementById("date-of-issue").value,
                    document.getElementById("place-of-issue").value
                )
            )
            break
        }
        case "seller":{
            objReq = new Seller(
                document.getElementById("phone").value,
                document.getElementById("email").value,
                document.getElementById("password").value,
                document.getElementById("inn").value,
                document.getElementById("shop-name").value,
                document.getElementById("address").value
            )
            break
        }
    }
    const http = new XMLHttpRequest()
    http.open('POST', '/registration/' + role, false)
    http.setRequestHeader('Content-type', 'application/json')
    http.send(JSON.stringify(objReq))
    //let parser = new DOMParser();
    //let doc = parser.parseFromString(http.response.toString(), "text/html");
    //window.document.body.innerHTML = doc.body.innerHTML
    alert(http.response)
    window.location.href = http.response
}

let inputsAcyncValidation = new Map([
    ["city", cityValidation],
    ["address", addressValidation]
])
let inputsValidation = new Map([
    ["phone", phoneValidation],
    ["email", emailValidation],
    ["password", passwordValidation],
    ["name", nameValidation],
    ["surname", surnameValidation],
    ["shop-name",shopNameValidation],
    ["inn",innValidation],
    ["series", passportSeriesValidation],
    ["date-of-issue", dateValidation],
    ["number", passportNumberValidation],
    ["place-of-issue", placeOfIssueValidation],
])
window.onload = function () {
    document.getElementById("role").addEventListener("change", changeRole)
    document.getElementById("submit").addEventListener("click", sendRegForm)
    document.querySelectorAll("input").forEach(input => {
            if (inputsValidation.get(input.id) != undefined) {
                input.addEventListener("input", function () {
                    if (!inputsValidation.get(input.id)(input.value)) {
                        input.style.background = '#FA8072'
                        document.getElementById("submit").disabled = true
                    } else {
                        input.style.background = '#EEE8AA'
                        document.getElementById("submit").disabled = false
                    }
                })
            }
            if (inputsAcyncValidation.get(input.id) != undefined) {
                input.addEventListener("change", function () {
                    document.getElementById("submit").disabled = true
                    let prom = inputsAcyncValidation.get(input.id)(input.value)
                    prom.then(function (res) {
                        if (!res) {
                            input.style.background = '#FA8072'
                        } else {
                            input.style.background = '#EEE8AA'
                            document.getElementById("submit").disabled = false
                            let geoObject = geoObjectsCash.get(input.value)
                            setPlacemark(geoObject.geometry.getCoordinates())
                        }
                    })
                })
            }
        }
    )
}

let map
let userPlacemark

function createPlacemark(coords) {
    return new window.ymaps.Placemark(coords, {
        iconCaption: 'поиск...'
    }, {
        preset: 'islands#violetDotIconWithCaption',
        //draggable: true
    });
}

function getFirstGeoObject(coords, object) {
    userPlacemark.properties.set('iconCaption', 'поиск...');
    return new Promise(function (resolve, reject) {
        window.ymaps.geocode(coords, {
            kind: object,
            results: 1
        }).then(function (res) {
            let firstGeoObject = res.geoObjects.get(0);
            resolve(firstGeoObject)
        })
    })
}

function getCity(coords) {
    getFirstGeoObject(coords, "locality").then(function (firstGeoObject) {
        let city = firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas()
        document.getElementById("city").value = city
        geoObjectsCash.set(city[0], firstGeoObject)
        document.getElementById("city").style.background = '#EEE8AA'
        document.getElementById("submit").disabled = false
        userPlacemark.properties.set({
                iconCaption: city,
                balloonContent: firstGeoObject.getAddressLine()
            });
    })
}

function getAddress(coords) {
    getFirstGeoObject(coords,'house').then(function (firstGeoObject) {
        let address = firstGeoObject.getAddressLine()
        document.getElementById("address").value = address
        geoObjectsCash.set(address, firstGeoObject)
        document.getElementById("address").style.background = '#EEE8AA'

        userPlacemark.properties.set({
            iconCaption: firstGeoObject.getAddressLine(),
            balloonContent: firstGeoObject.getAddressLine()
        });
    })
}
function setPlacemark(coords) {
    if (userPlacemark) {
        userPlacemark.geometry.setCoordinates(coords);
    } else {
        userPlacemark = createPlacemark(coords);
        map.geoObjects.add(userPlacemark);
        // Слушаем событие окончания перетаскивания на метке.
        //userPlacemark.events.add('dragend', function () {
        //getAddress(userPlacemark.geometry.getCoordinates());
        //});
    }
    if (document.querySelector("input[name = role]:checked").value == "seller"){
        getAddress(coords);
    } else {
        getCity(coords)
    }
}
function init() {
    let center = [55.753994, 37.622093];
    map = new window.ymaps.Map('map', {
        center: center,
        zoom: 10
    });


    map.events.add('click', function (e) {
        let coords = e.get('coords');
        setPlacemark(coords)
    });
}

window.ymaps.ready(init)