package model;

public class Student {
	private int id;
	private String fName;
	private String lName;
	private String address;
	private String postCode;
	private String postOffice;
	
	public Student() {
		id = -1;
		fName = "";
		lName = "";
		address = "";
		postCode = "";
		postOffice = "";
	}
	
	public Student(int id, String fName, String lName, String address, String postCode, String postOffice) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.postCode = postCode;
		this.postOffice = postOffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", fName=" + fName + ", lName=" + lName + ", address=" + address + ", postCode="
				+ postCode + ", postOffice=" + postOffice + "]";
	}
	
	
}
