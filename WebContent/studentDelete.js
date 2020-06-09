function main() {
	 var url = "http://localhost:8080/WebAppExercises/deleteStudent";
	 
	 var parameterData = "studentId=" + document.getElementById("studentId").value; 
	 
	 var requestOptions = { 
		 method: "POST",
		 headers: {"Content-Type": "application/x-www-form-urlencoded"},
		 body: parameterData
	 };
	 
	 postDataToServer(url, requestOptions, processResponseStatus);
}

function processResponseStatus(status) { 
	 if (status.errorCode === 0) {
		 alert("Student data deleted!");
	 } else if (status.errorCode === 1) {
		 alert("Student data not deleted. Unknown student id!");
	 } else if (status.errorCode === -1) {
		 alert("The database is temporarily unavailable. Please try again later.");
	 }
}


