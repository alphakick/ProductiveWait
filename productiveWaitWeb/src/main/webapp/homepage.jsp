<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Effective Wait</title>
	<!-- core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/responsive.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
    <link href="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.css" rel="stylesheet"/>

    
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
    <script src="<c:url value="/resources/js/jquery.tabledit.js" />"></script>

    <style>



     </style>
     <script>
     var selectedPhoneNumber = '';
	   
		$(document).ready(function(){
			 $( "#phonentag" ).autocomplete({
				 minLength: 1,
				 source: function (request, response) {
	                    $.ajax({
	                        type: "GET",
	                        url: "/productiveWaitWeb/getVisitorByPhone",
	                        dataType: "json",
	                        data: {
	                            term: request.term
	                        },
	                        error: function (xhr, textStatus, errorThrown) {
	                            alert('Error: ' + xhr.responseText);
	                        },
	                        success: function (data) {
	                            response($.map(data, function (item) {
	                                return {
	                                    label: item.phoneNumber + ' ' + item.firstName,
	                                    value: item.firstName + ' ' +  item.lastName   
	                                }
	                            }));
	                        }
	                        
	                    });
	                },
			    select: function (event, ui) { 
			    			var selectedLabel = ui.item.label;
			    			selectedPhoneNumber = selectedLabel.split(' ')[0];
			    			$('#addExistingVisitor').prop('disabled', false);
		                 return true;
         		    }
			 });
	 	 }); 
		

		 
		 function deteteVisitorFromQueue(phoneNumber){
			 $.get("/productiveWaitWeb/deleteVisitorFromQueue?phoneNumber=" + phoneNumber,function(){
			 	$("tr:contains('" + phoneNumber + "')").remove();
			 });
		 }

     
	     $(document).ready(function(){




	    	 
	    	    $('#visitorQueue-tbody').sortable({
	    	        helper: fixWidthHelper,
	    	        update: function( event, ui ) {
	    	            $(this).children().each(function(index) {
	    	        			$(this).find('td').first().html(index + 1)
	    	            });
	    	          }
	    	    }).disableSelection();
	    	        
	    	    function fixWidthHelper(e, ui) {
	    	        ui.children().each(function() {
	    	            $(this).width($(this).width());
	    	        });
	    	        return ui;
	    	    }  
	    	    
	    	    function resetFields(){
	    	    		$("#firstName").val("");
	    	    		$("#lastName").val("");
	    	    		$("#phoneNumber").val("");
	    	    		$("#email").val("");
	    	    		$("#address").val("");
	    	    		$("#city").val("");
	    	    		$("#zip").val("");
	    	    		$('#state').val("");
	    	    }
	    	    
 	    	    //$(".delImage").click(deteteVisitorFromQueue);
 	    	    		
	    	    

	    	    
	    	    $("#addVisitor").click(function(){
	    	        var rowCount = $('#visitorQueue >tbody >tr').length + 1;
	    	        var firstName = $("#firstName").val();
	    	        var lastName = $("#lastName").val();
	    	        var phoneNumber = $("#phoneNumber").val();
	    	        if(firstName != '' && lastName != '' && phoneNumber != ''){
	    	        		var actions = "<td><img alt='logo' height='20' width='20' src='<c:url value='/resources/images/delete-icon.jpeg'/>'>&nbsp;&nbsp;&nbsp;<img alt='logo' height='20' width='20' src='<c:url value='/resources/images/edit-icon.png'/>'></td>";
	    	        		var markup = "<tr><td>" + rowCount +"</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + phoneNumber + "</td>" + actions +"</tr>";
	 	        		$("#visitorQueue").append(markup);
	    	        }
	    	        $('#addVisitor-form').on('submit', function(e) { 
		    	    	   alert('stopping the form submission');
	        			e.preventDefault(); 
	            		$.post("/productiveWaitWeb/addVisitor",$('#addVisitor-form').serialize());
	            		resetFields();
		    	    });
	    	        
	    	    });
	    	    
	    	    $("#resetVisitor").click(function(){
	    	    	       resetFields();
	    	    	});
	    	            		
	    	    $("#addExistingVisitor").click(function(){
	    	    	    var MyRows = $('table#visitorQueue').find('tbody').find('tr');
	    	    	    for (var i = 0; i < MyRows.length; i++) {
	    	    	    var MyIndexValue = $(MyRows[i]).find('td:eq(3)').html();
	    	    	         if(MyIndexValue == selectedPhoneNumber){
	    	    	        	    alert('The person with phone # ' + selectedPhoneNumber + ' is already in Queue');
	    	    	        	    $("#phonentag").val("");
	    		    	        return
	    	    	         }
	    	    	    }
	    	    	    var rowCount = $('#visitorQueue >tbody >tr').length + 1;
	    	        var firstName = $("#phonentag").val().split(' ')[0];
	    	        var lastName = $("#phonentag").val().split(' ')[1];
	    	        var actions = "<td><img alt='logo' height='20' width='20' onclick='deteteVisitorFromQueue(" + selectedPhoneNumber +");' src='<c:url value='/resources/images/delete-icon.jpeg'/>'>&nbsp;&nbsp;&nbsp;<img alt='logo' height='20' width='20' src='<c:url value='/resources/images/edit-icon.png'/>'></td>";	
	    	        var markup = "<tr id='" + rowCount + "'><td>" + rowCount +"</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + selectedPhoneNumber + "</td>" + actions +"</tr>";
 	            $("#visitorQueue").append(markup);
	    	        $.get("/productiveWaitWeb/addExistingVisitor?phoneNumber=" + selectedPhoneNumber + "&firstName=" + firstName + "&lastName=" + lastName);
	    	        $("#phonentag").val("");
	    	        $('#addExistingVisitor').prop('disabled', true);
	    	        selectedPhoneNumber = '';
	    	        
		    		 $('#visitorQueue').Tabledit({
			    		 columns: {
			    		   identifier: [0, 'id'],                    
			    		   editable: [[1, 'First Name'], [2, 'Last Name'],[3, 'Phone #']]
			    		 }
		    		 });
	    	      	
	    	    });
	    	 }); 
	     
     </script>
