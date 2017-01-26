package org.gdgjss.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.gdgjss.model.Constraints;
import org.gdgjss.model.Questions;
import org.gdgjss.model.Registration;
import org.gdgjss.model.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * service layer for registration and login.
 * 
 * @author Tilhari
 *
 */
@Controller
public class Commons {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private Registration registered;
	
	// Index page controller.
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getadmissionform(HttpSession httpSession) {
		/**
		 * key attribute is set to check restart of test via back/front/refresh
		 * button of browser. Here if condition is used so that 'key' attribute
		 * is not created again-2 in case index page is refreshed which MAY
		 * throw exceptions.
		 */
		if (httpSession.getAttribute("key1") == null)
			httpSession.setAttribute("key1", "key1");
		ModelAndView model = new ModelAndView("index");
		model.addObject("invalid", null);

		return model;
	}
	
	

	// Registration page controller
	@RequestMapping(value = "/RegistrationController", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(
			@ModelAttribute("registration") org.gdgjss.model.Registration registration) {
		Session session = sessionFactory.openSession();
		ModelAndView model = new ModelAndView("index");
		Result result=new Result();
		if(session.get(Registration.class, registration.getRollno()) == null)
		{
			session.beginTransaction();
			registration.setAttempt(false);
			session.save(registration);
			result.setContact(registration.getContact());
			result.setName(registration.getName());
			result.setRollno(registration.getRollno());
			result.setNetMarks(0);
			result.setRgtAns(0);
			result.setWngAns(0);
			result.setNotAns(0);
			
			session.save(result);
			session.getTransaction().commit();
			session.close();
			model.addObject("invalid", "Successfully registered, login to proceed!");
			
		}
		else
			model.addObject("invalid", "This roll number is already registered.");
		
		return model;
	}

	// Login controller
	@RequestMapping(value = "/LoginController", method = RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @RequestParam("rollno") String rollno,
			@RequestParam("password") String password) {
		ModelAndView model;
		Session session = sessionFactory.openSession();
		registered = (Registration) session.get(Registration.class, rollno);
		//Constraints constraints=(Constraints) session.get(Constraints.class,"rules");
		//System.out.println(constraints.getValue());
		if (registered != null) {
			if (registered.getPass().equals(password)) {
				httpSession.setAttribute("SESSION", registered);
				/**
				 * In case bad-ass tries to come back to login-success(rules)
				 * page via browser back button and tries to be over smart to
				 * restart the quiz timer, following if-block code is executed
				 * as 'key1' attribute is already destroyed by question display
				 * page at first place. Header(---) included on the mapped JSP
				 * will force for page-reload from server and the GALVATRON
				 * INTERCEPTER page will be displayed, automatically destroying
				 * the session and logging out the participant.
				 * 
				 * @security
				 * @author Tilhari
				 */
				if (httpSession.getAttribute("key1") == null) {
					httpSession.invalidate();
					return new ModelAndView("galvatronIntercepter");
				}
				if(registered.isAttempt()==true)
				{
					model = new ModelAndView("index");
					model.addObject("invalid", "A test submission has already been made on this roll number.");
					return model;
				}
				registered = (Registration) httpSession.getAttribute("SESSION");
				model = new ModelAndView("loginsuccess");
				model.addObject("sessionName", registered.getName());
				model.addObject("sessionrollNo", registered.getRollno());
				//model.addObject("rules",constraints.getValue());

			} else {
				model = new ModelAndView("index");
				model.addObject("invalid", "Incorrect roll number or password");
			}
		} else {
			model = new ModelAndView("index");
			model.addObject("invalid", "Incorrect roll number or password");
		}
		session.close();
		return model;
	}
	//admin verifier
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminloginverify(HttpSession httpSession) {
		  ModelAndView model = new ModelAndView("adminlog");
		
		return model;
	}
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ModelAndView admin(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		String name = requestParams.get("name");
			String pass = requestParams.get("pass");
			System.out.println(name);
			System.out.println(pass);
			ModelAndView model;
			model=null;
			
			int f=0;
		if(name.equals("gdg") && pass.equals("gdg")){
		
			model = new ModelAndView("admin");
		
		
		}
		else{
			return new ModelAndView("err");
			
		}
		
		
		return model;
	}
	// questions admin controll
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView adds(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String qid = requestParams.get("id");
		String ques = requestParams.get("ques");
		
				String optionA = requestParams.get("opta");
				String optionB = requestParams.get("optb");
				String optionC = requestParams.get("optc");
				String optionD = requestParams.get("optd");
				String answer = requestParams.get("ans");
				
				
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("add");
	         
	       Query queryResult = session.createQuery("from Questions");
	       java.util.List allUsers;
	       String id;
	       id=null;
	       
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Questions user = (Questions) allUsers.get(i);
	        id=user.getQuestionId();
		      
	        if(ques.equals(id)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f!=1){
	        	   Questions user= new Questions();  
	      
	    user.setQuestionId(qid);
	    user.setQuestion(ques);
	    user.setOptionA(optionA);
	    user.setOptionB(optionB);
	    user.setOptionC(optionC);
	    user.setOptionD(optionD);
	    user.setAnswer(answer);
	      session.save(user);
	       session.getTransaction().commit();
	   
	       session.close(); 
	       user=null;
	       System.out.println(id);
	       model.addObject("add","record added");
	      
	     

	           } 
	           else
	           {   System.out.println("duplicate");
	           model.addObject("dup","duplicate record"); 
	           }
	           return model;  
		}	   
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ModelAndView del(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String questionId = requestParams.get("id");
		
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("del");
	         
	       Query queryResult = session.createQuery("from Questions");
	       java.util.List allUsers;
	       String id;
	     id=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Questions user = (Questions) allUsers.get(i);
	        id=user.getQuestionId();
		
	        if( questionId.equals(id)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f==1){
	        Questions user= (Questions) session.get(Questions.class,questionId);
	      
	     
	      session.delete(user);
	       session.getTransaction().commit();
	   
	       session.close(); 
	       user=null;
	       System.out.println(questionId);
	       model.addObject("delete","deleted");
	      
	     

	           } 
	           else
	           {   System.out.println("not found");
	           model.addObject("nr","not found "); 
	           }
	           return model;  
		}	   
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String quesId = requestParams.get("id");
	
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("edit");
	         
	       Query queryResult = session.createQuery("from Questions");
	       java.util.List allUsers;
	       String qid,ques,opta,optb,optc,optd,ans;
	      qid=null;
	      ques=null;
	      opta=null;
	      optb=null;
	      optc=null;
	      optd=null;
	      ans=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Questions user = (Questions) allUsers.get(i);
	          qid=user.getQuestionId();
	          ques=user.getQuestion();
	          opta=user.getOptionA();
	          optb=user.getOptionB();
	          optc=user.getOptionC();
	          optd=user.getOptionD();
	          ans=user.getAnswer();
	        		  
	         if(quesId.equals(qid) ){
		         f=1;
		         break;
	        
	        }
	         	         
	       }
	       if(f==1){
		         model.addObject("qid",qid); 
		         model.addObject("ques",ques); 
		         model.addObject("opta",opta);
		         model.addObject("optb",optb);
		         model.addObject("optc",optc);
		         model.addObject("optd",optd);
		         model.addObject("ans",ans);
		         
	        }
	         else
	         {
	        	  model.addObject("norec","no record found");  
	         }
	         
