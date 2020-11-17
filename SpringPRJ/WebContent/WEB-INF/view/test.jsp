<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	
<script>
  $(function(){
  $.ajax({
      url : "http://apis.data.go.kr/1741000/EmergencyAssemblyArea_Earthquake/getAreaList?serviceKey=jNvv%2FhQQ0GtPgbWU5%2Fx3oYY9ozWAdBNQZWovHiGsG1ay%2BcUw0AvcXcdD0EvdfIq7TuMZ0Wf7YLwxjQSYXUu1Nw%3D%3D&type=json&flag=N&pageNo=1&numOfRows=1000",
      dataType:"json",
      type:"get",
      success:function(data){
    	  for(var i=0; i<data.EarthquakeOutdoorsShelter[1].row.length;i++){
    	  console.log(data.EarthquakeOutdoorsShelter[1].row[i].vt_acmdfclty_nm);
    	  }
      }
     
    })
  })
	
</script>
</body>
</html>