package baldin.seb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Board {
    private ArrayList<User> users;
    private ArrayList<Message> messages;

    public Board() {
        users = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public void printUsers() {
        for (int x = 0; x < users.size(); x++) {
            if (x % 5 == 0) System.out.print("\n");
            System.out.print((x + 1) + ". " + users.get(x).getUsername() + "\t");
        }
    }

    public void printMessages() {
        /*messages.sort((o1, o2) -> {
            return o1.getData().compareTo(o2.getData());
        });*/
        for (int x = 0; x < messages.size(); x++) {
            System.out.println(messages.get(x).getSender().getUsername() + " manda " + messages.get(x).getBody());
        }
    }

    public void addNewUser(String username) {
        if (isUnique(username)) {
            users.add(new User(username));
            System.out.println("Utente " + username + " aggiunto correttamente.");
        } else
            System.out.println("Il nuovo utente con username " + username + " non puo' essere creato.\nL'username e' gia' occupato.");
    }

    public void renameUser(String username, String newUsername) {
        if (isUnique(newUsername)) {
            getUserFromUsername(username).setUsername(newUsername);
            System.out.println("Utente " + username + " rinominato in " + newUsername + ".");
        } else System.out.println("Username " + newUsername + " non disponibile.");
    }

    public void sendMessage(String date, String username, String message) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            messages.add(new Message(dateFormatter.parse(date), message, getUserFromUsername(username)));
            System.out.println("Messaggio inviato da " + getUserFromUsername(username).getUsername());
        } catch (ParseException e) {
            System.out.println("Errore nella parserizzazione della data.");
        }
    }

    public ArrayList<Message> getMessageForUser(String username, int num) {
        User user = getUserFromUsername(username);
        ArrayList<Followed> followeds = user.getUserFollowed();
        ArrayList<Message> list = new ArrayList<>();
        for (int x = 0; x < followeds.size(); x++) {
            for (int y = 0; y < messages.size(); y++) {
                if (!followeds.get(x).isMuted() && messages.get(y).getSender() == followeds.get(x).getUser() && followeds.get(x).getFollowedFrom().before(messages.get(y).getData())) {
                    list.add(messages.get(y));
                }
            }
        }
        list.sort((o1, o2) -> {
            return (o1.getData().compareTo(o2.getData())) * -1;
        });
        ArrayList<Message> list2 = new ArrayList<>();
        for (int x = 0; x < num && x < list.size(); x++) {
            list2.add(list.get(x));
        }
        return list2;
    }

    /*public ArrayList<Message> getMessageForUserLambda(String username, int num) {
        User user = getUserFromUsername(username);
        ArrayList<Followed> followeds = user.getUserFollowed();
        ArrayList<Message> list = new ArrayList<>();
        ArrayList<Message> list2 = new ArrayList<>();
        for (int x = 0; x < followeds.size(); x++) {
            for (int y = 0; y < messages.size(); y++) {
                if (!followeds.get(x).isMuted() && messages.get(y).getSender() == followeds.get(x).getUser() && followeds.get(x).getFollowedFrom().before(messages.get(y).getData())) {
                    list.add(messages.get(y));
                }
            }
        }
        messages.stream()
                .sorted((o1, o2) -> { return (o1.getData().compareTo(o2.getData())) * -1; })
                .filter(m -> {
                    return followeds.stream()
                            .filter(f -> !f.isMuted() && f.getFollowedFrom().before(m.getData()))
                            .map(f -> f.getUser())
                            .;
                })
        list.stream()
                .sorted((o1, o2) -> { return (o1.getData().compareTo(o2.getData())) * -1; })
                .limit(num)
                .forEach(m -> list2.add(m));
        return list2;
    }*/

    public ArrayList<Message> getMessageForUser(String username) {
        User user = getUserFromUsername(username);
        ArrayList<Followed> followeds = user.getUserFollowed();
        ArrayList<Message> list = new ArrayList<>();
        for (int x = 0; x < followeds.size(); x++) {
            for (int y = 0; y < messages.size(); y++) {
                if (!followeds.get(x).isMuted() && messages.get(y).getSender() == followeds.get(x).getUser() && followeds.get(x).getFollowedFrom().before(messages.get(y).getData())) {
                    list.add(messages.get(y));
                }
            }
        }
        list.sort((o1, o2) -> {
            return (o1.getData().compareTo(o2.getData())) * -1;
        });
        return list;
    }

    public ArrayList<Message> getUserMessage(String username) {
        User user = getUserFromUsername(username);
        ArrayList<Message> list = new ArrayList<>();
        for (Message m : messages) {
            if (m.getSender() == user) list.add(m);
        }
        return list;
    }

    public ArrayList<Message> getUserMessage(String username, int num) {
        User user = getUserFromUsername(username);
        ArrayList<Message> list = new ArrayList<>();
        for (int x = 0; x < messages.size(); x++) {
            if (messages.get(x).getSender() == user) list.add(messages.get(x));
        }
        list.sort((o1, o2) -> {
            return (o1.getData().compareTo(o2.getData())) * -1;
        });
        ArrayList<Message> list2 = new ArrayList<>();
        for (int x = 0; x < num && x < list.size(); x++) {
            list2.add(list.get(x));
        }
        return list2;
    }

    public ArrayList<Message> getUserMessageLambda(String username, int num) {
        User user = getUserFromUsername(username);
        ArrayList<Message> list = new ArrayList<>();
        messages.stream()
                .filter(m -> m.getSender() == user)
                .sorted((o1, o2) -> { return (o1.getData().compareTo(o2.getData())) * -1; })
                .limit(num)
                .forEach(m -> list.add(m));
        return list;
    }


        public void follow(String date, String person, String toFollow) {
        User one = getUserFromUsername(person);
        User two = getUserFromUsername(toFollow);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (one.follow(two, dateFormatter.parse(date))) {
                System.out.println("L'utente " + one.getUsername() + " segue " + two.getUsername());
            } else {
                System.out.println("Non è stata eseguita alcuna operazione.\nForse l'utente lo seguiva gia' o uno dei due non esiste.");
            }
        } catch (ParseException e) {
            System.out.println("Errore nella parserizzazione della data.");
        }

    }

    public void unfollow(String person, String toUnFollow) {
        User one = getUserFromUsername(person);
        User two = getUserFromUsername(toUnFollow);
        if (one.unfollow(two)) {
            System.out.println("L'utente " + one.getUsername() + " non segue piu' " + two.getUsername());
        } else {
            System.out.println("Non è stata eseguita alcuna operazione.\nForse l'utente non seguiva l'altro o uno dei due non esiste.");
        }
        System.out.println("Utente " + one.getUsername() + " non segue più " + two.getUsername());
    }

    public void setMuted(String user, String personToMute) {
        if (getUserFromUsername(user).muteFollowed(getUserFromUsername(personToMute))) {
            System.out.println("L'utente " + user + " ha mutato " + personToMute + ".");
        } else {
            System.out.println("L'utente " + user + " non ha mutato " + personToMute + ".\nForse l'utente è già stato mutato o non esiste.");
        }
    }

    public void setUnmuted(String user, String personToUnmute) {
        if (getUserFromUsername(user).unmuteFollowed(getUserFromUsername(personToUnmute))) {
            System.out.println("L'utente " + user + " ha riattivato i messaggi per l'utente " + personToUnmute + ".");
        } else {
            System.out.println("L'utente " + user + " non ha riattivato i messaggi per l'utente " + personToUnmute + ", forse li aveva già attivi.");
        }
    }

    public User getUserFromUsername(String username) {
        User user = null;
        for (User us : users) {
            if (us.getUsername().equals(username)) {
                user = us;
                break;
            }
        }
        return user;
    }

    private boolean isUnique(String username) {
        boolean isUnique = true;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    public void removeExistingUser() {

    }
}