	       return model;
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
	    ModelAndView model = new ModelAndView("edit");
		String questionId = requestParams.get("id");
				String ques = requestParams.get("ques");
		String optionA = requestParams.get("opta");
		String optionB = requestParams.get("optb");
		String optionC = requestParams.get("optc");
		String optionD = requestParams.get("optd");
		String ans = requestParams.get("ans");
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	  
	       Questions user= (Questions) session.get(Questions.class,questionId);
	           user.setQuestionId(questionId);
	           user.setQuestion(ques);
	           user.setOptionA(optionA);
	           user.setOptionB(optionB);
	           user.setOptionC(optionC);
	           user.setOptionD(optionD);
	           user.setAnswer(ans);
		      session.update(user);
		       session.getTransaction().commit();
		   
		       session.close(); 
		       model.addObject("rec","record edited");
		       return model;
	}
	@RequestMapping(value = "alques", method = RequestMethod.POST)
	public ModelAndView allques(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
	         
		 ModelAndView model = new ModelAndView("allques");
return model;

}
	//user registration controll
	
	@RequestMapping(value = "allreg", method = RequestMethod.POST)
	public ModelAndView allregs(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
	         
		 ModelAndView model = new ModelAndView("alregs");
return model;

}
	// add new registration
	@RequestMapping(value = "/addreg", method = RequestMethod.POST)
	public ModelAndView addreg(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String rollno = requestParams.get("roll");
		String name = requestParams.get("name");
		
				String college = requestParams.get("clg");
				
				String branch = requestParams.get("branch");
				String contact = requestParams.get("contact");
				String email = requestParams.get("email");
				String pass=requestParams.get("pass");
				
				
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("add");
	         
	       Query queryResult = session.createQuery("from Registration");
	       java.util.List allUsers;
	       String id;
	       id=null;
	       
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	  Registration user = (  Registration) allUsers.get(i);
	        id=user.getRollno();
		      
	        if(rollno.equals(id)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f!=1){
	        	   Registration user= new   Registration();  
	      user.setName(name);
	   user.setRollno(rollno);
	   user.setCollege(college);
	   user.setBranch(branch);
	   user.setContact(contact);
	user.setEmail(email);
	user.setPass(pass);
	      session.save(user);
	       session.getTransaction().commit();
	   
	       session.close(); 
	       user=null;
	       System.out.println(id);
	       model.addObject("add","record added");
	      
	     

	           } 
	           else
	           {   System.out.println("duplicate");
	           model.addObject("dup","duplicate record"); 
	           }
	           return model;  
		}	 
	// delete a registration
	@RequestMapping(value = "/delreg", method = RequestMethod.POST)
	public ModelAndView delreg(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String rollno = requestParams.get("rollno");
		
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("del");
	         
	       Query queryResult = session.createQuery("from Registration");
	       java.util.List allUsers;
	       String id;
	     id=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Registration user = (Registration) allUsers.get(i);
	       id=user.getRollno();
		
	        if( rollno.equals(id)){
	         f=1;
	     
	        break; 
	   
	        
	         }
	        }
	         
	           if(f==1){
	        	   Registration user= (Registration) session.get(Registration.class,rollno);
	      
	     
	      session.delete(user);
	       session.getTransaction().commit();
	   
	       session.close(); 
	       user=null;
	       System.out.println(rollno);
	       model.addObject("delete","deleted");
	      
	     

	           } 
	           else
	           {   System.out.println("not found");
	           model.addObject("nr","not found "); 
	           }
	           return model;  
		}	   
	// modify registrations
	@RequestMapping(value = "editreg", method = RequestMethod.POST)
	public ModelAndView editregs(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String rollno = requestParams.get("rollno");
	
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("editreg");
	         
	       Query queryResult = session.createQuery("from Registration");
	       java.util.List allUsers;
	       String rolno,name,email,pass,branch,college,contact;
	       rolno=null;
	    		   name=null;
	    		   email=null;
	    		   
	    		   pass=null;
	    		   branch=null;
	    		   college=null;
	    		   contact=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	  Registration user = (Registration) allUsers.get(i);
	          rolno=user.getRollno();
	          name=user.getName();
	          email=user.getEmail();
	          pass=user.getPass();
	          contact=user.getContact();
	          branch=user.getBranch();
	          college=user.getCollege();
	        		  
	         if(rollno.equals(rolno) ){
		         f=1;
		         break;
	        
	        }
	         	         
	       }
	       if(f==1){
		         model.addObject("rollno",rolno); 
		         model.addObject("name",name); 
		         model.addObject("pass",pass);
		         model.addObject("email",email);
		         model.addObject("branch",branch);
		         model.addObject("contact",contact);
		         model.addObject("college",college);
		         
	        }
	         else
	         {
	        	  model.addObject("norec","no record found");  
	         }
	         
	       return model;
	}
	@RequestMapping(value = "/modifyreg", method = RequestMethod.POST)
	public ModelAndView modifyregs(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
	    ModelAndView model = new ModelAndView("editreg");
		String rollno = requestParams.get("roll");
				String name = requestParams.get("name");
		String pass = requestParams.get("pass");
		String email= requestParams.get("email");
		String branch = requestParams.get("branch");
		String contact = requestParams.get("contact");
		String college = requestParams.get("college");
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	  
	      Registration user= (Registration) session.get(Registration.class,rollno);
	           user.setRollno(rollno);
	           user.setCollege(college);
	           user.setBranch(branch);
	           user.setEmail(email);
	           user.setName(name);
	           user.setContact(contact);
	           user.setPass(pass);
		      session.update(user);
		       session.getTransaction().commit();
		   
		       session.close(); 
		       model.addObject("rec","record edited");
		       return model;
	}
	// results of users
	@RequestMapping(value = "res", method = RequestMethod.POST)
	public ModelAndView result(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
	         
		 ModelAndView model = new ModelAndView("results");
return model;

}
	// modify constraints
	@RequestMapping(value = "const", method = RequestMethod.POST)
	public ModelAndView constraints(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
				String field = requestParams.get("id");
	             System.out.println(field);
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	      ModelAndView model = new ModelAndView("editconst");
	         
	       Query queryResult = session.createQuery("from Constraints");
	       java.util.List allUsers;
	       String fields,value,data;
	      
	    		   fields=null;
	    		   value=null;
	    		   data=null;
	       allUsers = queryResult.list();
	       int f;
	       f=0;
	       for (int i = 0; i < allUsers.size(); i++) {
	    	   Constraints user = (Constraints) allUsers.get(i);
	          fields=user.getField();
	        		  
	         if(field.equals(fields) ){
		         f=1;
		         break;
	        
	        }
	         	         
	       }
	       if(f==1){
		         model.addObject("field",fields); 
		         model.addObject("value",value); 
		        
		        
		         
	        }
	         else
	         {
	        	  model.addObject("norec","no record found");  
	         }
	         
	       return model;
	}
	@RequestMapping(value = "/modifyconst", method = RequestMethod.POST)
	public ModelAndView modifyconsts(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
	    ModelAndView model = new ModelAndView("editconst");
		String field= requestParams.get("field");
				String value = requestParams.get("value");
		
		
		 Session session =	sessionFactory.openSession();
	       session.beginTransaction();
	       
	  
	       Constraints user= (Constraints) session.get(Constraints.class,field);
	          user.setField(field);
	          user.setValue(value);
		      session.update(user);
		       session.getTransaction().commit();
		   
		       session.close(); 
		       model.addObject("rec","record edited");
		       return model;
	}
	// all constraints
	
	@RequestMapping(value = "cons", method = RequestMethod.POST)
	public ModelAndView constraint(HttpSession httpSession, @RequestParam Map<String,String> requestParams) {
		
		
	         
		 ModelAndView model = new ModelAndView("const");
return model;
}

}



