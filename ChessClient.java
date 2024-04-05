import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChessClient {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			socket = new Socket("127.0.0.1", 8000);
			System.out.println("서버에 접속했습니다.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			while(true) {
				System.out.print("전송하기>>> ");
				String outputMessage = sc.nextLine(); //출력할 메세지 받기
				out.println(outputMessage); //출력 스트림에 넣고
				out.flush(); //전송
				if ("quit".equalsIgnoreCase(outputMessage)) break;
								
				String inputMessage = in.readLine();
				System.out.println("From Server: " + inputMessage);
				if ("quit".equalsIgnoreCase(inputMessage)) break;
			}
			
		}
		catch (IOException e) {
			System.out.print("실패");
		}
		finally {
			try {
				socket.close();
				System.out.println("서버연결종료");
			} catch (IOException e) {
				System.out.println("소켓통신에러");
			}
		}
	}
}
