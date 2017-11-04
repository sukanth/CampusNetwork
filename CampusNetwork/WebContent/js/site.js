/**
 * 
 */

function getContextRoot (){
	return $("#contextRoot").val();
}

$(function() {
	$('.errMsg').hide();
	$('.hideMsg').on('click',function(){
		$('.errMsg').hide();
	});
});

function loadResponse (data,successMsg){
	
	if(data.responseData.errMsg != undefined){
		  $('.errMsgSpan').text(data.responseData.errMsg);
		  $('.errMsg').removeClass('alert-success').addClass('alert-danger').show();
	  }else{
		  $('.errMsgSpan').text(successMsg);
		  $('.errMsg').removeClass('alert-danger').addClass('alert-success').show();
	  }
	
}