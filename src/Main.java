import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        int n_max = 20; //Max account count
        System.out.println("-".repeat(29) + "\nWelcome to The Pogodemon Bank!");

        String users[] = new String[n_max];
        String passwords[] = new String[n_max];
        int balances[] = new int[n_max];
        int n_user = 0;
        for (int i = 0; i < users.length; i++)
        {
            users[i] = "";
            passwords[i] = "";
            balances[i] = 0;
        }

        boolean first_time = true;
        boolean logged_in = false;
        String user_token = "";
        while (true)
        {
            String command;

            if (!logged_in)
            {
                if (n_user == 0)
                {
                    if (first_time)
                    {
                        System.out.println("There are currently no registered users of the Pogodemon Bank. Hurry up, be the first one!");
                        first_time = false;
                    }
                }

                else
                {
                    System.out.println("-".repeat(40) + "\nWelcome. Use the \"login\" command to enter your account.");
                }
                if (n_user == 0)
                    System.out.println("Available commands: \"register\", \"exit\".");

                else if (n_user == n_max)
                    System.out.println("Available commands: \"login\", \"exit\".");

                else
                    System.out.println("Available commands: \"register\", \"login\", \"exit\".");

                System.out.print(">>");
                command = scanner.nextLine();
                command = removeSpaces(command);

                if (command.equals("exit"))
                    break;

                else if (command.equals("login"))
                {
                    if (n_user != 0)
                    {
                        String username = "";
                        String password = "";
                        boolean valid = false;
                        System.out.print("Enter the username.\n>>");
                        while (!valid)
                        {
                            username = scanner.nextLine();
                            username = removeSpaces(username);

                            for (int i = 0; i < n_user; i++)
                            {
                                if (username.equals(users[i]))
                                {
                                    valid = true;
                                    System.out.println("User " + username + " found. Enter the password.");
                                    break;
                                }
                            }

                            if (!valid)
                                System.out.print("No matching username found. Please try again.\n>>");
                        }

                        valid = false;
                        System.out.print(">>");
                        while (!valid)
                        {
                            password = scanner.nextLine();
                            password = removeSpaces(password);

                            if (password.equals(passwords[Arrays.asList(users).indexOf(username)]))
                            {
                                valid = true;
                                System.out.println("Success. Welcome, user " + username + ".");
                                logged_in = true;
                                user_token = username;
                            }

                            else
                                System.out.print("Wrong password. Please try again.\n>>");
                        }
                    }

                    else
                        System.out.println("There are no registered users right now. Try \"register\" command.");
                }

                else if (command.equals("register"))
                {
                    if (n_user == n_max)
                    {
                        System.out.println("We are sorry, but there are no storage left in our database to accommodate more users.");
                        System.out.println("You need to either visit another bank or wait for some of our other clients to delete their accounts.");
                    }

                    else
                    {
                        String username = "";
                        String password = "";
                        System.out.print("First, enter a 16 digit code as your username.\n>>");
                        boolean valid = false;
                        while (!valid)
                        {
                            username = scanner.nextLine();
                            username = removeSpaces(username);
                            if (username.length() != 16)
                            {
                                System.out.print("Your username must only consist of 16 digits. Please try again.\n>>");
                            }

                            else
                            {
                                for (int i = 0; i < 16; i++)
                                {
                                    valid = false;
                                    if (!Character.isDigit(username.charAt(i)))
                                    {
                                        System.out.print("Your username must only consist of digits. Please try again.\n>>");
                                        break;
                                    }
                                    valid = true;
                                }
                            }
                        }

                        users[n_user] = username;
                        System.out.print("Great. Now let's add some password to it.\nIt must consist of four digits.\n>>");

                        valid = false;
                        while (!valid)
                        {
                            password = scanner.nextLine();
                            password = removeSpaces(password);
                            if (password.length() != 4)
                            {
                                System.out.print("Your password must only consist of four digits. Please try again.\n>>");
                            }

                            else
                            {
                                for (int i = 0; i < 4; i++)
                                {
                                    valid = false;
                                    if (!Character.isDigit(password.charAt(i)))
                                    {
                                        System.out.print("Your password must only consist of digits. Please try again.\n>>");
                                        break;
                                    }
                                    valid = true;
                                }
                            }
                        }

                        passwords[n_user] = password;
                        n_user++;
                        System.out.println("Congratulations, your new account is ready. You are now logged in.");
                        logged_in = true;
                        user_token = username;
                    }
                }

                else
                {
                    System.out.println("Command \"" + command + "\" does not exist.");
                }
            }

            else
            {
                System.out.println("-".repeat(30) + "\nUser: " + user_token);
                System.out.println("Balance: " + balances[Arrays.asList(users).indexOf(user_token)] + " USD");
                System.out.print("Available commands: \"cashin\", \"cashout\", \"logout\", \"delete\".\n>>");

                command = scanner.nextLine();
                command = removeSpaces(command);

                if (command.equals("logout"))
                {
                    logged_in = false;
                    user_token = "";
                }

                else if (command.equals("cashin"))
                {
                    System.out.println("Enter the amount you want to cash-in.");

                    boolean valid = false;
                    String amount = "";
                    while (!valid)
                    {
                        System.out.print(">>");
                        amount = scanner.nextLine();
                        amount = removeSpaces(amount);

                        for (int i = 0; i < amount.length(); i++)
                        {
                            valid = false;

                            if (!Character.isDigit(amount.charAt(i)))
                            {
                                System.out.println("Wrong input format. Please try again.");
                                break;
                            }

                            valid = true;
                        }
                    }

                    balances[Arrays.asList(users).indexOf(user_token)] += Integer.valueOf(amount);
                    System.out.println("Success. You have cashed in " + amount + " USD.");
                }

                else if (command.equals("cashout"))
                {
                    if (balances[Arrays.asList(users).indexOf(user_token)] == 0)
                    {
                        System.out.println("Insufficient funds. Please cash-in first.");
                    }

                    else
                    {
                        System.out.println("Enter the amount you want to cash-out.");

                        boolean valid = false;
                        String amount = "";
                        while (!valid)
                        {
                            System.out.print(">>");
                            amount = scanner.nextLine();
                            amount = removeSpaces(amount);

                            for (int i = 0; i < amount.length(); i++)
                            {
                                valid = false;

                                if (!Character.isDigit(amount.charAt(i)))
                                {
                                    System.out.println("Wrong input format. Please try again.");
                                    break;
                                }

                                valid = true;
                            }

                            if (valid)
                            {
                                if (Integer.valueOf(amount) > balances[Arrays.asList(users).indexOf(user_token)])
                                {
                                    System.out.println("Insufficient funds. Please enter a smaller amount.");
                                    valid = false;
                                }
                            }
                        }

                        balances[Arrays.asList(users).indexOf(user_token)] -= Integer.valueOf(amount);
                        System.out.println("Success. You have cashed out " + amount + " USD.");
                    }
                }

                else if (command.equals("delete"))
                {
                    System.out.print("Are you sure you want to delete your account at The Pogodemon Bank?\nYou will lose all your unwithdrawn funds.\nType \"yes\" to confirm. Anything else will cancel the process.\n>>");
                    String confirmation = scanner.nextLine();
                    confirmation = removeSpaces(confirmation);

                    if (confirmation.toLowerCase(Locale.ROOT).equals("yes"))
                    {
                        System.out.println("Account belonging to user " + user_token + " has been deleted.");
                        int t = Arrays.asList(users).indexOf(user_token);
                        users[t] = "";
                        passwords[t] = "";
                        logged_in = false;
                        user_token = "";

                        for (int i = t; i < n_user - 1; i++)
                        {
                            users[i] = users[i + 1];
                            passwords[i] = passwords[i + 1];
                            balances[i] = balances[i + 1];
                        }

                        users[n_user - 1] = "";
                        passwords[n_user - 1] = "";
                        balances[n_user - 1] = 0;
                        n_user--;
                    }

                    else
                        System.out.println("Account deletion aborted. Your account still exists.");
                }

                else
                {
                    System.out.println("Command \"" + command + "\" does not exist.");
                }
            }
        }
        scanner.close();
        System.out.println("Thanks for visiting The Pogodemon Bank. Come back soon.");
    }

    private static String removeSpaces(String s)
    {
        if (s.contains(" "))
            return s.substring(0, s.indexOf(" "));

        else
            return s;
    }
}
