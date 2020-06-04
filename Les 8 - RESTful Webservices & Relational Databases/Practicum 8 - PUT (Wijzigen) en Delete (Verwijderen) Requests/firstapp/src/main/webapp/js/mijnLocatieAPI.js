function initPage() {
    fetch('https://ipapi.co/json/')
    .then(response => response.json())
    .then((myJson) => {
        document.querySelector('#landcode').append(myJson.country);
        document.querySelector('#land').append(myJson.country_name);
        document.querySelector('#regio').append(myJson.region);
        document.querySelector('#stad').append(myJson.city);
        document.querySelector('#postcode').append(myJson.country_name);
        document.querySelector('#latitude').append(myJson.latitude);
        document.querySelector('#longitude').append(myJson.longitude);
        document.querySelector('#ip').append(myJson.ip);

        showWeather(myJson.latitude, myJson.longitude, myJson.city);
        loadCountries();
    });
}

document.querySelector('#myLocation').addEventListener('click', (event) => {
    let stad = document.querySelector('#stad').textContent;
    let lat = document.querySelector('#latitude').textContent;
    let long = document.querySelector('#longitude').textContent;
    showWeather(lat.slice(10, lat.length), long.slice(11, long.length), stad.slice(6, stad.length));
});



// initPage();