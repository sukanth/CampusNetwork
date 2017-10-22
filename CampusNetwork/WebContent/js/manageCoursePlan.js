/**
 * 
 */

$(function() {
	$("#course-plan-accordion").accordion({ 
		heightStyle: "content",
		beforeActivate: function(event, ui) {
			if('Edit Course Plan' === ui.newHeader.text() ){
				$("#divCoursesSchedule").hide();
			}
	    }
	});

	$('#btnUpload').on('click',function(){
		$('#coursePlanForm').attr('action',getContextRoot()+"/entry/instructor/uploadCoursePlan.htm");
		$('#coursePlanForm').attr('method',"POST");
		$('#coursePlanForm').submit();
	});
	
	loadCoursesScheduleDT([]);
	$('#getSchedule').on('click',function(){
		var courseId = $('#pcourseId').val();
		if(courseId != ''){
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
				  },
				  error: function(data){
					  
				  }
				});
		}else{
			coursesSchedule.destroy();
			loadCoursesScheduleDT([]);
			$("#divCoursesSchedule").hide();
		}
		
	});
	
	
});


function loadCoursesScheduleDT(data){
	coursesSchedule = $('#coursesSchedule')
			.DataTable(
					{
						"bProcessing" : true,
						"aaData" : data,
						"paging" : false,
						"ordering" : false,
						"info" : false,
						"bFilter" : false,
						"scrollY" : "400px",
						"bscrollcollapse" : true,
						"aoColumns" : [
								{
									'mData' : 'topicDate',
									'sType' : 'string',
									"bVisible" : true,
									'mRender' : function(data, type, row) {
										txt = '<input type="text"  value="'+data+'" name = "topicDate" class="jDatePickers" style="width:100px;" />';
										return txt;
									}
								},
								{
									'mData' : 'topicName',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										txt = '<input type="text"  value="'+data+'"maxlength= "100" name = "topicName" style="width:350px;" />';
										return txt;
									}
								},
								{
									'mData' : 'assignmentName',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										txt = '<input type="text"  value="'+data+'" maxlength= "50" name = "assignmentName" style="width:250px;" />';
										return txt;
									}
								},
								{
									'mData' : 'assignmentDeadline',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										data = (data == 'null' || data == null) ? '' : data;
										txt = '<input type="text"  value="'+data+'" name = "assignmentDeadline" class="jDatePickers" style="width:100px;" />';
										return txt;
									}
								},
								{
									'mData' : 'uniqueId',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										txt = '<input type="button" uniqueId = "'+data+'" value="Update" Class="editData btn btn-lg btn-primary btn-block"style="width:100px;" />';
										return txt;
									}
								} ]
					});
	$('.jDatePickers').datepicker({
        changeMonth: true,
        numberOfMonths: 1,
        minDate: new Date(),
        dateFormat: 'mm/dd/yy'
    });
	
	$('.editData').on('click',function(){
		var input = {};
		var uniqueId = $(this).attr('uniqueId');
		input['uniqueId'] = uniqueId;
		input['topicName'] = $(this).parent().parent().find('input[name=topicName]').val();
		input['topicDate'] = $(this).parent().parent().find('input[name=topicDate]').val();
		input['assignmentName'] = $(this).parent().parent().find('input[name=assignmentName]').val();
		input['assignmentDeadline'] = $(this).parent().parent().find('input[name=assignmentDeadline]').val();
		$.ajax({
			  type : "GET",
			  url: getContextRoot()+"/entry/instructor/updateCourseSchedule.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				alert(data.responseData.success);
			  },
			  error: function(data){
				  
			  }
			});
	});
}


