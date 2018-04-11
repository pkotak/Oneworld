package edu.northeastern.oneworld.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.oneworld.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	/**
	 * Method to find user by username
	 * @param u
	 * @return Person
	 */
	@Query("SELECT u FROM Person u WHERE u.username=:username")
	Iterable<User> findUserByUsername(@Param("username") String u);

	/**
	 * Method to find user by credentials
	 * @param username
	 * @param password
	 * @return Person
	 */
	@Query("SELECT u FROM Person u WHERE u.username=:username AND u.password=:password")
	Iterable<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);
}