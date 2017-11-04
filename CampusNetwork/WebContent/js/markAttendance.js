/**
 * 
 */

$(function() {
	setLocation();
	loadAttendancesDT([]);
	$("#setAttendance-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Mark Attendance' === ui.newHeader.text() ){
				setLocation();
			}
			if('Check Attendance' === ui.newHeader.text() ){
				getAttendances();
			} 
	    }
 });

    
$('#markAttendance').on('click',function(){
		
		var input = {};
		input['courseId']   = $('#courseId').val();
		input['attendanceDate'] = $('#attendanceDate').val();
		input['latitude'] = $('#latitude').val();
		input['longitude'] = $('#longitude').val();
		input['randomCode'] = $('#attendanceCode').val();
		
		$('.errMsg').hide();
		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Attendance/markAttendance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  loadResponse(data,"Attendance Marked Successfully.");
			  },
			  error: function(data){
				  
			  }
			});
	});
	
});

function setLocation() {
	$('.errMsg').hide();
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            $('#latitude').val(latitude);
            var longitude = position.coords.longitude;
            $('#longitude').val(longitude);
        });
    } else {
        alert("Geolocation API is not supported in your browser.");
    }
    var d = new Date();
    var day = d.getDate();
    var monthIndex = d.getMonth();
    var year = d.getFullYear();
    
    var datestring = ("0"+(d.getMonth()+1)).slice(-2)+ "/" + ("0" + d.getDate()).slice(-2) + "/" + d.getFullYear();
    
    $('#attendanceDate').val(datestring);
}

function getAttendances(){
	$.ajax({
		  type : "GET",
		  url: getContextRoot()+"/entry/Attendance/getIndividualAttendance.htm",
		  data:{},
		  dataType: 'json',
		  cache: false,
		  success: function(data){
			  Attendances.destroy();
			  loadAttendancesDT(data.responseData.attendances);
		  },
		  error: function(data){
			  
		  }
		});
}

function deleteAttendance(input){
	$.ajax({
		  type : "get",
		  url: getContextRoot()+"/entry/Attendance/deleteAttendance.htm",
		  data:input,
		  dataType: 'json',
		  cache: false,
		  success: function(data){
			  getAttendances();
		  },
		  error: function(data){
			  
		  }
		});
}


function loadAttendancesDT(data){
	Attendances = $('#Attendances').DataTable( {
		"bProcessing": true,
		"aaData" : data,
        "paging":   true,
        "ordering": false,
        "info":     true,
        "bFilter" : false,
        "bLengthChange": false,
        "aoColumns": [
            {'mData': 'courseId', 'sType': 'string', "bVisible": true},
            {'mData': 'attendanceDate', 'sType': 'string', 'bVisible': true},
            {'mData': 'attendanceStatus', 'sType': 'string', 'bVisible': true,
            	'mRender': function( data, type, row ) {
                    var txt = '';
                    if(data === 'Present')
                    	txt ='<Span style="font-style: italic;color: green;font-weight: bold;">'+data+'</span>';
                    else if(data === 'Absent')
                    	txt ='<Span style="font-style: italic;color: red;font-weight: bold;">'+data+'</span>';
                    return txt;
                }},
            ]
    });
}


