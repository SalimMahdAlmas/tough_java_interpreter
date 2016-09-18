package littlevil;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;


public class Tough {
    private static void print_usage()  {
        System.out.print("tough by LittlEvil\n");
        System.out.print("written in java\n");
        System.out.print("usage : \n");
        System.out.print("\ttough [file]\t\t\t\t\t To execute your tough file\n");

        System.out.print("\ttough --help,help\t\t\t\t To print this\n");

        System.out.print("\ttough --version,version\t\t\t To print this\n");

        System.out.print("\ttough --copyright,copyright\t\t To print copyright\n");
    }

    private static int parse(String token , int last) {
        if (Objects.equals(token, "(.)")) {

            return 1;
        }
        int x = last;
        for (String word : token.split("")) {
            if (Objects.equals(word, ".")) {
                x += 1;

            }else if (Objects.equals(word, ">")) {
                x += 10;
            }else if (Objects.equals(word, "<")) {
                x -= 10;
            }else if (Objects.equals(word, "^")) {
                x += 5;
            }else if (Objects.equals(word, "_")) {
                x -= 5;
            }
        }
        return x;
    }
    private static  void parse_and_print(String code){

        // (.)|(>..)|(.)\(..<)|(.>>)

        String alphas[] = {"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        
        String[] tokens = code.split("\\|");


        if (tokens.length == 0) {
            System.err.println("syntax error \n");
            return;
        }
        for (String my_token : tokens) {

            if (Objects.equals(my_token, "()")) {
                System.out.print(" ");
                continue;
            }

            System.out.print(alphas[parse(my_token, 0)]);

        }
        System.out.println();
    }

    private static String cleanup(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : code.split("\n")) {
            if (!string.startsWith("//")) {
                stringBuilder.append(string);
            }
        }
        return stringBuilder.toString();

    }
    public static void main(String[] args) {


        if (args.length == 0) {
            print_usage();
        }else {
            String command = args[0];
            if (Objects.equals(command, "--help") || Objects.equals(command, "help")) {
                print_usage();
            }else if (Objects.equals(command, "--copyright") || Objects.equals(command, "copyright")) {
                System.out.println("tough - Copyright(c) S Mahammad Almas");
            }else if (Objects.equals(command, "--version") || Objects.equals(command, "version")) {
                String _VERSION = "0.9 built on java";
                System.out.println(_VERSION);
            }else {

                boolean exists = new File(command).exists();
                if (exists) {
                    try(BufferedReader br = new BufferedReader(new FileReader(command))) {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            line = br.readLine();
                        }
                        String everything = sb.toString();
                        String cleanup = cleanup(everything);


                        for (String line_token : cleanup.split("\n")) {
                            parse_and_print(line_token);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    System.err.println("tough - no such file found or directory");
                }
            }
        }
    }
}
