import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        //The port that the server will listen on
        int port = 2000;

        try(
                //create a server socket that waits for incoming requests on port 2000
                ServerSocket serverSocket = new ServerSocket(port);

                //Pause execution here until a client connects, then create a socket for that client
                Socket clientSocket = serverSocket.accept();

                //create a reader to get  data sent by the client
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //Create a writer to send data back to the client
                PrintWriter streamWtr = new PrintWriter(clientSocket.getOutputStream(), true);
                ){
            //Send the first question to the client
            streamWtr.println("What is your name?");

            //Receive the name String from the client
            String name = streamRdr.readLine();

            int age;
            //Enter a loot to ensure we get a valid numeric age
            while(true){
                //Ask the client for their age
                streamWtr.println("What is your age?");
                try{
                    //Try to convert the client's text input into an integer
                    age = Integer.parseInt(streamRdr.readLine());

                    //Validation: age cannot be zero or negative
                    if(age <= 0){
                    throw new NumberFormatException();
                    } else {
                        //if age is valid, exit the while loop
                        break;
                    }
                } catch (NumberFormatException nfe) {
                    //If input wasn't a number or was <=0, send an error message and repeat
                    streamWtr.println("Please enter a valid age");
                    continue;
                }
            }
            //Logic check: if age is 18 or older, they can vote
            if(age >= 18){
                streamWtr.println(name + ", you may exercise ur right to vote!");
            }else{
                //if younger than 18, send the "too young" message
                streamWtr.println(name + ", you are too young to vote!");
            }

            // send a final closing message
            streamWtr.println("Thank you and good day!");
        }catch (Exception e){
            //Print the stack trace if a network error occurs
            e.printStackTrace();
        }
    }
}
