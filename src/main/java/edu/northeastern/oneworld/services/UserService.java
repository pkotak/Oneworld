package edu.northeastern.oneworld.services;

import java.util.Optional;

import com.google.gson.Gson;
import edu.northeastern.oneworld.models.*;
import edu.northeastern.oneworld.repositories.UserLikeRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.northeastern.oneworld.repositories.TripRepository;
import edu.northeastern.oneworld.repositories.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:63342", "http://localhost:63343"})
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserLikeRepository userLikeRepository;
    /**
     * Method to create a new user
     *
     * @param user user object
     * @return user
     */
    @PostMapping("/api/user/register")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Method to create a new user
     *
     * @param json
     * @return int
     */
    @PostMapping("/api/user/login")
    public int loginUser(@RequestBody String json) {
        JSONObject object = new JSONObject(json);
        String username = object.getString("username");
        String password = object.getString("password");
        if (userRepository.findUserByCredentials(username, password).iterator().hasNext() == true){
            if(userRepository.isUserAdmin(username)){
                return 1;
            }
                return 2;
        }
        else
            return 0;
    }
    /**
     * Method to find all people
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/api/user")
    public Iterable<User> findAllPeople(@RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "password", required = false) String password) {
        if (username != null && password != null)
            return userRepository.findUserByCredentials(username, password);
        else if (username != null)
            return userRepository.findUserByUsername(username);
        else
            return userRepository.findAll();
    }

    /**
     * Find a specific user
     *
     * @param id
     * @return
     */
    @GetMapping("/api/user/{userId}")
    public Optional<User> findUserById(@PathVariable("userId") int id) {
        return userRepository.findById(id);
    }


    /**
     * Find a specific user's reviews
     *
     * @param id
     * @return
     */
    @GetMapping("/api/user/{userId}/review")
    public Iterable<Review> findReviewForUser(@PathVariable("userId") int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user.getReviews();
        }
        return null;
    }
    /**
     * @param id
     * @param newuser
     * @return
     */
    @PutMapping("/api/user/{userId}")
    public User updateuser(@PathVariable("userId") int id, @RequestBody User newuser) {
        Optional<User> optionaluser = userRepository.findById(id);
        if (optionaluser.isPresent()) {
            User user = optionaluser.get();
            user.set(newuser);
            return userRepository.save(user);
        } else
            return null;
    }

    /**
     * @param id
     */
    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }


    @PostMapping("/api/user/review/like")
    public UserLike likeReview(@RequestBody String json){
        Gson g = new Gson();
        UserLike userLike = g.fromJson(json, UserLike.class);
        return userLikeRepository.save(userLike);
    }

    @DeleteMapping("/api/user/review/like/{lId}")
    public void unlikeReview(@PathVariable("lId") int id){
        userLikeRepository.deleteById(id);
    }

}
