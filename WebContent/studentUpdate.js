function main() {
	var queryString = decodeURIComponent(window.location.search);
	queryString = queryString.substring(1);
	getDataFromServer("http://localhost:8080/WebAppExercises/updateStudent?"+queryString, printStudent);
}

function printStudent(student) {
	 var form = document.forms["formStudent"];
	 form["studentId"].disabled = true;
	 form["studentId"].value = student.id;
	 form["lName"].value = student.lName;
	 form["fName"].value = student.fName;
	 form["address"].value = student.address;
	 form["postCode"].value = student.postCode;
	 form["postOffice"].value = student.postOffice;
}

function updateStudent() {
	 var url = "http://localhost:8080/WebAppExercises/updateStudent";
	 
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
		 alert("Student data updated!");
	 } else if (status.errorCode === 1) {
		 alert("Cannot update the student.*");
	 } else if (status.errorCode === -1) {
		 alert("The database is temporarily unavailable. Please try again later.");
	 }
	 goToMainPage();
}

main();

