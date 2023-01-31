package edu.kh.jdbc1.model.vo;

public class Employee2 {
	private String empName;
	private String hireDate;
	private String gender;
	
	public Employee2() {}

	public Employee2(String empName, String hireDate, String gender) {
		super();
		this.empName = empName;
		this.hireDate = hireDate;
		this.gender = gender;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return empName + " / " + hireDate + " / " + gender ;
	}
	
	
	


	
	
}
