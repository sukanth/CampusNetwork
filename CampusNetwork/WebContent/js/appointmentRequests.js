/**
 * 
 */

$(function() {
	
loadAppointmentsDT([]);
	
getAppointments();

	
});


function getAppointments(){
	$.ajax({
		  type : "GET",
		  url: getContextRoot()+"/entry/Appointments/getAppoinmentRequests.htm",
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

function updateRequestedAppointments(apptId,status){
	$.ajax({
		  type : "get",
		  url: getContextRoot()+"/entry/Appointments/updateRequestedAppointments.htm",
		  data:{'appointmentId':apptId,'status':status},
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
        	{'mData': 'studentName', 'sType': 'string', 'bVisible': true},
            {'mData': 'appointmentDate', 'sType': 'string', "bVisible": true},
            {'mData': 'appointmentTime', 'sType': 'string', 'bVisible': true},
            {'mData': 'appointmentType', 'sType': 'string', 'bVisible': true},
            {'mData': 'desc', 'sType': 'string',"sClass": "text-center", 'bVisible': true},
            {'mData': 'appointmentId', 'sType': 'string', "sClass": "text-center",'bVisible': true,
		            	 'mRender': function( data, type, row ) {
		                     	txt ='<input type="button" onclick=updateRequestedAppointments('+data+',"Approved") value="Approve" Class="btn btn-success"style="width:100px;" />&nbsp;&nbsp;';
		                     	txt = txt +'<input type="button" onclick=updateRequestedAppointments('+data+',"Rejected") value="Reject" Class="btn  btn-danger"style="width:100px;" />';
		                     return txt;
		                 } 
            }
            ]
    });
}