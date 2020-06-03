function degToCompass(num) {
    const val =  Math.floor((num / 45) + 0.5);
    const arr = ["N","NE","E", "SE","S","SW","W","NW"];
    return arr[(val % 8)]
}

function showWeather(latitude, longitude, city) {
    document.querySelector('#weatherInfo_city').innerHTML = city;
    let item = window.localStorage.getItem("weatherInfo_" + city);
    if (item != null) {
        item = JSON.parse(item);	
    }
    
    console.log(item);
    console.log(new Date());
    
    if (item == null || new Date() > new Date(item.set_on)) {
        console.log("if");
        
        fetch('https://api.openweathermap.org/data/2.5/weather?lat=' + latitude + '&lon=' + longitude + '&APPID=00fb0158113e4b223c8064f484b43ac2&units=Metric')
        .then(response => response.json())
        .then((myJson) => {				
            document.querySelector('#weatherInfo_temperatuur').innerText = "Temperatuur: " + myJson.main.temp;
            document.querySelector('#weatherInfo_luchtvochtigheid').innerText = "Luchtvochtigheid: " + myJson.main.humidity;
            document.querySelector('#weatherInfo_windsnelheid').innerText = "Windsnelheid: " + myJson.wind.speed;
            document.querySelector('#weatherInfo_windrichting').innerText = "Windrichting: " + degToCompass(myJson.wind.deg);

            let date = new Date(myJson.sys.sunrise * 1000);
            date.setHours(date.getHours());
            sunrise = date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();

            let date2 = new Date(myJson.sys.sunset * 1000);
            date2.setHours(date2.getHours());
            sunset = date2.getHours() + ':' + date2.getMinutes() + ':' + date2.getSeconds();

            document.querySelector('#weatherInfo_zonsopgang').innerText = "Zonsopgang: " + sunrise;
            document.querySelector('#weatherInfo_zondsondergang').innerText = "Zonsondergang: " + sunset;
            
            let datum = new Date();
            let data = {
                    temp: myJson.main.temp,
                    humidity: myJson.main.humidity,
                    speed: myJson.wind.speed,
                    compass: degToCompass(myJson.wind.deg),
                    sunrise: sunrise,
                    sunset: sunset,
                    city: city,
                    set_on: datum.getTime() + 10*60000
                };
            window.localStorage.setItem("weatherInfo_" + city, JSON.stringify(data));
            console.log(window.localStorage);
            console.log(window.localStorage.getItem("xxx"));
        })
    } else {
        console.log("else");
        console.log(item);
        console.log(new Date(item.set_on));
        
        document.querySelector('#weatherInfo_temperatuur').innerText = "Temperatuur: " + item.temp;
        document.querySelector('#weatherInfo_luchtvochtigheid').innerText = "Luchtvochtigheid: " + item.humidity;
        document.querySelector('#weatherInfo_windsnelheid').innerText = "Windsnelheid: " + item.speed;
        document.querySelector('#weatherInfo_windrichting').innerText = "Windrichting: " + item.compass;
        document.querySelector('#weatherInfo_zonsopgang').innerText = "Zonsopgang: " + item.sunrise;
        document.querySelector('#weatherInfo_zondsondergang').innerText = "Zonsondergang: " + item.sunset;
    }
}