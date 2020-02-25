<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- Styles -->
<style>
	article{
		display: flex; /*바로안에있는것만적용됨*/
        flex-wrap: wrap;
		width:80%;
		margin:auto;
	}
	
	.left-aside{
/* 		background-color: EBFBFF; */
        width: 20%;
        margin-top:150px;
	}

	section{
/* 		background-color: C8FFFF; */
		flex-grow:1;
		margin-top:150px;
	}
	
	#chartdiv {
	  width: 100%;
	  height: 500px;
	}
</style>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("chartdiv", am4charts.XYChart);
chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

chart.data = [
  {
    country: '${list[0].dt}', 
    visits: ${list[0].count}
  },
  {
    country: '${list[1].dt}',
    visits: ${list[1].count}
  },
  {
    country: '${list[2].dt}',
    visits: ${list[2].count}
  },
  {
    country: '${list[3].dt}',
    visits: ${list[3].count}
  },
  {
    country: '${list[4].dt}',
    visits: ${list[4].count}
  },
  {
    country: '${list[5].dt}',
    visits: ${list[5].count}
  },
  {
    country: '${list[6].dt}',
    visits: ${list[6].count}
  }
];

var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.dataFields.category = "country";
categoryAxis.renderer.minGridDistance = 40;
categoryAxis.fontSize = 11;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.min = 0;
valueAxis.max = ${max};
valueAxis.strictMinMax = true;
valueAxis.renderer.minGridDistance = 40;
// axis break
var axisBreak = valueAxis.axisBreaks.create();
axisBreak.startValue = ${max}/4;
axisBreak.endValue = ${max}/4*3;
//axisBreak.breakSize = 0.005;

// fixed axis break
var d = (axisBreak.endValue - axisBreak.startValue) / (valueAxis.max - valueAxis.min);
axisBreak.breakSize = 0.05 * (1 - d) / d; 

// make break expand on hover
var hoverState = axisBreak.states.create("hover");
hoverState.properties.breakSize = 1;
hoverState.properties.opacity = 0.1;
hoverState.transitionDuration = 1500;

axisBreak.defaultState.transitionDuration = 1000;
/*
// this is exactly the same, but with events
axisBreak.events.on("over", function() {
  axisBreak.animate(
    [{ property: "breakSize", to: 1 }, { property: "opacity", to: 0.1 }],
    1500,
    am4core.ease.sinOut
  );
});
axisBreak.events.on("out", function() {
  axisBreak.animate(
    [{ property: "breakSize", to: 0.005 }, { property: "opacity", to: 1 }],
    1000,
    am4core.ease.quadOut
  );
});*/

var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.categoryX = "country";
series.dataFields.valueY = "visits";
series.columns.template.tooltipText = "{valueY.value}";
series.columns.template.tooltipY = 0;
series.columns.template.strokeOpacity = 0;

// as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
series.columns.template.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
});

}); // end am4core.ready()
</script>

<!-- HTML -->
<article>
	<aside class="left-aside">	
		<jsp:include page="/WEB-INF/views/template/adminmenu.jsp"></jsp:include>
	</aside>
	
	<section>
		<div id="chartdiv"></div>
	</section>
</article>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>