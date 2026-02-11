import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        int port = 2000;

        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(clientSocket.getOutputStream(), true);
                ){
            streamWtr.println("What is your name? ");
            String name = streamRdr.readLine();

            int age;
            while(true) {
                streamWtr.println("What is your age? ");
                try {
                    age = Integer.parseInt(streamRdr.readLine());

                    //Validation: age cannot be zero or negative
                    if (age <= 0) {
                        throw new NumberFormatException();
                    } else {
                        //Logic check: if age is 18 or older, they can vote
                        if (age >= 18) {
                            streamWtr.println(name + ", you may exercise ur right to vote!");
                            break;
                        } else {
                            //if younger than 18, send the "too young" message
                            streamWtr.println(name + ", you are too young to vote!");
                            break;
                        }
                    }
                } catch (NumberFormatException nfe) {
                    streamWtr.println("Please enter a valid age");
                }
            }
            streamWtr.println("Thank you and good day!");
        }catch (Exception e){
            //Print the stack trace if a network error occurs
            e.printStackTrace();
        }
    }
}
