<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ehotel.abs.report.VideoReport"%>
<%@page import="java.util.Vector"%>
<%@page import="ehotel.domain.report.DataReport"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	int year=2012;
	if(request.getParameter("year")!=null)
	{
		year=Integer.parseInt(request.getParameter("year").toString().trim());		
	}				
	VideoReport rmi=new VideoReport();
	Vector<DataReport> v_rs= rmi.getUsedFrequency_monthly(year);
 %>
<script type="text/javascript" src="javascript/charts/jquery.js"></script>
<script type="text/javascript" src="javascript/charts/highcharts.js"></script>
<script type="text/javascript"
	src="javascript/charts/modules/exporting.js"></script>
</head>
<script type="text/javascript">		
			var chart;
			var list=new Array();
			var categories=new Array();
			<%for(int i=0;i<v_rs.size();i++){
			System.out.println("name:"+v_rs.get(i).getName());
			%>	
				categories[<%=i%>]='<%=v_rs.get(i).getName()%>'	
				list[<%=i%>]=<%=v_rs.get(i).getQuanlity()%>;		
			<%}%>
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'column',						
					},
					title: {
						text: 'Monthly Average Video'
					},
					subtitle: {
						text: 'Source: Elcom'
					},
					xAxis: {
						categories: categories
					},
					yAxis: {
						min: 0,
						title: {
							text: ''
						}
					},
					legend: {
						layout: 'vertical',
						backgroundColor: '#FFFFFF',
						align: 'left',
						verticalAlign: 'top',
						x: 100,
						y: 70,
						floating: true,
						shadow: true
					},
					tooltip: {
						formatter: function() {
							return ''+
								this.x +': '+ this.y +' video';
						}
					},
					plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 0
						}
					},
				        series: [{
						name: 'Video',						
						data: list
					}]
				});
			});
		</script>

<body>
	<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>

</body>
</html>