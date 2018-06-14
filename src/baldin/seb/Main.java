package baldin.seb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TestGenerator generator = new TestGenerator();
        generator.genAFile(135, "A05.txt");
        generator.genMFile(35, "M05.txt");
        generator.genQKFile(20, "Q04.txt");
        generator.genAFile(76, "A06.txt");
        generator.genQKFile(10, "K01.txt");
        generator.genMFile(51, "M06.txt");
        generator.genQKFile(11, "Q05.txt");
        args = generator.getArguments();
        Board board = new Board();
        for (String param : args) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(param));
                String line = br.readLine();
                if (param.charAt(0) == 'A') {
                    while (line != null) {
                        String[] params = line.split("\\t");
                        System.out.print("Linea parserizzata: \"" + line + "\"\nRisultato:\n");
                        try {
                            if (params[2].equals("SIGN-UP")) {
                                board.addNewUser(params[1]);
                            } else if (params[2].equals("FOLLOW")) {
                                board.follow(params[0], params[1], params[3]);
                            } else if (params[2].equals("UNFOLLOW")) {
                                board.unfollow(params[1], params[3]);
                            } else if (params[2].equals("RENAME")) {
                                board.renameUser(params[1], params[3]);
                            } else if (params[2].equals("MUTE")) {
                                board.setMuted(params[1], params[3]);
                            } else if (params[2].equals("UNMUTE")) {
                                board.setUnmuted(params[1], params[3]);
                            } else {
                                System.out.println("Operazione da effettuare non riconosciuta.\nPotrebbe esserci un errore di sintassi.");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (NullPointerException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (Exception e)  {
                            System.out.println("Errore imprevisto.");
                        }
                        line = br.readLine();
                        System.out.print("\n");
                    }
                } else if (param.charAt(0) == 'M') {
                    while (line != null) {
                        String[] params = line.split("\\t");
                        System.out.print("Linea parserizzata: \"" + line + "\"\nRisultato:\n");
                        try {
                            board.sendMessage(params[0], params[1], params[2]);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (NullPointerException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (Exception e)  {
                            System.out.println("Errore imprevisto.");
                        }
                        line = br.readLine();
                        System.out.print("\n");
                    }
                } else if (param.charAt(0) == 'Q') {
                    while (line != null) {
                        String[] params = line.split("\\t");
                        System.out.print("Linea parserizzata: \"" + line + "\"\nRisultato:\n");
                        try {
                            //System.out.println(params[0] + " - " + params[params.length - 1]);
                            /*for (String p: params) {
                                System.out.println(p + "-");
                            }*/
                            System.out.println("L'utente " + board.getUserFromUsername(params[0]).getUsername() + " ha ricevuto i seguenti messaggi: " + board.getMessageForUser(params[0], Integer.parseInt(params[1])));
                        } catch (NumberFormatException e) {
                            System.out.println("Numero non valido.\nQuesti sono tutti i messaggi ricevuti dall'utente:\n");
                            System.out.println("L'utente " + board.getUserFromUsername(params[0]).getUsername() + " ha ricevuto i seguenti messaggi: " + board.getMessageForUser(params[0]));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (NullPointerException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (Exception e)  {
                            System.out.println("Errore imprevisto.");
                        }
                        line = br.readLine();
                        System.out.print("\n");
                    }
                } else if (param.charAt(0) == 'K') {
                    while (line != null) {
                        String[] params = line.split("\\t");
                        System.out.print("Linea parserizzata: \"" + line + "\"\nRisultato:\n");
                        try {
                            System.out.println("L'utente " + board.getUserFromUsername(params[0]).getUsername() + " ha inviato i seguenti messaggi: " + board.getUserMessage(params[0], Integer.parseInt(params[1])));
                        } catch (NumberFormatException e) {
                            System.out.println("Numero non valido.\nQuesti sono tutti i messaggi inviati dall'utente:\n");
                            System.out.println("L'utente " + board.getUserFromUsername(params[0]).getUsername() + " ha inviato i seguenti messaggi: " + board.getUserMessage(params[0]));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (NullPointerException e) {
                            System.out.println("C'e' stato un errore nella parserizzazione della linea corrente.\nProbabilmente i campi non erano delimitati da tabulazioni.");
                        } catch (Exception e)  {
                            System.out.println("Errore imprevisto.");
                        }
                        line = br.readLine();
                        System.out.print("\n");
                    }
                } else {
                    System.out.println("Opzione non valida.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File non trovato.");
            } catch (IOException e) {
                System.out.println("Errore di Input/Output.");
            } catch (Exception e)  {
                System.out.println("Errore imprevisto.");
            }
        }
        //board.printMessages();
    }

    /*public static void testTwitter() {
        Board board = new Board();

        board.addNewUser("sebo");
        board.addNewUser("pippo");
        board.addNewUser("pane");
        board.follow("pane", "pippo");

        board.sendMessage("pippo", 2);
        board.follow(1, 3);
        board.sendMessage("sono pane", 3);

        System.out.println("2. " + board.getMessageForUser(2));

        board.addNewUser("pesce");

        board.follow(3, 1);
        board.printUsers();
        board.printMessages();

        board.sendMessage("sono Pippo", 2);
        board.sendMessage("sono pane 2", 3);

        board.renameUser(3, "Paxerello");
        System.out.println("1. " + board.getMessageForUser(1));
        System.out.println("2. " + board.getMessageForUser(2));
        //board.follow(2, 3);
        board.sendMessage("ciaoooooo", 1);

        System.out.println("1. " + board.getMessageForUser(1));
        System.out.println("2. " + board.getMessageForUser(2));
        System.out.println("3. " + board.getMessageForUser(3));
        System.out.println("4. " + board.getMessageForUser(4));

        board.printUsers();
        System.out.println("\n");
        board.printMessages();

        System.out.println(board.getUserMessage(3));
    }*/
}
