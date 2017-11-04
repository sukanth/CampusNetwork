/**
 * 
 */

$(function() {
	setLocation();
	loadAttendancesDT([]);
	$("#setAttendance-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Set Attendance' === ui.newHeader.text() ){
				setLocation();
				$('.errMsg').hide();
			}
			if('List of Attendance' === ui.newHeader.text() ){
				getAttendances();
				$('.errMsg').hide();
			} 
	    }
 });
	
	$('#attendanceDate').datepicker({
        changeMonth: true,
        numberOfMonths: 1,
        minDate: new Date(),
        onClose: function (selectedDate) {
           
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
    
$('#setAttendance').on('click',function(){
		
		var input = {};
		input['courseId']   = $('#courseId').val();
		input['attendanceDate'] = $('#attendanceDate').val();
		input['startTime'] = $('#fromTime').val();
		input['endTime']   = $('#toTime').val();
		input['latitude'] = $('#latitude').val();
		input['longitude'] = $('#longitude').val();
		$('.errMsg').hide();
		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Attendance/setAttendance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  loadResponse(data,"Attendance Created Successfully.");
			  },
			  error: function(data){
				  
			  }
			});
	});
	
});

function setLocation() {
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
    
}

function getAttendances(){
	$.ajax({
		  type : "GET",
		  url: getContextRoot()+"/entry/Attendance/getAttendances.htm",
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

function generateAttendance(input){
	$('.errMsg').hide();
	$.ajax({
		  type : "get",
		  url: getContextRoot()+"/entry/Attendance/generateAttendance.htm",
		  data:input,
		  dataType: 'json',
		  cache: false,
		  success: function(data){
			  loadResponse(data,"Attendance Generated Successfully.");
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
            {'mData': 'latitude', 'sType': 'string', 'bVisible': true},
            {'mData': 'longitude', 'sType': 'string', 'bVisible': true},
            {'mData': 'randomCode', 'sType': 'string',"sClass": "text-center", 'bVisible': true},
            {'mData': 'startTime', 'sType': 'string', "sClass": "text-center",'bVisible': true},
            {'mData': 'endTime', 'sType': 'string', "sClass": "text-center",'bVisible': true},
            {'mData': 'isGenerated', 'sType': 'string', 'bVisible': true,
		            	 'mRender': function( data, type, row ) {
		            		 var txt = '';
		                     if(data === 'Y')
		                     	txt ='<input type="button" ID="btnCancel"  value="Generate Attendance" Class="btnCancel btn btn-lg btn-primary btn-block disabled"style="width:190px;" />';
		                     else 
		                     	txt = '<input type="button" ID="btnCancel" onclick=generateAttendance('+JSON.stringify(row)+') value="Generate Attendance" Class="btnCancel btn btn-lg btn-primary btn-block"style="width:190px;" />';
		                     return txt;
		                 } 
            }
            ,
            {'mData': 'attendanceId', 'sType': 'string', 'bVisible': true,
		            	 'mRender': function( data, type, row ) {
		                     return '<input type="button" ID="btnCancel" onclick=deleteAttendance('+JSON.stringify(row)+') value="Delete" Class="btnCancel btn btn-lg btn-primary btn-block"style="width:100px;" />'; 
		                 } 
            }
            ]
    });
}


