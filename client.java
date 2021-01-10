/**
 *
 * @author Abhisht Singh 
 * 1810110006
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String args[]) {
        double radius = 20.0;
            System.out.println("Hello");
        
        try {
            Socket clients = new Socket("127.0.0.1", 6789);
            System.out.println("Socket connected");
//
            DataInputStream ip = new DataInputStream(clients.getInputStream());
            DataOutputStream op = new DataOutputStream(clients.getOutputStream());
            ObjectOutputStream os = new ObjectOutputStream(clients.getOutputStream());
            System.out.println("client.main()");

//            ObjectInputStream is = new ObjectInputStream(clients.getInputStream());
            
            Scanner ss = new Scanner(System.in);
            char node1 = 'A';
            char node2 = 'D';
            int n = 4;
            Integer[][] arr = {{0,2,0,1,1},
                                {0,0,0,1,0},
                                {0,0,0,0,1},
                                {0,0,0,0,0},
                                {0,1,0,0,0}};
            
            System.out.println("Enter the 25 matrix elements");
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++)
                    arr[i][j] = ss.nextInt();
            }
            System.out.println("The matrix is ");
            
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++)
                    System.out.print(arr[i][j]+" ");
                System.out.println("");                  
            }
            System.out.println("Enter n");
            n = ss.nextInt();
            System.out.println("Enter node1 and node2");
            ss.nextLine();
            node1 = (ss.nextLine()).toUpperCase().charAt(0);
            node2 = (ss.next()).toUpperCase().charAt(0);
            
            os.writeObject(arr);
            op.writeInt(n);
            op.writeChar(node1);
            op.writeChar(node2);

            op.flush();
            char result = ip.readChar();
            System.out.println(result);  // displaying the result 
            
            // saving the file as 'result.jpg' 
            FileOutputStream fo = new FileOutputStream("result.jpg");
            long filelength = ip.readLong();
            byte[] buffer = new byte[4*1024];
            int bytes = 0;
            while (filelength > 0 && (bytes = ip.read(buffer, 0, (int)Math.min(buffer.length, filelength))) != -1) {
            fo.write(buffer,0,bytes);
            filelength -= bytes;      // read upto file size
        }
            fo.close();
            System.err.println("Image saved as 'result.jpg'");
            clients.close();
            
            
            
            
        } catch (IOException e) {
            System.out.println(e);
        }
        catch (Exception ee){
            System.out.println(ee);
        }
        
        
        
    }
}
