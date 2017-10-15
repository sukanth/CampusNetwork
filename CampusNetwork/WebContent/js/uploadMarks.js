/**
 * 
 */

$(function() {
	
	$("#uploadMarks-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Cancel Appointments' === ui.newHeader.text() ){
				getAppointments();
			}     
	    }
 });
	
	$('#fromDate').datepicker({
        changeMonth: true,
        numberOfMonths: 1,
        minDate: new Date(),
        onClose: function (selectedDate) {
            $('#toDate').datepicker("option", "minDate", selectedDate);
        }
    });
    $('#toDate').datepicker({
        changeMonth: true,
        numberOfMonths: 1,
        onClose: function (selectedDate) {
            $('#fromDate').datepicker("option", "maxDate", selectedDate);
        }
    });
	
    $('#fromTime').timepicker({
        timeFormat: 'h:mm',
        interval: 60,
        minTime: '8',
        maxTime: '5:00pm',
        dynamic: true,
        dropdown: true,
        scrollbar: false
    });
    
    $('#toTime').timepicker({
        timeFormat: 'h:mm',
        interval: 60,
        minTime: '8',
        maxTime: '5:00pm',
        dynamic: true,
        dropdown: true,
        scrollbar: false
    });
	
	
	$('#assignedCourses').DataTable( {
        "paging":   false,
        "ordering": false,
        "info":     false,
        "bFilter" : false
    } );
	$('.getAssessement').on('click',function(){
		
		var input = {};
		input['courseId'] = $('#courseId').val();
		input['courseType']   = $('#assessmentType').val();
		$('#assessment').empty();
		if($('#courseId').val() != '' && $('#assessmentType').val() != '') {
			$.ajax({
				  type : "get",
				  url: getContextRoot()+"/entry/Performance/getAssessement.htm",
				  data:input,
				  dataType: 'json',
				  cache: false,
				  success: function(data){
					  $('#assessment').append('<option value="">--SELECT--</option>');
					  $.each(data.responseData.assessements,function(id,value){
						  $('#assessment').append('<option value="'+value+'">'+value+'</option>');
					  });
				  },
				  error: function(data){
					  
				  }
				});
		}else{
			 $('#assessment').append('<option value="">--SELECT--</option>');
		}
	});
	

	
	loadAppointmentsDT([]);
	
	$('.btnCancel').on('click',function(){
		
	});
	
});


function getAppointments(){
	var courseId = $(this).attr('courseId');
	$.ajax({
		  type : "GET",
		  url: getContextRoot()+"/entry/Appointments/getAppointments.htm",
		  data:{'courseId':courseId},
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

function deleteAppointments(input){
	$.ajax({
		  type : "get",
		  url: getContextRoot()+"/entry/Appointments/deleteAppointment.htm",
		  data:input,
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
            {'mData': 'appointmentFromDate', 'sType': 'string', "bVisible": true},
            {'mData': 'appointmentToDate', 'sType': 'string', 'bVisible': true},
            {'mData': 'fromTime', 'sType': 'string', 'bVisible': true},
            {'mData': 'toTime', 'sType': 'string', 'bVisible': true},
            {'mData': 'appointmentDuration', 'sType': 'string',"sClass": "text-center", 'bVisible': true},
            {'mData': 'maxAppointments', 'sType': 'string', "sClass": "text-center",'bVisible': true},
            {'mData': 'instructorId', 'sType': 'string', 'bVisible': true,
		            	 'mRender': function( data, type, row ) {
		                     return '<input type="button" ID="btnCancel" onclick=deleteAppointments('+JSON.stringify(row)+') value="Cancel" Class="btnCancel btn btn-lg btn-primary btn-block"style="width:100px;" />'; 
		                 } 
            }
            ]
    });
}