function main() {
	
	fetch("http://localhost:8080/WebAppExercises/students")
		.then(response => response.json())
		.then(studentList => printStudentsTable(studentList));
}

function printStudents(studentList) {
	for (var student of studentList) {
		console.log(student.lName);
	}
}

function printStudentsTable(studentList) {
	for (var student of studentList) {

		var x = document.createElement("TR");
		var y = document.createElement("TD");
		y.innerHTML = student.id;
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
		
		document.getElementById("myTBody").appendChild(x);
	}
	
}


main();