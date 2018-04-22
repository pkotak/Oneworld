package edu.northeastern.oneworld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * User Representation
 *
 */
@Entity
public class User extends Person implements Serializable {

	@OneToMany(mappedBy = "user")
	private List<UserLike> likes;
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "UsersInTrips")
	@JsonIgnore
	private List<Trip> trips;
	private static final long serialVersionUID = 1L;

	public User(List<UserLike> likes, List<Review> reviews, List<Trip> trips) {
		super();
		this.likes = likes;
		this.reviews = reviews;
		this.trips = trips;
	}

	public User() {
		super();
	}

	public List<UserLike> getLikes() {
		return likes;
	}

	public void setLikes(List<UserLike> likes) {
		this.likes = likes;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public void addToTrip(Trip trip){
		this.trips.add(trip);
		if(! trip.getUser().contains(this))
			trip.getUser().add(this);
	}

	public void set(User newUser) {
		this.setUsername(newUser.getUsername() != null ? newUser.getUsername() : this.getUsername());
		this.setPassword(newUser.getPassword() != null ? newUser.getPassword() : this.getPassword());
		this.setFirstName(newUser.getFirstName() != null ? newUser.getFirstName() : this.getFirstName());
		this.setLastName(newUser.getLastName() != null ? newUser.getLastName() : this.getLastName());
		this.setDob(newUser.getDob() != null ? newUser.getDob() : this.getDob());
		this.setEmail(newUser.getEmail() != null ? newUser.getEmail() : this.getEmail());
		this.setAddress(newUser.getAddress() != null ? newUser.getAddress() : this.getAddress());
		this.setPhoneNumber(newUser.getPhoneNumber() != null ? newUser.getPhoneNumber() : this.getPhoneNumber());
		this.setTrips(newUser.getTrips() != null ? newUser.getTrips() : this.getTrips());
		this.setLikes(newUser.getLikes() != null ? newUser.getLikes() : this.getLikes());
	}

	@Override
	public String toString() {
		return "User [likes=" + likes + ", reviews=" + reviews + ", trips=" + trips
				+ "]";
	}

}
