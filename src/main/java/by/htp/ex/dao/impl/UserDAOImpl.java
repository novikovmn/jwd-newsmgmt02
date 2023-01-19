package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.dao.cpool.ConnectionPool;
import by.htp.ex.dao.cpool.exception.ConnectionPoolException;
import by.htp.ex.dao.exception.UserDAOException;

public class UserDAOImpl implements UserDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private final String LOGINATION_SQL = "SELECT * FROM user WHERE user_name = ? AND password = ?";

	@Override
	public User logination(String login, String password) throws UserDAOException {

		User user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(LOGINATION_SQL);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String role = resultSet.getString("role");
				int userId = resultSet.getInt("user_id"); 
				user = new User(role, userId);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new UserDAOException("Logination is not possible now. Please, try again.");
		}finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;

	}

	private final String REGISTRATION_SQL = "INSERT INTO user (user_name, password, email) VALUES (?, ?, ?)";

	@Override
	public boolean registration(User user) throws UserDAOException {

		if (checkUser(user)) {
			// if the user is not in db - put him to db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {				
				connection = connectionPool.takeConnection();
				preparedStatement = connection.prepareStatement(REGISTRATION_SQL);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmail());				
				int rows = preparedStatement.executeUpdate();				
				
				if (rows > 0) {
					return true;
				} else {
					return false;
				}				
				
			} catch (ConnectionPoolException | SQLException e) {
				throw new UserDAOException("Registration is not possible now. Please, try again.", e);
			}finally {
				connectionPool.closeConnection(connection, preparedStatement);
			}

		} else {
			return false;
		}

	}

	// helper-method for registration procedure
	// goal -> to check if the user missing or not in database
	// if not -> registration method put him to db
	private final String CHECK_USER_SQL = "SELECT * FROM user WHERE user_name = ? AND email = ?";

	private boolean checkUser(User user) throws UserDAOException {

		boolean isUserPresentInDatabase = false;

		// we will check user by this two fields
		String userName = user.getUserName();
		String email = user.getEmail();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(CHECK_USER_SQL);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, email);

			resultSet = preparedStatement.executeQuery();			

			if (resultSet.next()) {				
				return isUserPresentInDatabase;
			} else {				
				isUserPresentInDatabase = true;
				return isUserPresentInDatabase;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new UserDAOException("Can't check the user. Try later.", e);
		}finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	private final String GET_ROLE_SQL = "SELECT * FROM user WHERE user_name = ?";

	@Override
	public String getRole(User user) throws UserDAOException {
		// fetch user's role by his user_name
		String role = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {			
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_ROLE_SQL);
			preparedStatement.setString(1, user.getUserName());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				role = resultSet.getString("role");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new UserDAOException("Can't fetch the role. Try later.", e);
		}finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}

		return role;
	}

}
