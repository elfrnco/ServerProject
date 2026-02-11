import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QuizClient {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        try(
                Socket serverSocket = new Socket("localhost", 9999);
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(serverSocket.getOutputStream(), true);
                ){
            while(true) {
                String message;
                System.out.print(streamRdr.readLine());
                message = keyboard.nextLine();
                streamWtr.println(message);

                String result = streamRdr.readLine();
                System.out.println(result);
                if (result.contains("zero")){
                    break;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}