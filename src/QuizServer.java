import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
    public static void main(String[] args) throws IOException {

        int port = 9999;
        String message;
        String result;

        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(clientSocket.getOutputStream(), true);
                ){
            while(true) {
                streamWtr.println("Enter a message: ");
                message = streamRdr.readLine();
                int counter = 0;

                for (int i = 0; i < message.length(); i++) {
                    char c = message.charAt(i);
                    if (c >= '0' && c <= '9') {
                        counter++;
                    }
                }

                if(counter == 0){
                    result = "zero";
                    streamWtr.println(result);
                    break;
                }else if(counter % 2 == 0){
                    result = "even";
                    streamWtr.println(result);
                }else if(counter % 2 == 1){
                    result = "odd";
                    streamWtr.println(result);
                }
            }
        }
    }
}