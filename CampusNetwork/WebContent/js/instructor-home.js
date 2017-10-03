/**
 * 
 */

$(function() {
	
	$("#instructor-home-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
		if('Check Schedules' === ui.newHeader.text() ){
			$("#divCoursesSchedule").hide();
		}     
    }
 });
	
	
	
	$('#assignedCourses').DataTable( {
        "paging":   false,
        "ordering": false,
        "info":     false,
        "bFilter" : false
    } );
	

	
	loadCoursesScheduleDT([]);
	$('.getSchedule').on('click',function(){
		var courseId = $(this).attr('courseId');
		$.ajax({
			  type : "GET",
			  url: getContextRoot()+"/entry/instructor/courseSchedule.htm",
			  data:{'courseId':courseId},
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				$("#divCoursesSchedule").show();
				$("#courseName").text(courseId);
				coursesSchedule.destroy();
				loadCoursesScheduleDT(data.responseData.courseSchedule);
			    console.log(data.responseData.courseSchedule);
			  },
			  error: function(data){
				  
			  }
			});
	});
	
});


function loadCoursesScheduleDT(data){
	coursesSchedule = $('#coursesSchedule').DataTable( {
		"bProcessing": true,
		"aaData" : data,
        "paging":   false,
        "ordering": false,
        "info":     false,
        "bFilter" : false,
        "scrollY": "400px",
        "bscrollcollapse":true,
        "aoColumns": [
            {'mData': 'topicDate', 'sType': 'string', "bVisible": true},
            {'mData': 'topicName', 'sType': 'string', 'bVisible': true},
            {'mData': 'assignmentName', 'sType': 'string', 'bVisible': true},
            {'mData': 'assignmentDeadline', 'sType': 'string', 'bVisible': true},
            ]
    });
}