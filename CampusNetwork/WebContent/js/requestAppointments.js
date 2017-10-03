/**
 * 
 */

$(function() {
	
	$("#student-appointments-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Check Status' === ui.newHeader.text() ){
				getAppointments();
			}     
	    }
 });
	
	$('#appointmentDate').datepicker({
        changeMonth: true,
        numberOfMonths: 1,
        minDate: new Date()
    });
	
$('.getApptTime').on('change',function(){
		
		var input = {};
		input['appointmentDate'] = $('#appointmentDate').val();
		input['instructorId']   = $('#instructor').val();
		$('#appointmentTime').empty();
		if($(this).val() != '' && $('#appointmentDate').val() != '') {
			$.ajax({
				  type : "GET",
				  url: getContextRoot()+"/entry/Appointments/getAppointmentTime.htm",
				  data:input,
				  dataType: 'json',
				  cache: false,
				  success: function(data){
					  $('#appointmentTime').append('<option value="">--SELECT--</option>');
					  $.each(data.responseData.getAppointmentTime,function(id,value){
						  $('#appointmentTime').append('<option value="'+value.key +' to '+value.value+'">'+value.key +' to '+value.value+'</option>');
					  });
					
				  },
				  error: function(data){
					  
				  }
				});
		}else{
			 $('#appointmentTime').append('<option value="">--SELECT--</option>');
		}
		
	});
	
	$('#assignedCourses').DataTable( {
        "paging":   false,
        "ordering": false,
        "info":     false,
        "bFilter" : false
    } );
	
	
	$('#btnSubmit').on('click',function(){
		
		var input = {};
		input['appointmentDate'] = $('#appointmentDate').val();
		input['instructorId']   = $('#instructor').val();
		input['appointmentTime'] = $('#appointmentTime').val();
		input['appointmentType']   = $('#appointmentType').val();
		input['desc'] = $('#description').val();
		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Appointments/requestAppointment.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				alert(data.responseData.status);
			  },
			  error: function(data){
				  
			  }
			});
	});
	

	
	loadAppointmentsDT([]);
	
	$('.btnCancel').on('click',function(){
		
	});
	
});


function getAppointments(){
	$.ajax({
		  type : "GET",
		  url: getContextRoot()+"/entry/Appointments/getRequestedAppointments.htm",
		  data:{},
		  dataType: 'json',
		  cache: false,
		  success: function(data){
			  Appointments.destroy();
			  loadAppointmentsDT(data.responseData.appointments);
		  },
		  error: function(data){
			  
		  }
		});
}

function deleteRequestedAppointments(input,canCancel){
	if(canCancel == 'N')
		return false;
	
	$.ajax({
		  type : "get",
		  url: getContextRoot()+"/entry/Appointments/deleteRequestedAppointment.htm",
		  data:{'appointmentId':input},
		  dataType: 'json',
		  cache: false,
		  success: function(data){
			  getAppointments();
		  },
		  error: function(data){
			  
		  }
		});
}


function loadAppointmentsDT(data){
	Appointments = $('#Appointments').DataTable( {
		"bProcessing": true,
		"aaData" : data,
        "paging":   true,
        "ordering": false,
        "info":     true,
        "bFilter" : false,
        "bLengthChange": false,
        "aoColumns": [
            {'mData': 'appointmentDate', 'sType': 'string', "bVisible": true},
            {'mData': 'instructorName', 'sType': 'string', 'bVisible': true},
            {'mData': 'appointmentTime', 'sType': 'string', 'bVisible': true},
            {'mData': 'appointmentType', 'sType': 'string', 'bVisible': true},
            {'mData': 'desc', 'sType': 'string',"sClass": "text-center", 'bVisible': true},
            {'mData': 'status', 'sType': 'string', "sClass": "text-center",'bVisible': true,
            	'mRender': function( data, type, row ) {
                    var txt = '';
                    if(data == 'Pending')
                    	txt ='<Span style="font-style: italic;color: darkorange;font-weight: bold;">'+data+'</span>';
                    else if(data == 'Approved')
                    	txt ='<Span style="font-style: italic;color: green;font-weight: bold;">'+data+'</span>';
                    else if(data == 'Rejected')
                    	txt ='<Span style="font-style: italic;color: red;font-weight: bold;">'+data+'</span>';
                    return txt;
                }
            },
            {'mData': 'appointmentId', 'sType': 'string', 'bVisible': true,
		            	 'mRender': function( data, type, row ) {
		            		 var txt = '';
		                     if(row.status != 'Pending')
		                     	txt ='<input type="button" ID="btnCancel" onclick=deleteRequestedAppointments('+data+',"N") value="Cancel" Class="btnCancel btn btn-lg btn-primary btn-block disabled"style="width:100px;" />';
		                     else
		                     	txt ='<input type="button" ID="btnCancel" onclick=deleteRequestedAppointments('+data+') value="Cancel" Class="btnCancel btn btn-lg btn-primary btn-block"style="width:100px;" />';
		                     return txt;
		                 } 
            }
            ]
    });
}