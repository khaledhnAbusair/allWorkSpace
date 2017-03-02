<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>


<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD1_ibV0oUOTvDORB1e9_h9A5bviZQEx0w&callback=initMap">
	
</script>

<script>
	$.ready(function() {

	});
	var map;
	function initMap() {
		var uluru = {
			lat : -25.363,
			lng : 131.044
		};
		map = new google.maps.Map(document.getElementById('map'), {
			zoom : 2,
			center : uluru
		});
		$.getJSON('/api/locations', function(result, status, ajax) {
			for (i = 0; i < result.length; i++) {
				var location = result[i];
				var marker = new google.maps.Marker({
					label : location.name,
					position : {
						lat : location.latitude,
						lng : location.longitude
					},
					map : map
				});
			}
		});
	}
</script>