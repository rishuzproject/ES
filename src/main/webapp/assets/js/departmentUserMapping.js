/**
 * Anantha Meghana Jagruthi
 */

var userslist="";
var userIdSelected="";
var staffSelected="";
$(document).ready(function(){
	
	getUsersListByDomain();
	initialiseDropdownlist(userslist);
});


function initialiseDropdownlist(userslist)
{
	$('#initializeRole').find('option').remove().end();
	var usersInfo = document.getElementById("initializeRole");
	console.log(usersInfo);
	for (var i = 0; i < userslist.length; i++) {
		
			var option = document.createElement("option");
			option.text = userslist[i].nickName;
			option.value = userslist[i].userId;
			try {
				usersInfo.add(option, select.options[null]);
			} catch (e) {
				usersInfo.add(option, null);
			}
		}
	
	$('#initializeStaffList').find('option').remove().end();
    usersInfo = document.getElementById("initializeStaffList");
	for (var i = 0; i < userslist.length; i++) {
		
			var option = document.createElement("option");
			option.text = userslist[i].nickName;
			option.value = userslist[i].userId;
			try {
				usersInfo.add(option, select.options[null]);
			} catch (e) {
				usersInfo.add(option, null);
			}
		}
}


$('#initializeRole').on('change', function (e) {
	console.log($("#initializeRole").val());
	if($("#initializeRole").val()!=null&&$("#bootstrap-duallistbox-selected-list_initializeRole").val()==null)
		userIdSelected=$("#initializeRole").val();
	else
      userIdSelected=$("#bootstrap-duallistbox-selected-list_initializeRole").val();
    if(userIdSelected!=null&&userIdSelected!=""&&userIdSelected!=undefined)
    {
	  updateUsers(userIdSelected,"depHead");
    }
});


$('#initializeStaffList').on('change', function (e) {
	console.log($("#initializeStaffList").val());
	if($("#initializeStaffList").val()!=null&&$("#bootstrap-duallistbox-selected-list_initializeStaffList").val()==null)
		staffSelected=$("#initializeStaffList").val();
	else
		staffSelected=$("#bootstrap-duallistbox-selected-list_initializeStaffList").val();
    if(staffSelected!=null&&staffSelected!="" && staffSelected!=undefined)
     {
	   updateUsers(staffSelected,"depStaff");
     }
});


function updateUsers(userIdSelected,id)
{
	var selectedUserDetails=[];
	var count=0;
	var  resultString="";
	for(var i=0;i<userslist.length&&count<userIdSelected.length;i++)
		{
		  
		   if(parseInt(userIdSelected[count])== userslist[i].userId)
			   {
			   
			   if(count<userIdSelected.length-1)
			   resultString+=userslist[i].nickName+" , ";
			   else
				   resultString+=userslist[i].nickName;
			   selectedUserDetails.push(userslist[i].nickName);
			   count++;
			   }
		   
		}
	if(id=="depHead")
	$("#"+id).html(resultString+'<i class="fa fa-edit txt-color-blue toggleDualListBox"></i>');
	else
		$("#"+id).html(resultString+'<i class="fa fa-edit txt-color-blue toggleDualListBox1"></i>');

	
}



function getUsersListByDomain()
{
	
	$.ajax({
		url:"getUserDetailsByDomain",
		method:"POST",
		async:false,
		success:function(result)
		{
			userslist=JSON.parse(result).usersList;
			
		}
	});
}