package by.htp.ex.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String password;
	private String email;
	private String role;
	private List<News> listOfNews;
	
	public User() {
	}

	public User(String role, Integer userId) {
		this.role = role;
		this.userId = userId;
	}


	public User(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<News> getListOfNews() {
		return listOfNews;
	}

	public void setListOfNews(List<News> listOfNews) {
		this.listOfNews = listOfNews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, listOfNews, password, role, userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(listOfNews, other.listOfNews)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", role=" + role + ", listOfNews=" + listOfNews + "]";
	}
	
	
	
	
	
}