</head><!--/head-->



<body id="home" class="homepage">

    <header id="header">
        <nav id="main-menu" class="navbar navbar-default navbar-fixed-top" role="banner">
            <div class="container">
<!--                 <div class="navbar-header"> -->
<!--                     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> -->
<!--                         <span class="sr-only">Toggle navigation</span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                     </button> -->
<!--                     <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a> -->
<!--                 </div> -->
				
                <div class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="scroll active"><a href="/productiveWaitWeb/logout">Log Out</a></li>                        
                    </ul>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
    </header><!--/header-->

 

      <!--/#login-->
    <section id="login">

        <div class="container-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="contact-form">
                            <h3>Search Visitor</h3>
<%--                             <form name="contact-form" method="post" action="/productiveWaitWeb/getVisitorByPhone"> --%>
                                <div class="form-group row">
                                      <div class="col-xs-6"><input id="phonentag" type="text" name="phonenumber" class="form-control autocomplete" placeholder="Search by Phone Number" required></div>
                                      <div class="col-xs-4"><button id="addExistingVisitor"type="submit" class="btn btn-primary" disabled>Add to Queue</button></div>
                                </div>
<%--                             </form> --%>
                            <h3>Add Visitor</h3>
                             <form:form id="addVisitor-form" modelAttribute="visitor" method="post" action="/productiveWaitWeb/addVisitor">
                             	<div class="form-group row">
                             	    <div class="col-xs-4"><input id="firstName" type="text" name="firstName" class="form-control" placeholder="First Name" required></div>
                             	    <div class="col-xs-4"><input id="lastName" type="text" name="lastName" class="form-control" placeholder="Last Name" required></div>   
                                 </div>
                                  <div class="form-group row">
								     <div class="col-xs-4"><input id="phoneNumber" type="text" name="phoneNumber" class="form-control" placeholder="Phone Number" required></div>  
								     <div class="col-xs-4"> <input id="email" type="text" name="email" class="form-control" placeholder="Email">	</div> 
                                	</div>
                                 <div class="form-group row">    
                                      <div class="col-xs-8"><input id="address" type="text" name="address" class="form-control" placeholder="Address"></div>
                                 </div> 
                                 <div class="form-group row">
                                    <div class="col-xs-4"><input id="city" type="text" name="city" class="form-control" placeholder="City"></div>
                                    <div class="col-xs-4">
	                                     <select id="state" name="state" class="form-control">
	                                             <option value=""></option>
												<option value="AL">Alabama</option>
												<option value="AK">Alaska</option>
												<option value="AZ">Arizona</option>
												<option value="AR">Arkansas</option>
												<option value="CA">California</option>
												<option value="CO">Colorado</option>
												<option value="CT">Connecticut</option>
												<option value="DE">Delaware</option>
												<option value="DC">District Of Columbia</option>
												<option value="FL">Florida</option>
												<option value="GA">Georgia</option>
												<option value="HI">Hawaii</option>
												<option value="ID">Idaho</option>
												<option value="IL">Illinois</option>
												<option value="IN">Indiana</option>
												<option value="IA">Iowa</option>
												<option value="KS">Kansas</option>
												<option value="KY">Kentucky</option>
												<option value="LA">Louisiana</option>
												<option value="ME">Maine</option>
												<option value="MD">Maryland</option>
												<option value="MA">Massachusetts</option>
												<option value="MI">Michigan</option>
												<option value="MN">Minnesota</option>
												<option value="MS">Mississippi</option>
												<option value="MO">Missouri</option>
												<option value="MT">Montana</option>
												<option value="NE">Nebraska</option>
												<option value="NV">Nevada</option>
												<option value="NH">New Hampshire</option>
												<option value="NJ">New Jersey</option>
												<option value="NM">New Mexico</option>
												<option value="NY">New York</option>
												<option value="NC">North Carolina</option>
												<option value="ND">North Dakota</option>
												<option value="OH">Ohio</option>
												<option value="OK">Oklahoma</option>
												<option value="OR">Oregon</option>
												<option value="PA">Pennsylvania</option>
												<option value="RI">Rhode Island</option>
												<option value="SC">South Carolina</option>
												<option value="SD">South Dakota</option>
												<option value="TN">Tennessee</option>
												<option value="TX">Texas</option>
												<option value="UT">Utah</option>
												<option value="VT">Vermont</option>
												<option value="VA">Virginia</option>
												<option value="WA">Washington</option>
												<option value="WV">West Virginia</option>
												<option value="WI">Wisconsin</option>
												<option value="WY">Wyoming</option>
									     </select>
 								     </div>
								 </div>
								 <div class="form-group row">
								     <div class="col-xs-4">  
								         <input id="country" type="text" name="country" class="form-control" placeholder="USA" value="USA" disabled>
								     </div>
								     <div class="col-xs-4"><input id="zip" type="text" name="zip" class="form-control" placeholder="Zip" ></div>
								 </div>
								 <div class="form-group row"> 
							 		 <div class="col-xs-4"><button id="resetVisitor" type="button" class="btn btn-primary">Reset</button></div>   
                                    	 <div class="col-xs-4"><button id="addVisitor" type="submit" class="btn btn-success">Add Visitor</button></div>
                                 </div> 
                             </form:form>
                        </div>
             		</div>
             	    <div class="col-sm-6">
	                     <h3>Active Queue</h3>
	                     <div class="container-fluid">      
