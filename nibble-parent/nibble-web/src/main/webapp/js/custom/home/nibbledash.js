;nibbledash = {
	weeklyChart : null,
	name 	: 'nibbledash',
	
	init 	: function () {
		$('#home-content').load('views/home/'+nibbledash.name+'.html', function(){
			nibbledash.initwidgets();
		});
	},
	
	initwidgets : function(){
		var request = {};
		request.type = 'GET';
		request.url = nibblemain.getServicesUrl()+'/rest/weeksummary';
		request.done = function(data){		
			// init gauge
			var config = liquidFillGaugeDefaultSettings();
		    config.circleColor = "#FA8E09";
		    config.textColor = "#FFF";
		    config.waveTextColor = "#FA8E09";
		    config.waveColor = "rgba(151,187,205,0.5)";
		    config.circleThickness = 0.2;
		    config.textVertPosition = 0.5;
		    config.waveAnimateTime = 500;
		    config.waveRiseTime = 2000;
		    loadLiquidFillGauge("fillgauge", data.currentTargetPercent, config);
			nibblemain.hideWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			
			//init table
			$.extend( $.fn.dataTable.defaults, {
			    "searching": false,
			    "ordering": false
			} );			
			var trxtable = $('#trxtable').DataTable({
				responsive: true,
				bLengthChange : false,
				iDisplayLength : 5
			});			
			for(var i=0; i<data.trxs.length; i++){
				var place = '';
				if(data.trxs[i].city!=null && data.trxs[i].state!=null) place = data.trxs[i].city+', '+data.trxs[i].state;
				else if(data.trxs[i].city == null && data.trxs[i].state!=null) place = data.trxs[i].state;
				else if(data.trxs[i].city != null && data.trxs[i].state==null) place = data.trxs[i].city;
				else place = 'Unknown';
				trxtable.row.add([
				               
				               data.trxs[i].institutionName+' ..'+data.trxs[i].accountNumber,
				               '$ '+data.trxs[i].trxAmount,
				               '$ '+data.trxs[i].roundupAmount,
				               place
				                  ]).draw();
			}
			
			// gauge labels
			$('#gauge-label-text-label-left').html('<p>your weekly target</p>');
			$('#gauge-label-text-left').html('$ '+ data.weeklyTarget);
			$('#gauge-label-text-label-right').html('<p>you have accumulated</p>');
			$('#gauge-label-text-right').html('$ '+ data.currentWeekAmount);
			
			// Line chart
	        var randomScalingFactor = function(){ return Math.round(Math.random()*10)};
	        var chartdata = {
	          labels : ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],
	          datasets : [
				{
					  label: "Average week",
				      fillColor : "rgba(250,142,9,0.2)",
				      strokeColor : "rgba(250,142,9,0.4)",
				      pointColor: "rgba(250,142,9,0.4)",
				      pointStrokeColor: "#fff",
				      pointHighlightFill: "#fff",
				      highlightFill : "rgba(250,142,9,0.4)",
				      highlightStroke : "rgba(250,142,9,1)",
				      pointHighlightStroke: "rgba(250,142,9,1)",
				  data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				},
		          {
		        	  label: "Current week",
		            fillColor : "rgba(151,187,205,0.6)",
		            strokeColor : "rgba(151,187,205,0.8)",
		            pointColor: "rgba(151,187,205,0.8)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            highlightFill : "rgba(151,187,205,0.8)",
		            highlightStroke : "rgba(151,187,205,0.8)",
		            pointHighlightStroke: "rgba(151,187,205,1)",
		            data : [data.day0total,
		                    	data.day1total,
		                    	data.day2total,
		                    	data.day3total,
		                    	data.day4total,
		                    	data.day5total,
		                    	data.day6total]
		          }
	          
	          ]

	        };
	        
	        var ctx = $("#nibble-weekly-chart").get(0).getContext("2d");
	        nibbledash.weeklyChart = new Chart(ctx).Line(chartdata, {
	      	  maintainAspectRatio: true,
	    	  responsive: true,
	    	  scaleShowVerticalLines: false,
	    	  scaleFontSize: 18,
	    	  tooltipFontStyle: "normal",
	    	  scaleFontFamily: "'Ubuntu', sans-serif",
	    	  scaleFontColor: "rgb(250,142,9)",
	    	  scaleFontStyle: "normal",
	    	  scaleShowGridLines : false,
	    	  tooltipFontSize: 15,
	    	  tooltipTemplate: "<%if (label){%><%=label %>: <%}%><%= '$ '+value %>",
	    	  multiTooltipTemplate: "<%= '$ '+value %>",
	    	   tooltipTitleFontFamily: "'Ubuntu', sans-serif",
	    		 tooltipTitleFontSize: 18,
	    		 tooltipTitleFontStyle: "bold",
	    		 tooltipTitleFontColor: "rgb(250,142,9)",		
	    		 tooltipYPadding: 15,
	    		 tooltipXPadding: 10,
	    		 tooltipCaretSize: 8,
	    		 tooltipCornerRadius: 6,
	    		 tooltipXOffset: 10
	       });
	        
	      //then you just need to generate the legend
//	      var legend = nibbledash.weeklyChart.generateLegend();
//	      $("#nibble-weekly-chart").append(legend);
		};	
		nibblemain.jsonasync(request);
	}
};