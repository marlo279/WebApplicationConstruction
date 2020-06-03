var countries = document.getElementsByClassName('country_list'); 

function loadCountries() {
    fetch('http://localhost:8080/firstapp/restservices/countries')
        .then(response => response.json())
        .then((myJson) => {
            let table = document.querySelector('tbody');

            var naam = myJson[2].name;
            console.log(naam);
           
            var i = 0;
            for (i; i < myJson.length; i++) {
                table.innerHTML = table.innerHTML + "<tr class=" + "country_list" + " data-lang='" + myJson[i].latitude + "' data-long='" + myJson[i].longitude + "' data-land='" + myJson[i].capital +"'>" +
                    "<td>" + myJson[i].name + "</td>" +
                    "<td>" + myJson[i].capital + "</td>" +
                    "<td>" + myJson[i].region + "</td>" +
                    "<td>" + myJson[i].surface + "</td>" +
                    "<td>" + myJson[i].population + "</td>" +
                "</tr>";
                
            }

            
            var landen = document.getElementsByClassName('country_list');
            
            for (i = 0; i < landen.length; i++) {
                landen[i].addEventListener('click', (event) => {
                    let land = event.target.closest(".country_list");
                    showWeather(land.getAttribute("data-lang"), land.getAttribute("data-long"), land.getAttribute("data-land"));
                }, false);
            }
    });

    
}









window.onload = function() {
    loadCountries();
}