<!-- 							  <table class="table table-hover table-condensed table-responsive"> -->
							<table id="visitorQueue" class="my-table">
							    <thead>
							      <tr>
							        <th>S.No.</th>
							        <th>First Name</th>
							        <th>Last Name</th>
							        <th>Phone #</th>
							        <th>Action</th>
							        
							      </tr>
							    </thead>
							    <tbody id="visitorQueue-tbody">
								   <c:forEach items="${visitorQueue}" var="visitor" varStatus="myIndex"> 
									  <tr name="visitorQueueRow">
									    <td>${visitor.visitorQueueIndex}</td>
									    <td>${visitor.firstName}</td>
									    <td>${visitor.lastName}</td>
									    <td>${visitor.phoneNumber}</td>
<%-- 									    <td><img alt="logo" height="20" width="20" class="delImage" onclick="deteteVisitorFromQueue($(this),'${visitor.visitorQueueIndex}','${visitor.phoneNumber}');" src="<c:url value="/resources/images/delete-icon.jpeg"/>">&nbsp;&nbsp;&nbsp;<img alt="logo" height="20" width="20" src="<c:url value="/resources/images/edit-icon.png"/>"></td> --%>
									        <td><img alt="logo" height="20" width="20" class="delImage" onclick="deteteVisitorFromQueue('${visitor.phoneNumber}');" src="<c:url value="/resources/images/delete-icon.jpeg"/>">&nbsp;&nbsp;&nbsp;<img alt="logo" height="20" width="20" src="<c:url value="/resources/images/edit-icon.png"/>"></td>
									  </tr>
									</c:forEach>
							    </tbody>
							  </table>
							</div>
	                		</div>
        		    </div>
            </div>
        </div>
   
    </section>
    <!--/#login-->

  
    <!--/#bottom-->

    <footer id="footer" style="position: fixed; bottom: 0px; width: 100%; height: 85px;">
        <div class="container">
            <div class="row">
                 <div class="col-sm-6">
                    &copy; 2017 Stallion Group LLC. Designed by Alpha
                </div>
                <div class="col-sm-6">
                    <ul class="social-icons">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                        <li><a href="#"><i class="fa fa-behance"></i></a></li>
                        <li><a href="#"><i class="fa fa-flickr"></i></a></li>
                        <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#"><i class="fa fa-github"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer><!--/#footer-->

 

</body>
</html>