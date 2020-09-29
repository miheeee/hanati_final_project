<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- <script src="../static/vendor/jquery/jquery.min.js"></script>
	<script src="../static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script> -->
	
	<!-- CSS -->    
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.2/main.min.css">
	<!-- JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.2/main.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.2/locales-all.min.js"></script>
	<!-- Other -->
	<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.2/package.json"></script>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<!-- 아이콘 -->
	<script src="https://kit.fontawesome.com/925e80bab6.js"></script>


	<!-- 캘린더가 표현될 공간 -->
	<div id='calendar'></div>

	<!-- 특정 일을 눌렀을 때 띄워주는 모달 -->
	<div class="modal" id="myModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	      </div>
	      
	      <div class="modal-body">
	        <p>Modal body text goes here.</p>
	      </div>
	      
	      <div class="text-right mr-4 mb-2">
			<span style="font-size: 2em; color:navy;">
				<i class="fas fa-plus-circle"></i>
			</span>
	      </div>
	      
	    </div>
	  </div>
	</div>

    <script>
	<!-- initialize a calendar via browser globals -->
      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          /* initialView: 'dayGridMonth' */
          selectable: true,
          dateClick: function(info) {
        	    alert('Clicked on: ' + info.dateStr);
        	    
        	    $('#myModal').modal('show');
        	  }          
        });
        calendar.render();
      });

    </script>

    