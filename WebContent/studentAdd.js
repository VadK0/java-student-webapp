function main() {
	 var url = "http://localhost:8080/WebAppExercises/addStudent";
	 
	 var form = document.forms["formStudent"];
	 var parameterData =
		 "studentId=" + form["studentId"].value + "&lastName=" + form["lName"].value +
		 "&firstName=" + form["fName"].value + 	"&address=" + form["address"].value +
		 "&postCode=" + form["postCode"].value + "&postOffice=" + form["postOffice"].value; 
	 
	 var requestOptions = { 
		 method: "POST",
		 headers: {"Content-Type": "application/x-www-form-urlencoded"},
		 body: parameterData
	 };
	 
	 postDataToServer(url, requestOptions, processResponseStatus);
}

function goToMainPage() {
	location.replace("studentListImproved.html");
}

function processResponseStatus(status) { 
	 if (status.errorCode === 0) {
		 alert("Student data added!");
	 } else if (status.errorCode === 1) {
		 alert("Cannot add the student. The id is already in use!");
	 } else if (status.errorCode === -1) {
		 alert("The database is temporarily unavailable. Please try again later.");
	 }
	 goToMainPage();
}


