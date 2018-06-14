package baldin.seb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TestGenerator {
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Random rand = new Random();
    private ArrayList<String> arguments = new ArrayList<>();

    private void printFile(String fileName, ArrayList<String> lines) {
        File logFile = new File(fileName);
        arguments.add(fileName + " ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genAFile(int lines, String fileName) {
        ArrayList<String> lineList = new ArrayList<>(lines);
        Date date;
        for (int x = 0; x < lines; x++) {
            date = new Date(rand.nextLong());
            switch (rand.nextInt(4)) {
                case 0:
                    lineList.add(dateFormatter.format(date) + "\t" + names[rand.nextInt(names.length)] + "\t" + operands[0]);
                    break;
                default:
                    lineList.add(dateFormatter.format(date) + "\t" + names[rand.nextInt(names.length)] + "\t" + operands[rand.nextInt(operands.length)] + "\t" + names[rand.nextInt(names.length)]);
            }
        }
        printFile(fileName, lineList);
    }

    public void genMFile(int lines, String fileName) {
        ArrayList<String> lineList = new ArrayList<>(lines);
        Date date;
        for (int x = 0; x < lines; x++) {
            date = new Date(rand.nextLong());
            lineList.add(dateFormatter.format(date) + "\t" + names[rand.nextInt(names.length)] + "\t" + messages[rand.nextInt(messages.length)]);
        }
        printFile(fileName, lineList);
    }

    public void genQKFile(int lines, String fileName) {
        ArrayList<String> lineList = new ArrayList<>();
        for (int x = 0; x < lines; x++) lineList.add(names[rand.nextInt(names.length)] + "\t" + rand.nextInt(40));
        printFile(fileName, lineList);
    }

    public String[] getArguments() {
        String[] argu = new String[arguments.size()];
        for (int z = 0; z < arguments.size(); z++) {
            argu[z] = arguments.get(z);
        }
        return argu;
    }

    private String[] names = {
            "Marco",
            "Paolo",
            "Mario",
            "Sara",
            "Alice",
            "Anna",
            "Giorgio",
            "Giovanni",
            "Martina",
            "Giulia",
            "Paola",
            "Francesca" ,
            "Angel",
            "Florida",
            "Aisha",
            "Prince",
            "Delta",
            "Janetta",
            "Ophelia",
            "Junko",
            "Carlie",
            "Gertie",
            "Tim",
            "Antone",
            "Kristine",
            "Vena",
            "Charlie",
            "Devin",
            "Chasidy",
            "Gretta",
            "Melodie",
            "Shavon",
            "Jamal",
            "Georgina",
            "Venice",
            "Cyril",
            "Elda",
            "Jonah",
            "Rosalina",
            "Lydia",
            "Earlene",
            "Son",
            "Allegra",
            "Myrtice",
            "Brittney",
            "Latarsha",
            "Laree",
            "Refugia",
            "Emery",
            "Gertude",
            "Lina",
            "Denis",
            "Shantell",
            "Dorthea",
            "Danielle",
            "Gerald",
            "Chauncey",
            "Geneva",
            "Armanda",
            "Everett",
            "Kimbery",
            "Kristy"
    };
    private String[] operands = {"SIGN-UP", "FOLLOW", "RENAME", "UNFOLLOW", "MUTE", "UNMUTE"};
    private String[] messages = {"A bird in the hand is worth two in the bush",
            "A bunch of fives",
            "A chain is only as strong as its weakest link",
            "A countenance more in sorrow than in anger",
            "A Daniel come to judgement",
            "A diamond in the rough",
            "A diamond is forever",
            "A different kettle of fish",
            "A dish fit for the gods",
            "A dog is a man's best friend",
            "A drop in the bucket",
            "A drop in the ocean",
            "A fate worse than death",
            "A feather in one's cap",
            "A fish rots from the head down",
            "A fish out of water",
            "A fly in the ointment",
            "A fool and his money are soon parted",
            "A fool's paradise",
            "A foot in the door",
            "A foregone conclusion",
            "A friend in need is a friend indeed",
            "A golden key can open any door",
            "Mad as a hatter",
            "Mad as a March hare",
            "Magical realism",
            "Main chance",
            "Make a bee-line for",
            "Make a clean breast of it",
            "Make a pig's ear of",
            "Make a virtue of necessity",
            "Make haste",
            "Make hay while the sun shines",
            "Make him an offer he can't refuse",
            "Make my day",
            "Make no bones about",
            "Make your hair stand on end",
            "Mal de mer",
            "Man after my own heart",
            "Man does not live by bread alone",
            "Man who is his own lawyer has a fool for a client - A",
            "A dog is a man's best friend",
            "Man's inhumanity to man",
            "Many a little makes a mickle",
            "Many are called but few are chosen",
            "Many happy returns",
            "Many a true word is spoken in jest",
            "'March' phrases",
            "Mare's nest",
            "Marital aid",
            "Market forces",
            "Marry in haste, repent at leisure",
            "May you live in interesting times"
    };
}
