<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <!--Import Google Icon Font-->
            <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
            <!--Import materialize.css-->
            <link type="text/css" rel="stylesheet" href="static/css/materialize.min.css"  media="screen,projection"/>

            <!--Let browser know website is optimized for mobile-->
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
            
            <script type="text/javascript">
			function formValidation()
			{
				var pass = (document.getElementById("pass").value).length;
				var contact = (document.getElementById("contact").value).length;
				if( pass < 6)
				{
					alert('Password length should be of atleast 6 characters.');
					return false
				}
				if(contact != 10 || ( isNaN(document.getElementById("contact").value)) )
					{
					alert('Contact number should be of 10 digit.');
					return false
					}
				return true;
			}
		</script>
            <!-- My Stuff -->
            <title>GDG Assessment</title>
            <link rel="stylesheet" type="text/css" href="static/css/style.css">
        </head>

        <body id="background_indexpage">
            <div class="row">
                <div class="col s4 offset-s6" id="box">
                <h6 style="color: red;" class="left-align">${invalid}</h6>
                    <img src="static/images/gdgjsslogoblue.png" style="margin: 0px 0px 0px -17px;">
                    <br><br>
                    <a class="waves-effect waves-light modal-trigger blue-grey darken-3" id="btn" href="#modal1"><p id="btn-txt">Login</p></a>
                    <br><p style="margin-left: 33%;">--or--</p>
                    <a class="waves-effect waves-light modal-trigger blue-grey darken-3" id="btn" href="#modal2"><p id="btn-txt">Sign Up</p></a>
                    <!-- Modal Structure -->
                    <div id="modal1" class="modal">
                        <div class="modal-content">
                            <div class="container">
                                <div class="row">
                                    <h4 class="center blue-grey-text text-darken-3">LOGIN</h4>
                                    <form class="col s12" action="/gdgmcq/LoginController.html" method="post">
                                        <div class="row">
                                            <div class="input-field col s12">
                                              <i class="material-icons prefix">person_pin</i>
                                              <input id="icon_prefix" type="text" class="validate" name="rollno" placeholder="14IT037" required />
                                              <label for="icon_prefix">Roll Number</label>
                                            </div>
                                            <div class="input-field col s12">
                                              <i class="material-icons prefix">lock</i>
                                              <input id="password" type="password" class="validate" name="password" required />
                                              <label for="icon_telephone">Password</label>
                                            </div>
                                        </div>
                                        <input type="submit" value="SUBMIT" class="btn blue-grey darken-3" style="margin-left: 40%;">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <a href="#!" class= "modal-action modal-close waves-effect waves-grey btn-flat blue-grey-text text-darken-3">CLOSE</a>
                        </div>
                    </div>
                    <div id="modal2" class="modal">
                        <div class="modal-content">
                          <div class="container">
                                <div class="row">
                                    <h4 class="center blue-grey-text text-darken-3">SIGN UP</h4>
                                    <form class="col s12" action="/gdgmcq/RegistrationController.html" method="post" onsubmit="return formValidation();">
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <i class="material-icons prefix">perm_identity</i>
                                                <input id="icon_prefix" type="text" class="validate" name="name" required />
                                                <label for="icon_prefix">Name</label>
                                            </div>
                                            <div class="input-field col s12">
                                                <i class="material-icons prefix">email</i>
                                                <input id="email" type="email" class="validate" name="email" required />
                                                <label for="email">Email</label>
                                            </div>
                                       		<div class="input-field col s12">
                                              <i class="material-icons prefix">school</i>
                                              <input id="icon_prefix" type="text" class="validate" name="college" required />
                                              <label for="icon_prefix">College</label>
                                            </div>
                                            <div class="input-field col s12">
                                              <i class="material-icons prefix">assignment</i>
                                              <input id="icon_prefix" type="text" class="validate" name="branch" required />
                                              <label for="icon_prefix">Branch</label>
                                            </div>
                                            <div class="input-field col s12">
                                              <i class="material-icons prefix">person_pin</i>
                                              <input id="icon_prefix" type="text" class="validate" name="rollno" placeholder="14IT037" required />
                                              <label for="icon_prefix">Roll No</label>
                                            </div>
                                            <div class="input-field col s12">
                                                <i class="material-icons prefix">phone</i>
                                                <input id="contact" type="tel" class="validate" name="contact" required />
                                                <label for="icon_telephone">Contact</label>
                                            </div>
                                            
                                            <div class="input-field col s12">
                                              <i class="material-icons prefix">lock_outline</i>
                                              <input id="pass" type="password" class="validate" name="pass" required />
                                              <label for="icon_telephone">Password</label>
                                            </div>
                                        </div>
                                        <input type="submit" value="SUBMIT" class="btn blue-grey darken-3" style="margin-left: 40%;">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <a href="#!" class=" modal-action modal-close waves-effect waves-grey btn-flat blue-grey-text text-darken-3">Close</a>
                        </div>
                    </div>
                </div>
            </div>
            <!--Import jQuery before materialize.js-->
            <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
            <script type="text/javascript" src="static/js/materialize.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function(){
                    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
                    $('.modal-trigger').leanModal();
                });
            </script>
        </body>
    </html>