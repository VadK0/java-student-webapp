function getDataFromServer(urlAddress, callFunc) {
	fetch(urlAddress)
	.then(response => {
		if (response.ok) {
			return response.json();
		} else {
			throw "HTTP status code is " + response.status;
		}
	})
	.then(studentList => callFunc(studentList))
	.catch(errorText => alert("getDataFromServer failed: " + errorText));
}

function postDataToServer(url, requestOptions, callFunc) {
	fetch(url, requestOptions) 
	.then(response => {
		if (response.ok) {
			return response.json();
		} else {
			throw "HTTP status code is " + response.status;
		}
	})
	.then(json => callFunc(json))
	.catch(errorText => console.error("postDataToServer failed: " + errorText)); 
}