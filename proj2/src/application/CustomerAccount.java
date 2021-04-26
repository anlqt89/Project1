package application;

interface CustomerAccount extends Customer {
	private static boolean isUserAvailable(String Username) throws FileNotFoundException { // method to check if username is taken
        String userInfo;
        String breakUserInfo[];
        BufferedReader br = null;
        Scanner InputFile = new Scanner(System.in);

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\micha\\OneDrive\\Desktop\\OOP Test.txt"));
        } catch (FileNotFoundException fnfex) {
            System.out.println("The file was not found");
            System.exit(0);
        }
        try{
            userInfo = br.readLine();
            while ((userInfo = br.readLine()) != null) {
                breakUserInfo = userInfo.split("\t");
                if(breakUserInfo[1].equals(Username)){
                    // Username is already taken
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }
        //Username is valid
        return false;
    }

    public static boolean isEmailAvailable(String Email) throws FileNotFoundException { // method to check if email is taken
        String userInfo;
        String breakUserInfo[];
        BufferedReader br = null;
        Scanner InputFile = new Scanner(System.in);

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\micha\\OneDrive\\Desktop\\OOP Test.txt"));
        } catch (FileNotFoundException fnfex) {
            System.out.println("The file was not found");
            System.exit(0);
        }
        try{
            userInfo = br.readLine();
            while ((userInfo = br.readLine()) != null) {
                breakUserInfo = userInfo.split("\t");
                if(breakUserInfo[0].equals(Email)){
                    // Email is already taken
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }
        //Email is valid
        return false;
    }
	//Log on
	//Log out
	//Create Account
	//Purchase Premium
}
