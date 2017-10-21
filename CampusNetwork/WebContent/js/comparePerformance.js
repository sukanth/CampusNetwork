/**
 * 
 */

$(function() {
	$('#chartView').hide();
	$("#Compare-performance-accordion").accordion({ 
		heightStyle: "content"
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
		input['topicName']   = $('#assessment').val();
		
		$.ajax({
			  type : "POST",
			  url: getContextRoot()+"/entry/Performance/getMyPerformanceRange.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(response){
				  var data = response.responseData.range;
				  BarChart(data.range1,data.range2,data.range3,data.range4,data.range5);
				  $('#lblCourseId').text($('#courseId').val());
				  $('#lblStrength').text(response.responseData.courseStrength);
				  $('#lblPosition').text(response.responseData.position);
				  $('#lblPercentage').text(response.responseData.percentage);
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
