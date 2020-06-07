var countries = document.getElementsByClassName('country_list'); 

function loadCountries() {
	let fetchoptions = {
		method: 'GET',
		headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}

	}
    fetch('http://localhost:8080/firstapp/restservices/countries', fetchoptions)
        .then(response => response.json())
        .then((myJson) => {
            let table = document.querySelector('tbody');
           
            var i = 0;
            for (i; i < myJson.length; i++) {       
                table.innerHTML = table.innerHTML + "<tr class=" + "country_list " + "id='country_list_" + myJson[i].code  + "' data-lang='" + myJson[i].latitude + "' data-long='" + myJson[i].longitude + "' data-land='" + myJson[i].capital +"'>" +
                    "<td>" + myJson[i].code + "</td>" +
					"<td>" + "<input type='text' name='name' value='" + myJson[i].name + "' />" + "</td>" +
					"<td>" + "<input type='text' name='capital' value='" + myJson[i].capital + "' />" + "</td>" +
					"<td>" + "<input type='number' name='surface' value='" + myJson[i].surface + "' />" + "</td>" +
					"<td>" + "<input type='number' name='population' value='" + myJson[i].population + "' />" + "</td>" +
					"<td>" + "<input type='button' class='update' data-code='" + myJson[i].code + "' value='Bewerk'/>" + "</td>" +
					"<td>" + "<input type='button' class='delete' data-code='" + myJson[i].code + "' value='Verwijder'/></td>" +
                "</tr>";
                
            }

            var deleteButtons = document.getElementsByClassName('delete');
			for (i = 0; i < deleteButtons.length; i++) {
				deleteButtons[i].addEventListener('click', (event) => {
					let deletebutton = event.target.closest(".delete");
					deleteLand(deletebutton.getAttribute("data-code"));
				}, false);
			}
			
			var updateBunttons = document.getElementsByClassName('update');
			for (i = 0; i < updateBunttons.length; i++) {
				updateBunttons[i].addEventListener('click', (event) => {
					let updateBuntton = event.target.closest(".update");
					updateLand(updateBuntton);
				}, false);
			}  

            
            // var landen = document.getElementsByClassName('country_list');
            
            // for (i = 0; i < landen.length; i++) {
            //     landen[i].addEventListener('click', (event) => {
            //         let land = event.target.closest(".country_list");
            //         showWeather(land.getAttribute("data-lang"), land.getAttribute("data-long"), land.getAttribute("data-land"));
            //     }, false);
//            }
    });

    
}



function updateLand(clickedButton) {
	var code = clickedButton.getAttribute("data-code");
	var row = document.querySelector('#country_list_' + code);
	let sur = row.querySelector('input[name="surface"]').value;
	let pop = row.querySelector('input[name="population"]').value;
	
	let data = {
		name: row.querySelector('input[name="name"]').value,
		capital: row.querySelector('input[name="capital"]').value,
		surface: Number.parseInt(sur),
		population: Number.parseInt(pop)
	};
	
	
	
	var encData = new URLSearchParams(data);
	console.log(encData);
	
	document.querySelector("#error").innerHTML = "";
	let fetchoptions = {
		method: 'PUT',
		body: encData,
		headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}
	}
	fetch("http://localhost:8080/firstapp/restservices/countries/" + code, fetchoptions)
		.then((myJson) => {
			if (myJson.status == 403) {
				document.querySelector("#error").innerHTML = "Log in om deze actie te doen.";
			} else {
				console.log(myJson);
			}
		});
}

function deleteLand(code) {
	document.querySelector("#error").innerHTML = "";
	let fetchoptions = {
		method: 'DELETE',
		headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}
	}
	
	fetch('http://localhost:8080/firstapp/restservices/countries/' + code, fetchoptions)
		.then((response) => {
			if (response.status == 403) {
				document.querySelector("#error").innerHTML = "Log in om deze actie te doen.";
			} else {
				initPage();
				console.log("deleted");
			}
		});
}

function store() {	
	var formData = new FormData(document.querySelector("#save_form"));
	var encData = new URLSearchParams(formData.entries());
	document.querySelector("#error").innerHTML = "";
	
	let fetchoptions = {
		method: 'POST',
		body: encData,
		headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}
	}
	
	fetch('http://localhost:8080/firstapp/restservices/countries/', fetchoptions)
	.then((response) => {
		console.log(response.status);
		if (response.status == 403) {
			document.querySelector("#error").innerHTML = "Log in om deze actie te doen.";
		} else if (response.status == 402) { 
			document.querySelector("#error").innerHTML = "er is iets fout gegaan.";
			console.log("error2");
			document.querySelector("#error").innerHTML = "Er is iets fout gegaan.";
		} else {
			initPage();
		}
	});
	
	return false;
}









window.onload = function() {
    loadCountries();
}