//// alter use of -------string with Model------- in case donot want to use ModelandView
/*
Session session = sessionFactory.openSession();
registered = (Registration) session.get(Registration.class, rollno);
if (registered != null) {
	if (registered.getPassword().equals(password)) {
		httpSession.setAttribute("SESSION", registered);
		*//**
		 * In case bad-ass tries to come back to login-success(rules)
		 * page via browser back button and tries to be over smart to
		 * restart the quiz timer, following if-block code is executed
		 * as 'key1' attribute is already destroyed by question display
		 * page at first place. Header(---) included on the mapped JSP
		 * will force for page-reload from server and the GALVATRON
		 * INTERCEPTER page will be displayed, automatically destroying
		 * the session and logging out the participant.
		 * 
		 * @security
		 * @author Tilhari
		 *//*
		if (httpSession.getAttribute("key1") == null) {
			httpSession.invalidate();
			return new ModelAndView("galvatronIntercepter");
			return "yoyo";
		}
		registered = (Registration) httpSession.getAttribute("SESSION");
		model = new ModelAndView("loginsuccess");
		model.addObject("sessionName", registered.getName());
		model.addObject("sessionrollNo", registered.getRollno());
				return "chiki";
	} else {
		model = new ModelAndView("index");
		model.addAttribute("invalid", "Incorrect roll number or password");
	}
} else {
	model = new ModelAndView("index");
	model.addAttribute("invalid", "Incorrect roll number or password");
}
session.close();
return "index";  //name of jsp page/view
}

}
*/



/*public ModelAndView adminlogin(HttpSession httpSession, @RequestParam("username") String username,
		@RequestParam("password") String password) {
	ModelAndView model;
	Session session = sessionFactory2.openSession();
	admin = (Admin) session.get(Admin.class, username);
	if (admin != null) {
		if (admin.getPassword().equals(password)) {
			httpSession.setAttribute("ADMINSESSION", admin);
			if (httpSession.getAttribute("key1") == null) {
				httpSession.invalidate();
				return new ModelAndView("galvatronIntercepter");
			}
			registered = (Registration) httpSession.getAttribute("ADMINSESSION");
			model = new ModelAndView("adminWorkspace");
			model.addObject("sessionName", admin.getUsername());

		} else {
			model = new ModelAndView("adminLogin");
			model.addObject("invalid", "Incorrect username or password");
		}
	} else {
		model = new ModelAndView("adminLogin");
		model.addObject("invalid", "Incorrect username or password");
	}
	session.close();
	return model;
}*/
