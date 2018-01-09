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
     
	     $(document).ready(function(){
// 	    	    $("#visitorQueue-tbody").sortable();
// 	    	    $("#visitorQueue-tbody").disableSelection();
	    	    	    
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
	    	    
	    	    $("#addVisitor").click(function(){
	    	        //$.get("/productiveWaitWeb/addVisitor");
	    	    });
	    	    $("#addExistingVisitor").click(function(){
	    	    	    var rowCount = $('#visitorQueue >tbody >tr').length + 1;
	    	        var firstName = $("#phonentag").val().split(' ')[0];
	    	        var lastName = $("#phonentag").val().split(' ')[1];
 	            var markup = "<tr><td>" + rowCount +"</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + selectedPhoneNumber + "</td></tr>";
 	            $("#visitorQueue").append(markup);
	    	        $.get("/productiveWaitWeb/addExistingVisitor?phoneNumber=" + selectedPhoneNumber + "&firstName=" + firstName + "&lastName=" + lastName);
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
                             <form:form method = "POST" action = "/productiveWaitWeb/addVisitor" modelAttribute="visitor">
                             	<div class="form-group row">
                             	    <div class="col-xs-4"><input type="text" name="firstName" class="form-control" placeholder="First Name" required></div>
                             	    <div class="col-xs-4"><input type="text" name="lastName" class="form-control" placeholder="Last Name" required></div>   
                                 </div>
                                  <div class="form-group row">
								     <div class="col-xs-4"><input type="text" name="phoneNumber" class="form-control" placeholder="Phone Number" required></div>  
								     <div class="col-xs-4"> <input type="text" name="email" class="form-control" placeholder="Email">	</div> 
                                	</div>
                                 <div class="form-group row">    
                                      <div class="col-xs-8"><input type="text" name="address" class="form-control" placeholder="Address"></div>
                                 </div> 
                                 <div class="form-group row">
                                    <div class="col-xs-4"><input type="text" name="city" class="form-control" placeholder="City"></div>
                                    <div class="col-xs-4">
	                                     <select name="State" class="form-control">
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
									     <select class="form-control" required>
											<option value="AFG">Afghanistan</option>
											<option value="ALA">�land Islands</option>
											<option value="ALB">Albania</option>
											<option value="DZA">Algeria</option>
											<option value="ASM">American Samoa</option>
											<option value="AND">Andorra</option>
											<option value="AGO">Angola</option>
											<option value="AIA">Anguilla</option>
											<option value="ATA">Antarctica</option>
											<option value="ATG">Antigua and Barbuda</option>
											<option value="ARG">Argentina</option>
											<option value="ARM">Armenia</option>
											<option value="ABW">Aruba</option>
											<option value="AUS">Australia</option>
											<option value="AUT">Austria</option>
											<option value="AZE">Azerbaijan</option>
											<option value="BHS">Bahamas</option>
											<option value="BHR">Bahrain</option>
											<option value="BGD">Bangladesh</option>
											<option value="BRB">Barbados</option>
											<option value="BLR">Belarus</option>
											<option value="BEL">Belgium</option>
											<option value="BLZ">Belize</option>
											<option value="BEN">Benin</option>
											<option value="BMU">Bermuda</option>
											<option value="BTN">Bhutan</option>
											<option value="BOL">Bolivia, Plurinational State of</option>
											<option value="BES">Bonaire, Sint Eustatius and Saba</option>
											<option value="BIH">Bosnia and Herzegovina</option>
											<option value="BWA">Botswana</option>
											<option value="BVT">Bouvet Island</option>
											<option value="BRA">Brazil</option>
											<option value="IOT">British Indian Ocean Territory</option>
											<option value="BRN">Brunei Darussalam</option>
											<option value="BGR">Bulgaria</option>
											<option value="BFA">Burkina Faso</option>
											<option value="BDI">Burundi</option>
											<option value="KHM">Cambodia</option>
											<option value="CMR">Cameroon</option>
											<option value="CAN">Canada</option>
											<option value="CPV">Cape Verde</option>
											<option value="CYM">Cayman Islands</option>
											<option value="CAF">Central African Republic</option>
											<option value="TCD">Chad</option>
											<option value="CHL">Chile</option>
											<option value="CHN">China</option>
											<option value="CXR">Christmas Island</option>
											<option value="CCK">Cocos (Keeling) Islands</option>
											<option value="COL">Colombia</option>
											<option value="COM">Comoros</option>
											<option value="COG">Congo</option>
											<option value="COD">Congo, the Democratic Republic of the</option>
											<option value="COK">Cook Islands</option>
											<option value="CRI">Costa Rica</option>
											<option value="CIV">C�te d'Ivoire</option>
											<option value="HRV">Croatia</option>
											<option value="CUB">Cuba</option>
											<option value="CUW">Cura�ao</option>
											<option value="CYP">Cyprus</option>
											<option value="CZE">Czech Republic</option>
											<option value="DNK">Denmark</option>
											<option value="DJI">Djibouti</option>
											<option value="DMA">Dominica</option>
											<option value="DOM">Dominican Republic</option>
											<option value="ECU">Ecuador</option>
											<option value="EGY">Egypt</option>
											<option value="SLV">El Salvador</option>
											<option value="GNQ">Equatorial Guinea</option>
											<option value="ERI">Eritrea</option>
											<option value="EST">Estonia</option>
											<option value="ETH">Ethiopia</option>
											<option value="FLK">Falkland Islands (Malvinas)</option>
											<option value="FRO">Faroe Islands</option>
											<option value="FJI">Fiji</option>
											<option value="FIN">Finland</option>
											<option value="FRA">France</option>
											<option value="GUF">French Guiana</option>
											<option value="PYF">French Polynesia</option>
											<option value="ATF">French Southern Territories</option>
											<option value="GAB">Gabon</option>
											<option value="GMB">Gambia</option>
											<option value="GEO">Georgia</option>
											<option value="DEU">Germany</option>
											<option value="GHA">Ghana</option>
											<option value="GIB">Gibraltar</option>
											<option value="GRC">Greece</option>
											<option value="GRL">Greenland</option>
											<option value="GRD">Grenada</option>
											<option value="GLP">Guadeloupe</option>
											<option value="GUM">Guam</option>
											<option value="GTM">Guatemala</option>
											<option value="GGY">Guernsey</option>
											<option value="GIN">Guinea</option>
											<option value="GNB">Guinea-Bissau</option>
											<option value="GUY">Guyana</option>
											<option value="HTI">Haiti</option>
											<option value="HMD">Heard Island and McDonald Islands</option>
											<option value="VAT">Holy See (Vatican City State)</option>
											<option value="HND">Honduras</option>
											<option value="HKG">Hong Kong</option>
											<option value="HUN">Hungary</option>
											<option value="ISL">Iceland</option>
											<option value="IND">India</option>
											<option value="IDN">Indonesia</option>
											<option value="IRN">Iran, Islamic Republic of</option>
											<option value="IRQ">Iraq</option>
											<option value="IRL">Ireland</option>
											<option value="IMN">Isle of Man</option>
											<option value="ISR">Israel</option>
											<option value="ITA">Italy</option>
											<option value="JAM">Jamaica</option>
											<option value="JPN">Japan</option>
											<option value="JEY">Jersey</option>
											<option value="JOR">Jordan</option>
											<option value="KAZ">Kazakhstan</option>
											<option value="KEN">Kenya</option>
											<option value="KIR">Kiribati</option>
											<option value="PRK">Korea, Democratic People's Republic of</option>
											<option value="KOR">Korea, Republic of</option>
											<option value="KWT">Kuwait</option>
											<option value="KGZ">Kyrgyzstan</option>
											<option value="LAO">Lao People's Democratic Republic</option>
											<option value="LVA">Latvia</option>
											<option value="LBN">Lebanon</option>
											<option value="LSO">Lesotho</option>
											<option value="LBR">Liberia</option>
											<option value="LBY">Libya</option>
											<option value="LIE">Liechtenstein</option>
											<option value="LTU">Lithuania</option>
											<option value="LUX">Luxembourg</option>
											<option value="MAC">Macao</option>
											<option value="MKD">Macedonia, the former Yugoslav Republic of</option>
											<option value="MDG">Madagascar</option>
											<option value="MWI">Malawi</option>
											<option value="MYS">Malaysia</option>
											<option value="MDV">Maldives</option>
											<option value="MLI">Mali</option>
											<option value="MLT">Malta</option>
											<option value="MHL">Marshall Islands</option>
											<option value="MTQ">Martinique</option>
											<option value="MRT">Mauritania</option>
											<option value="MUS">Mauritius</option>
											<option value="MYT">Mayotte</option>
											<option value="MEX">Mexico</option>
											<option value="FSM">Micronesia, Federated States of</option>
											<option value="MDA">Moldova, Republic of</option>
											<option value="MCO">Monaco</option>
											<option value="MNG">Mongolia</option>
											<option value="MNE">Montenegro</option>
											<option value="MSR">Montserrat</option>
											<option value="MAR">Morocco</option>
											<option value="MOZ">Mozambique</option>
											<option value="MMR">Myanmar</option>
											<option value="NAM">Namibia</option>
											<option value="NRU">Nauru</option>
											<option value="NPL">Nepal</option>
											<option value="NLD">Netherlands</option>
											<option value="NCL">New Caledonia</option>
											<option value="NZL">New Zealand</option>
											<option value="NIC">Nicaragua</option>
											<option value="NER">Niger</option>
											<option value="NGA">Nigeria</option>
											<option value="NIU">Niue</option>
											<option value="NFK">Norfolk Island</option>
											<option value="MNP">Northern Mariana Islands</option>
											<option value="NOR">Norway</option>
											<option value="OMN">Oman</option>
											<option value="PAK">Pakistan</option>
											<option value="PLW">Palau</option>
											<option value="PSE">Palestinian Territory, Occupied</option>
											<option value="PAN">Panama</option>
											<option value="PNG">Papua New Guinea</option>
											<option value="PRY">Paraguay</option>
											<option value="PER">Peru</option>
											<option value="PHL">Philippines</option>
											<option value="PCN">Pitcairn</option>
											<option value="POL">Poland</option>
											<option value="PRT">Portugal</option>
											<option value="PRI">Puerto Rico</option>
											<option value="QAT">Qatar</option>
											<option value="REU">R�union</option>
											<option value="ROU">Romania</option>
											<option value="RUS">Russian Federation</option>
											<option value="RWA">Rwanda</option>
											<option value="BLM">Saint Barth�lemy</option>
											<option value="SHN">Saint Helena, Ascension and Tristan da Cunha</option>
											<option value="KNA">Saint Kitts and Nevis</option>
											<option value="LCA">Saint Lucia</option>
											<option value="MAF">Saint Martin (French part)</option>
											<option value="SPM">Saint Pierre and Miquelon</option>
											<option value="VCT">Saint Vincent and the Grenadines</option>
											<option value="WSM">Samoa</option>
											<option value="SMR">San Marino</option>
											<option value="STP">Sao Tome and Principe</option>
											<option value="SAU">Saudi Arabia</option>
											<option value="SEN">Senegal</option>
											<option value="SRB">Serbia</option>
											<option value="SYC">Seychelles</option>
											<option value="SLE">Sierra Leone</option>
											<option value="SGP">Singapore</option>
											<option value="SXM">Sint Maarten (Dutch part)</option>
											<option value="SVK">Slovakia</option>
											<option value="SVN">Slovenia</option>
											<option value="SLB">Solomon Islands</option>
											<option value="SOM">Somalia</option>
											<option value="ZAF">South Africa</option>
											<option value="SGS">South Georgia and the South Sandwich Islands</option>
											<option value="SSD">South Sudan</option>
											<option value="ESP">Spain</option>
											<option value="LKA">Sri Lanka</option>
											<option value="SDN">Sudan</option>
											<option value="SUR">Suriname</option>
											<option value="SJM">Svalbard and Jan Mayen</option>
											<option value="SWZ">Swaziland</option>
											<option value="SWE">Sweden</option>
											<option value="CHE">Switzerland</option>
											<option value="SYR">Syrian Arab Republic</option>
											<option value="TWN">Taiwan, Province of China</option>
											<option value="TJK">Tajikistan</option>
											<option value="TZA">Tanzania, United Republic of</option>
											<option value="THA">Thailand</option>
											<option value="TLS">Timor-Leste</option>
											<option value="TGO">Togo</option>
											<option value="TKL">Tokelau</option>
											<option value="TON">Tonga</option>
											<option value="TTO">Trinidad and Tobago</option>
											<option value="TUN">Tunisia</option>
											<option value="TUR">Turkey</option>
											<option value="TKM">Turkmenistan</option>
											<option value="TCA">Turks and Caicos Islands</option>
											<option value="TUV">Tuvalu</option>
											<option value="UGA">Uganda</option>
											<option value="UKR">Ukraine</option>
											<option value="ARE">United Arab Emirates</option>
											<option value="GBR">United Kingdom</option>
											<option value="USA" selected="selected">United States</option>
											<option value="UMI">United States Minor Outlying Islands</option>
											<option value="URY">Uruguay</option>
											<option value="UZB">Uzbekistan</option>
											<option value="VUT">Vanuatu</option>
											<option value="VEN">Venezuela, Bolivarian Republic of</option>
											<option value="VNM">Viet Nam</option>
											<option value="VGB">Virgin Islands, British</option>
											<option value="VIR">Virgin Islands, U.S.</option>
											<option value="WLF">Wallis and Futuna</option>
											<option value="ESH">Western Sahara</option>
											<option value="YEM">Yemen</option>
											<option value="ZMB">Zambia</option>
											<option value="ZWE">Zimbabwe</option>
										 </select>
								     </div>
								     <div class="col-xs-4"><input type="text" name="zip" class="form-control" placeholder="Zip" ></div>
								 </div>
								 <div class="form-group row"> 
							 		 <div class="col-xs-4"><button id="resetVisitor" type="submit" class="btn btn-primary">Reset</button></div>   
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
							        <th>Firstname</th>
							        <th>Lastname</th>
							        <th>Phone #</th>
							        <th>Action</th>
							        
							      </tr>
							    </thead>
							    <tbody id="visitorQueue-tbody">
							      <tr>
							      	<td>1</td>
							        <td>John</td>
							        <td>Doe</td>
							        <td>9085876339</td>
							      </tr>
							      <tr>
							      	<td>2</td>
							        <td>Mary</td>
							        <td>Moe</td>
							        <td>7326586109</td>
							      </tr>
							      <tr>
							      	<td>3</td>
							        <td>July</td>
							        <td>Dooley</td>
							        <td>9085876338</td>
							      </tr>
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