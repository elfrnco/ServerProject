import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int port = 2000;

        try(
                Socket socket = new Socket("localhost", port);
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(socket.getOutputStream(), true);
                ){
            //name
            System.out.print(streamRdr.readLine());
            String input = keyboard.nextLine();
            streamWtr.println(input);

            //age
            while(true) {
                System.out.print(streamRdr.readLine());
                input = keyboard.nextLine();
                streamWtr.println(input);

                String result = streamRdr.readLine();

                if (result.contains("vote")){
                    System.out.println(result);
                    break;
                }
                System.out.println(result);

            }
            System.out.println(streamRdr.readLine());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
