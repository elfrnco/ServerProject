import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //Initialize scanner to get user input from the physical keyboard
        Scanner keyboard = new Scanner(System.in);
        //The port number must match the server's port number
        int port = 2000;

        try(
                //attempt to connect to their server at "localhost" (this PC) on port 2000
                Socket socket = new Socket("localhost", port);

                //Create a reader to "hear" messages coming from the server
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //Create a writer to "speak" to the server; 'true' enables auto-flushing
                PrintWriter streamWtr = new PrintWriter(socket.getOutputStream(), true);
                ){
            //Read the server's first message (What is ur name?) and print it
            System.out.println(streamRdr.readLine());

            //Wait for the user to  type their name into the console
            String input = keyboard.nextLine();

            //Send that name across the network to the server
            streamWtr.println(input);

            //Read the server's next message ("What is ur age?") and print it
            System.out.println(streamRdr.readLine());

            //wait for the user to type their age to the console
            input = keyboard.nextLine();

            //send that age String to the server
            streamWtr.println(input);

            //Read the server's calculation result and print it
            System.out.println(streamRdr.readLine());

            //Read final message
            System.out.println(streamRdr.readLine());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
