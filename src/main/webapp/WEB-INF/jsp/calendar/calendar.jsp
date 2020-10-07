<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='${ pageContext.request.contextPath }/resources/fullCalendar/packages/core/main.css' rel='stylesheet' />
<link href='${ pageContext.request.contextPath }/resources/fullCalendar/packages/daygrid/main.css' rel='stylesheet' />
<link href='${ pageContext.request.contextPath }/resources/fullCalendar/packages/timegrid/main.css' rel='stylesheet' />
<script src='${ pageContext.request.contextPath }/resources/fullCalendar/packages/core/main.js'></script>
<script src='${ pageContext.request.contextPath }/resources/fullCalendar/packages/interaction/main.js'></script>
<script src='${ pageContext.request.contextPath }/resources/fullCalendar/packages/daygrid/main.js'></script>
<script src='${ pageContext.request.contextPath }/resources/fullCalendar/packages/timegrid/main.js'></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      defaultDate: '2020-10-12',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
    	  //alert(info.dateStr);
    	  
    	  let today = new Date();
    	  let year = today.getFullYear(); // 년도
    	  let month = today.getMonth() + 1;  // 월
    	  let date = today.getDate();  // 날짜
    	  let day = today.getDay();  // 요일
    	  let hours = today.getHours(); // 시
    	  let s = year + '년 ' + month + '월 ' + date + '일'	
    	  			+ ((hours+1 >= 12)? '오후 ': '오전 ') + ((hours+1 >= 12)? hours+1-12: hours+1) + ':00';
    	  let e = year + '년 ' + month + '월 ' + date + '일'	
			+ ((hours+2 >= 12)? '오후 ': '오전 ') + ((hours+2 >= 12)? hours+2-12: hours+2) + ':00';

 		$("#edit-start").val(s);
		$("#edit-end").val(e); 

    	$("#eventModal").modal('show');
        $("#save-event").click(function(){
            var title = $("#edit-title").val();
        	if(title){
                calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay
                })        		
        	}
        	$("#eventModal").modal('hide');            
        })
        calendar.unselect()
      },
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      events: [
/*         {
          title: '회비 입금일',
          start: '2020-10-05',
       	  color: '#D25565;',   // an option!
       	  textColor: 'white' // an option!
        }, */
        {
          title: '길동 생일',
          start: '2020-10-16',
       	  color: '#9775fa;',   // an option!
       	  textColor: 'white' // an option!
        },
        {
          title: '모임',
          start: '2020-10-13T18:00:00',
			color: '#63e6be;',   // an option!
			textColor: 'white' // an option!
        },
        {
            title: '번개',
            start: '2020-10-04T19:00:00',
  			color: '#ffa94d;',   // an option!
  			textColor: 'white' // an option!
          },
         {
             title: '철수 생일',
             start: '2020-10-09T19:00:00',
   			color: '#f06595;',   // an option!
   			textColor: 'white' // an option!
           }
      ]
    });
    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

	label {
		width: 50px;
		margin-left: 10px;
		/* vertical-align: text-top; */
	}
	
</style>
</head>
<body>

  <div id='calendar'></div>
  
  <!-- 일정 추가 MODAL -->
  <div class="modal fade" tabindex="-1" role="dialog" id="eventModal">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h4 class="modal-title">새로운 일정</h4>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                          aria-hidden="true">&times;</span></button>
              </div>
              <div class="modal-body">

                  <div class="row">
                      <div class="col-xs-12">
                          <label class="col-xs-4" for="edit-title">일정명</label>
                          <input class="inputModal" type="text" name="edit-title" id="edit-title"
                              required="required" />
                      </div>
                  </div>
                  
                  <div class="row">
                      <div class="col-xs-12">
                          <label class="col-xs-4" for="edit-start">시작</label>
                          <input class="inputModal" type="text" name="edit-start" id="edit-start" />
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-xs-12">
                          <label class="col-xs-4" for="edit-end">끝</label>
                          <input class="inputModal" type="text" name="edit-end" id="edit-end" />
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-xs-12">
                          <label class="col-xs-4" for="edit-desc" id="memoLable">메모</label>
                          <textarea rows="4" cols="50" class="inputModal" name="edit-desc"
                              id="edit-desc"></textarea>
                      </div>
                  </div>
              </div>
              <div class="modal-footer modalBtnContainer-addEvent">
                  <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                  <button type="button" class="btn btn-primary" id="save-event">저장</button>
              </div>
          </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->  

</body>
</html>
