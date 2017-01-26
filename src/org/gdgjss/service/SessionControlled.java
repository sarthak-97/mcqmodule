package org.gdgjss.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gdgjss.model.Constraints;
import org.gdgjss.model.Questions;
import org.gdgjss.model.Registration;
import org.gdgjss.model.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Service layer for displaying questions and submitting solution.
 * 
 * @author Tilhari
 *
 */

@Controller
public class SessionControlled {
	
	private List<Constraints> cons;

	private String solutions[];

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Registration registration;

	

	@RequestMapping(value = "/sessionQuestionController", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(HttpSession httpSession) {
		/**
		 * As soon as the test is started key1 attribute is destroyed. In case
		 * reload/ back button is triggered while test is going on, JSP
		 * Header(---) and following if block code will logout the participant.
		 */
		if (httpSession.getAttribute("key1") == null) {
			httpSession.invalidate();
			return new ModelAndView("galvatronIntercepter");
		}
		httpSession.removeAttribute("key1");
		Session session = sessionFactory.openSession();

		/**
		 * Logic for shuffling questions for each participants.
		 */

		List<Questions> ques = session.createCriteria(Questions.class).list();
		cons= session.createCriteria(Constraints.class).list();
		registration = (Registration) httpSession.getAttribute("SESSION");
		Registration reg = (Registration) session.get(Registration.class, registration.getRollno());
		session.beginTransaction();
		reg.setAttempt(true);
		session.save(reg);
		session.getTransaction().commit();
		session.close();
		Collections.shuffle(ques);
		int size = ques.size();
		/**
		 * Ignoring 0th position of array solution to sync the solution.
		 */
		solutions = new String[size + 1];
		int seqOfQues = 1;
		for (Questions getAnswers : ques) {
			solutions[seqOfQues] = getAnswers.getAnswer();
			seqOfQues++;
		}
		System.out.println( "yeah11"  +cons.get(0).getValue());
		ModelAndView model = new ModelAndView("displayquestions");
		int myhr = 0, mymin = 20, mysec = 0;
		model.addObject("sessionName", registration.getName());
		model.addObject("sessionrollNo", registration.getRollno());
		model.addObject("myhr", cons.get(3).getValue());
		model.addObject("mymin", cons.get(4).getValue());
		model.addObject("mysec", cons.get(5).getValue());
		model.addObject("ques", ques);
		return model;

	}

	@RequestMapping(value = "/sessionSubmitSolution", method = RequestMethod.POST)
	public ModelAndView submitSolution(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("solutionsubmit");
		int marks = 0, countCorrect = 0, countWrong = 0, countUnanswered = 0;
		for (int j = 1; j < solutions.length; j++) {
			String nameid = "ques" + Integer.toString(j);
			String participantSolution = request.getParameter(nameid);
			if (participantSolution != null) {
				if (solutions[j].equals(participantSolution)) {
					marks += Integer.parseInt(cons.get(1).getValue());
					countCorrect += 1;
				} else {
					marks -= Integer.parseInt(cons.get(0).getValue());
					countWrong += 1;
				}

			} else {
				countUnanswered += 1;
			}
		}
		registration = (Registration) httpSession.getAttribute("SESSION");
		Session session = sessionFactory.openSession();
		Result res=(Result)session.get(Result.class, registration.getRollno());
		Transaction tx=session.beginTransaction();
		res.setRgtAns(countCorrect);
		res.setWngAns(countWrong);
		res.setNotAns(countUnanswered);
		res.setNetMarks(marks);
		session.update(res);
		tx.commit();
		session.close();
		model.addObject("marks", marks);
		model.addObject("sessionName", registration.getName());
		httpSession.invalidate();
		return model;
	}

	@RequestMapping(value = "/sessionLogoutController", method = RequestMethod.POST)
	public ModelAndView LogoutController(HttpSession httpSession) {
		httpSession.invalidate();
		ModelAndView model = new ModelAndView("index");
		return model;
	}
}
