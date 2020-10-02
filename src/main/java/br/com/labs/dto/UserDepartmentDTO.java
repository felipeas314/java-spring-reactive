package br.com.labs.dto;

import br.com.labs.model.User;

public class UserDepartmentDTO {

	private Integer userId;
	private String userName;
	private int age;
	private double salary;
	private Integer departmentId;
	private String departmentName;
	private String loc;

	public UserDepartmentDTO(UserDepartmentDTOBuilder userDepartmentDTOBuilder) {
		super();
		this.userId = userDepartmentDTOBuilder.userId;
		this.userName = userDepartmentDTOBuilder.userName;
		this.age = userDepartmentDTOBuilder.age;
		this.salary = userDepartmentDTOBuilder.salary;
		this.departmentId = userDepartmentDTOBuilder.departmentId;
		this.departmentName = userDepartmentDTOBuilder.departmentName;
		this.loc = userDepartmentDTOBuilder.loc;
	}
	
	public UserDepartmentDTO() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public  UserDepartmentDTO builder() {
		return new UserDepartmentDTO();
	}
	
	public UserDepartmentDTO age(int i) {
//		userDepartmentDTO.setAge(age);
		return this;
	}
	
	public static class UserDepartmentDTOBuilder{
		
		private Integer userId;
		private String userName;
		private int age;
		private double salary;
		private Integer departmentId;
		private String departmentName;
		private String loc;
		
		public UserDepartmentDTOBuilder userId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public UserDepartmentDTOBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}
		
		public UserDepartmentDTOBuilder age(int age) {
			this.age = age;
			return this;
		}
		
		
		
		public UserDepartmentDTO build() {
			UserDepartmentDTO userDepartmentDTO = new UserDepartmentDTO(this);
			return userDepartmentDTO;
		}
	}

}
