package edu.kh.emp.run;

import edu.kh.emp.view.EmployeeView;

public class EmployeeRun {
//실행용 클래스 
	//Run - View -(Service) - DAO - VO 순으로~ 
	public static void main(String[] args) {
		
		new EmployeeView().displayMenu();

	}

}
