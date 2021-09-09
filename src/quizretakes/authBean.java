package quizretakes;


public class authBean {
	private String username;
	   private String pass;

	   // *** Constructor *** //
	   public authBean (String username, String pass)
	   {
	      this.username = username;
	      this.pass   = pass;
	   }

	   // *** Getters *** //
	   public String getUser()
	   {
	      return username;
	   }
	   public String getPassword()
	   {
	      return pass;
	   }
}

