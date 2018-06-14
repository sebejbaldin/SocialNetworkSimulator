package baldin.seb;

import java.util.Date;

public class Followed {
    private User user;
    private boolean muted;
    private Date followedFrom;

    public Followed(User user) {
        this.user = user;
        followedFrom = new Date();
        muted = false;
    }

    public Followed(User user, Date followedFrom) {
        this.user = user;
        this.followedFrom = followedFrom;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public User getUser() {
        return user;
    }

    public boolean isMuted() {
        return muted;
    }

    public Date getFollowedFrom() {
        return followedFrom;
    }
}
