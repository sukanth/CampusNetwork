/**
 * 
 */

$(function() {
	
	$("#uploadMarks-accordion").accordion({ 
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
	
	
	$('#btnUpload').on('click',function(){
		$('#uploadMarksForm').attr('action',getContextRoot()+"/entry/Performance/uploadMarksData.htm");
		$('#uploadMarksForm').attr('method',"POST");
		$('#uploadMarksForm').submit();
	});
	
});




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

