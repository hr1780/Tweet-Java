import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tweetapp.service.RegisterService;

public class Application {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
    static RegisterService registerService = new RegisterService() ;

	public static void menu() throws NumberFormatException, IOException {
		System.out.println("Enter your choice :");
        System.out.println("1.> Register");
        System.out.println("2.> Login");
        System.out.println("3.> Forgot Password");
        System.out.println("4.> Exit");
        
		  int choice = Integer.parseInt(br.readLine());
		     switch (choice) {
				case 1 :
					registerUser();
					break ;
				case 2:
					loginUser();
					break ;
				case 3:
					forgotPassord();
					break ;
				case 4:
					System.exit(0);
				default:
					menu();
					break;
				}

	}

	private static  void registerUser() throws NumberFormatException, IOException {
		try {
			registerService.registerUser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
	}

	private static void loginUser() throws IOException {
		registerService.loginUser();
	}

	private static void forgotPassord() throws NumberFormatException, IOException {
		registerService.forgotPassord();
		menu();
	}

	public static void main(String[] args) throws IOException {  
      menu();
  		
}
}