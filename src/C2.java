import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class C2 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int port = 2000;
        String input;

        try(
                Socket socket = new Socket("localhost", port);
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(socket.getOutputStream(), true);
                ){
            //prompt name
            System.out.print(streamRdr.readLine());
            input = keyboard.nextLine();
            streamWtr.println(input);

            //prompt age
            System.out.print(streamRdr.readLine());
            input = keyboard.nextLine();
            streamWtr.println(input);

            //Validation loop
            String serverResponse;
            while(true){
                serverResponse = streamRdr.readLine();
                System.out.print(serverResponse);
                if(serverResponse.contains("vote") || serverResponse.contains("Thank You")){
                    break;
                }
                input = keyboard.nextLine();
                streamWtr.println(input);
            }

            //print result
            System.out.println(streamRdr.readLine());

            System.out.println(streamRdr.readLine());
            System.out.println(streamRdr.readLine());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}