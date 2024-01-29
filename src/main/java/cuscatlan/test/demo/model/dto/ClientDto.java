package cuscatlan.test.demo.model.dto;

public class ClientDto {

	private Long clientId;
	private String name;
	private String lastName;
	private String email;
	private String address;
	private String birthDate;
	private Integer status;
	private String password;
	private String role;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ClientDto(Long clientId, String name, String lastName, String email, String address, String birthDate,
			Integer status, String password, String role) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
		this.status = status;
		this.password = password;
		this.role = role;
	}

	public ClientDto() {
		super();
	}

}
