/**
 * 
 */

$(function() {
	$('#divIndividualPerformance').hide();
	loadPerformanceDT([]);
	$("#view-performance-accordion").accordion({ 
		heightStyle: "content"
	});


	$('#viewPerformance').on('click',function(){
		$('#divIndividualPerformance').hide();
		var input = {};
		input['courseId'] = $('#pcourseId').val();
		
		$.ajax({
			  type : "GET",
			  url: getContextRoot()+"/entry/Performance/getMyPerformance.htm",
			  data:input,
			  dataType: 'json',
			  cache: false,
			  success: function(data){
				  individualPerformance.destroy();
				  $('#divIndividualPerformance').show();
				  $('#lblCourseId').text($('#pcourseId').val());
				  loadPerformanceDT(data.responseData.marks);
			  },
			  error: function(data){
				  
			  }
			});
	});

	
});

	

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