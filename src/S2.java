import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class S2{
    public static void main(String[] args){
        int port = 2000;
        String name;

        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();

                BufferedReader streamRdr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter streamWtr = new PrintWriter(clientSocket.getOutputStream(), true)
                ){
            //prompt name
            streamWtr.println("Enter ur name!!!");
            name = streamRdr.readLine();

            int age;
            while(true){
                streamWtr.println("Enter ur age!!!");
                try{
                    age = Integer.parseInt(streamRdr.readLine());
                    if(age<=0){
                        throw new NumberFormatException();
                    }else{
                        break;
                    }
                }catch(Exception e){
                    streamWtr.println("Wrong age!!!");
                    continue;
                }
            }

            if( age >= 18){
                streamWtr.println(name + ", u r eligible to vote!!!");
            }else{
                streamWtr.println(name + ", u r too young to vote!!!");
            }

            streamWtr.println("Thank you and Good bye!");
            streamWtr.println("TERMINATING!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}