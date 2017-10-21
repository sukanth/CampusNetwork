/**
 * 
 */

$(function() {
	$('.assessement').hide();
	$('#chartView').hide();
	loadPerformanceDT([]);
	$("#analyze-performance-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Individual Performance' === ui.newHeader.text() ){
				$('#divIndividualPerformance').hide();
			}
			if('Class Performance' === ui.newHeader.text() ){
				$('#chartView').hide();
			}
	    }
	});

	$("#analysisType").on('change',function(){
		var analysisType = $(this).val();
			if(analysisType === 'Individual')
				$('.assessement').show();
			else
				$('.assessement').hide();
	});
	
	$('.getAssessement').on('change',function(){
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
	
$('#btnSubmit').on('click',function(){
		$('#chartView').hide();
		var input = {};
		input['courseId'] = $('#courseId').val();
		
		var analysisType = $('#analysisType').val();
		if(analysisType === 'Individual'){
			input['topicName']   = $('#assessment').val();
		}
		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Performance/getPerformanceRange.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(response){
				  var data = response.responseData.range;
				  BarChart(data.range1,data.range2,data.range3,data.range4,data.range5);
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

	$('#viewPerformance').on('click',function(){
		$('#divIndividualPerformance').hide();
		var input = {};
		input['courseId'] = $('#pcourseId').val();
		input['sso']   = $('#ssoId').val();
		
		$.ajax({
			  type : "GET",
			  url: getContextRoot()+"/entry/Performance/getIndividualPerformance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  individualPerformance.destroy();
				  $('#divIndividualPerformance').show();
				  loadPerformanceDT(data.responseData.marks);
			  },
			  error: function(data){
				  
			  }
			});
	});

	
});


function BarChart(t1, t2, t3, t4, t5) {
	$('#chartView').show();
    var chart = AmCharts.makeChart("chartdiv", {
        "theme": "none",
        "type": "serial",
        "startDuration": 2,
        "dataProvider": [{
            "country": "90-100",
            "visits": t1,
            "color": "#FF0F00"
        }, {
            "country": "80-90",
            "visits": t2,
            "color": "#FF6600"
        }, {
            "country": "70-80",
            "visits": t3,
            "color": "#FF9E01"
        }, {
            "country": "60-70",
            "visits": t4,
            "color": "#FCD202"
        }, {
            "country": "below 60",
            "visits": t5,
            "color": "#F8FF01"
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
	

function loadPerformanceDT(data){
	individualPerformance = $('#individualPerformance').DataTable( {
		"bProcessing": true,
		"aaData" : data,
        "paging":   false,
        "ordering": false,
        "info":     false,
        "bFilter" : false,
        "bLengthChange": false,
        "aoColumns": [
            {'mData': 'courseId', 'sType': 'string', "bVisible": true},
            {'mData': 'topicName', 'sType': 'string', 'bVisible': true},
            {'mData': 'totalmarks', 'sType': 'string', 'bVisible': true},
            {'mData': 'marksObtained', 'sType': 'string', 'bVisible': true},
            {'mData': 'percentage', 'sType': 'string',"sClass": "text-center", 'bVisible': true,
            	'mRender': function( data, type, row ) {
                    var txt = '';
                    if(data > 50)
                    	txt ='<Span style="font-style: italic;color: green;font-weight: bold;">'+data+'</span>';
                    else if(data < 50)
                    	txt ='<Span style="font-style: italic;color: red;font-weight: bold;">'+data+'</span>';
                    return txt;
                }},
            {'mData': 'comments', 'sType': 'string', "sClass": "text-center",'bVisible': true}
            ]
    });
}