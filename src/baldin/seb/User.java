package baldin.seb;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String username;
    private ArrayList<Followed> userFollowed;

    public User(String username) {
        this.username = username;
        userFollowed = new ArrayList<>();
    }

    public boolean follow(User user, Date date) {
        boolean alreadyAdded = false;
        if (user != this) {
            for (int x = 0; x < userFollowed.size(); x++) {
                if (userFollowed.get(x).getUser() == user) {
                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) userFollowed.add(new Followed(user, date));
        }
        return !alreadyAdded;
    }

    public boolean unfollow(User user) {
        boolean done = false;
        for (int x = 0; x < userFollowed.size(); x++) {
            if (userFollowed.get(x).getUser() == user) {
                userFollowed.remove(x);
                done = true;
                break;
            }
        }
        return done;
    }

    public boolean muteFollowed(User user) {
        boolean done = false;
        for (int x = 0; x < userFollowed.size(); x++) {
            if (userFollowed.get(x).getUser() == user) {
                userFollowed.get(x).setMuted(true);
                done = true;
                break;
            }
        }
        return done;
    }

    public boolean unmuteFollowed(User user) {
        boolean done = false;
        for (int x = 0; x < userFollowed.size(); x++) {
            if (userFollowed.get(x).getUser() == user) {
                userFollowed.get(x).setMuted(false);
                done = true;
                break;
            }
        }
        return done;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Followed> getUserFollowed() {
        return userFollowed;
    }
}
