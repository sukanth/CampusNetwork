/**
 * 
 */

$(function() {
	$('#divMarks').hide();
	$("#uploadMarks-accordion").accordion({ 
		heightStyle: "content"
 });

	loadUploadMarksDT([]);
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
	
	$('#getMarks').on('click',function(){
		var input = {};
		input['courseId'] = $('#courseId').val();
		input['topicName']   = $('#assessment').val();
		if($('#courseId').val() != '' && $('#assessment').val() != '') {
			$.ajax({
				  type : "get",
				  url: getContextRoot()+"/entry/Performance/getStudentsMarks.htm",
				  data:input,
				  dataType: 'json',
				  cache: false,
				  success: function(data){
					  $('#divMarks').show	();
					  $('#hCourseId').val(input['courseId']);
					  $('#hTopicName').val(input['topicName']);
					  uploadMarks.destroy();
					  loadUploadMarksDT(data.responseData.marks);
				  },
				  error: function(data){
					  
				  }
				});
		}else{
			alert('Please select the assessment.');
		}
	});
	
	$('#totalMarks').on('keyup',function(){
		var totalMarks = $(this).val();
		$('.totalMarks').val(totalMarks);
		$('.marksObtained').attr('max',totalMarks);
		$('#uploadMarks tbody tr').each(function (i,e) {
			var marks = $(this).find('input[name = marksObtained]').val();
			var percentage = Math.round((marks *100)/totalMarks);
			$(this).find('input[name = percentage]').val(percentage);
		});
	});
	
	$('#btnUpload').on('click',function(){
		var array =[];
		$('#uploadMarks tbody tr').each(function (i,e) {
			var input = {};
			input['sso'] = uploadMarks.row( this ).data().sso;
			input['marksObtained'] = $(this).find('input[name = marksObtained]').val();
			input['totalmarks'] = $(this).find('input[name = totalmarks]').val();
			input['comments'] = $(this).find('input[name = comments]').val();
			var percentage = Math.round((input['marksObtained'] *100)/input['totalmarks']);
			input['percentage'] = (percentage) ? percentage: 0;
			input['courseId'] =  $('#hCourseId').val();
			input['topicName'] =$('#hTopicName').val();
			array.push(input);
		});
		var data = JSON.stringify(array);
		var myval = {};
		myval['data'] = data;
		$.ajax({
			  type : "post",
			  url: getContextRoot()+"/entry/Performance/uploadMarksData.htm",
			  data : myval,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  alert('Marks Uploaded Successfully.');
			  },
			  error: function(data){
				  
			  }
			});
	});
	
});

function loadUploadMarksDT(data){
	uploadMarks = $('#uploadMarks')
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
									'mData' : 'sso',
									'sType' : 'string',
									"bVisible" : true
								},
								{
									'mData' : 'studentName',
									'sType' : 'string',
									"bVisible" : true
								},
								{
									'mData' : 'totalmarks',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										data = (data == 'null' || data == null) ? '0' : data;
										txt = '<input type="text"  value="'+data+'" readonly = "readonly" name = "totalmarks" class="totalMarks" style="width:100px;" />';
										return txt;
									}
								},
								{
									'mData' : 'marksObtained',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										data = (data == 'null' || data == null) ? '0' : data;
										txt = '<input type="number" value="'+data+'"  maxlength= "3" name = "marksObtained" class="marksObtained" min ="0" max="'+row.totalmarks+'" style="width:100px;" />';
										return txt;
									}
								},
								{
									'mData' : 'percentage',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										data = (data == 'null' || data == null) ? '0' : data;
										txt = '<input type="text"  value="'+data+'" readonly = "readonly" name = "percentage" class="" style="width:100px;" />';
										return txt;
									}
								},
								{
									'mData' : 'comments',
									'sType' : 'string',
									'bVisible' : true,
									'mRender' : function(data, type, row) {
										data = (data == 'null' || data == null) ? '' : data;
										txt = '<input type="text"  value="'+data+'" name = "comments"  maxlength= "200" class="" style="width:150px;" />';
										return txt;
									}
								}
							]
					});
	
	$('.marksObtained').bind('keyup change',function(){
		if (parseInt($(this).val()) <= parseInt($(this).attr('max')) && parseInt($(this).val()) >= 0)
			$(this).val(Math.round($(this).val()));
	    else
	      $(this).val(0);
		
		var marksObtained = $(this).val();
		var totalMarks = $(this).closest('tr').find('input[name = totalmarks]').val();
		if(parseInt(totalMarks) > 0){
			var percentage = Math.round((marksObtained *100)/totalMarks);
			$(this).closest('tr').find('input[name = percentage]').val(percentage);
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

