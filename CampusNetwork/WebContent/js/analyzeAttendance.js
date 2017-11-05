/**
 * 
 */

$(function() {
	$('#chartView').hide();
	loadAttendanceDT([]);
	$("#analyze-attendance-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Individual Attendance' === ui.newHeader.text() ){
				$('#divIndividualAttendance').hide();
			}
			if('Class Attendance' === ui.newHeader.text() ){
				$('#chartView').hide();
			}
	    }
	});
	
	$('#courseId').on('change',function(){
		var input = {};
		input['courseId'] = $('#courseId').val();
		$('#attendanceDate').empty();
		if($('#courseId').val() != '') {
			$.ajax({
				  type : "get",
				  url: getContextRoot()+"/entry/Attendance/getAttendanceDates.htm",
				  data:input,
				  dataType: 'json',
				  cache: false,
				  success: function(data){
					  $('#attendanceDate').append('<option value="">--SELECT--</option>');
					  $.each(data.responseData.attendanceDates,function(id,value){
						  $('#attendanceDate').append('<option value="'+value+'">'+value+'</option>');
					  });
				  },
				  error: function(data){
					  
				  }
				});
		}else{
			 $('#attendanceDate').append('<option value="">--SELECT--</option>');
		}
	});
	
$('#btnSubmit').on('click',function(){
		$('#chartView').hide();
		var input = {};
		input['courseId'] = $('#courseId').val();
		input['attendanceDate']   = $('#attendanceDate').val();

		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Attendance/getCourseAttendance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(response){
				  var data = response.responseData.range;
				  BarChart(data.range1,data.range2);
				  $('#lblCourseId').text($('#courseId').val());
				  $('#lblStrength').text(response.responseData.courseStrength);
			  },
			  error: function(data){
				  
			  }
			});
	});
	
$('#pcourseId').on('change',function(){
	var input = {};
	input['courseId'] = $('#pcourseId').val();
	$('#ssoId').empty();
	if($('#pcourseId').val() != '') {
		$.ajax({
			  type : "get",
			  url: getContextRoot()+"/entry/Performance/getStudents.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  $('#ssoId').append('<option value="">--SELECT--</option>');
				  $.each(data.responseData.students,function(id,value){
					  $('#ssoId').append('<option value="'+value.sso+'">'+value.studentName+'</option>');
				  });
			  },
			  error: function(data){
				  
			  }
			});
	}else{
		$('#ssoId').append('<option value="">--SELECT--</option>');
	}
});

	$('#viewAttendance').on('click',function(){
		$('#divIndividualAttendance').hide();
		var input = {};
		input['courseId'] = $('#pcourseId').val();
		input['sso']   = $('#ssoId').val();
		
		$.ajax({
			  type : "GET",
			  url: getContextRoot()+"/entry/Attendance/getIndividualIAttendance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  individualAttendance.destroy();
				  $('#divIndividualAttendance').show();
				  loadAttendanceDT(data.responseData.attendances);
			  },
			  error: function(data){
				  
			  }
			});
	});

	
});


function BarChart(t1, t2) {
	$('#chartView').show();
    var t1 = t1;
    var t2 = t2;
    var chart = AmCharts.makeChart("chartdiv", {
        "theme": "none",
        "type": "serial",
        "startDuration": 2,
        "dataProvider": [{
            "country": "Present",
            "visits": t1,
            "color": "#00FF00"
        }, {
            "country": "Absent",
            "visits": t2,
            "color": "#FF0F00"
        }],
        "valueAxes": [{
            "position": "left",
            "axisAlpha": 0,
            "gridAlpha": 0
        }],
        "graphs": [{
            "balloonText": "[[category]]: <b>[[value]]</b>",
            "colorField": "color",
            "fillAlphas": 0.85,
            "lineAlpha": 0.1,
            "type": "column",
            "topRadius": 1,
            "valueField": "visits"
        }],
        "depth3D": 40,
        "angle": 30,
        "chartCursor": {
            "categoryBalloonEnabled": false,
            "cursorAlpha": 0,
            "zoomable": false
        },
        "categoryField": "country",
        "categoryAxis": {
            "gridPosition": "start",
            "axisAlpha": 0,
            "gridAlpha": 0

        },
        "exportConfig": {
            "menuTop": "20px",
            "menuRight": "20px",
            "menuItems": [{
                "icon": '/lib/3/images/export.png',
                "format": 'png'
            }]
        }
    }, 0);
}

function loadAttendanceDT(data){
	individualAttendance = $('#individualAttendance').DataTable( {
		"bProcessing": true,
		"aaData" : data,
        "paging":   true,
        "ordering": false,
        "info":     true,
        "bFilter" : false,
        "bLengthChange": false,
        "aoColumns": [
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