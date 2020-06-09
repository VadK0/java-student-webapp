function main() {
	getDataFromServer("http://localhost:8080/WebAppExercises/students", printStudentsTable);
}

function printStudentsTable(studentList) {
	for (var student of studentList) {

		var x = document.createElement("TR");
		var y = document.createElement("TD");
		var studentId = student.id;
		
		y.innerHTML = studentId;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = student.lName;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = student.fName;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = student.address;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = student.postCode;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = student.postOffice;
		x.appendChild(y);
		y = document.createElement("TD");
		y.innerHTML = createUpdateDeleteLinks(studentId);
		x.appendChild(y);
		
		document.getElementById("myTBody").appendChild(x);
	}
	
}

function studentAdd() {
	location.replace("studentAdd.html");
}

function deleteStudent(studentId) {
	var r = confirm("Delete student " + studentId + "?");
	if (r == true) {
		
		var url = "http://localhost:8080/WebAppExercises/deleteStudent";
		 
		var parameterData = "studentId=" + studentId; 
		 
		var requestOptions = { 
			method: "POST",
			headers: {"Content-Type": "application/x-www-form-urlencoded"},
			body: parameterData
		};
		 
		postDataToServer(url, requestOptions, processResponseStatus);	
	}
	location.reload();
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

function updateStudent(studentId) {
	location.replace("studentUpdate.html?studentId=" + studentId);
}

function createUpdateDeleteLinks(studentId) {
	 return "<span class='link' onclick='updateStudent(" + studentId + ");'>Update</span>" +
	 "&nbsp;&nbsp;" +
	 "<span class='link' onclick='deleteStudent(" + studentId + ");'>Delete</span>";
	}


main();