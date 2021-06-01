package com.twovet.websocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twovet.catalog.dto.EventDTO;
import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.model.RegistrationExamination;
import com.twovet.catalog.services.implement.RegisExaminationService;

@Controller
public class WebSocketSendToController {
	
	@Autowired 
	RegisExaminationService eventService;
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputMessage send(Message message) throws Exception {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		OutputMessage mess = new OutputMessage(message.getFrom(), "updated",time);
		return mess;
	}
	
//	@MessageMapping("/calendar")
//	@SendTo("/show/event")
//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	@ResponseBody
	public ResponseEntity<?> getEventForMonth(WsParam wsParam) {
		String startTime = wsParam.getStart();
		String endTime = wsParam.getEnd();
		List<RegistrationExaminationDTO> events = new ArrayList<>();
		RegistrationExamination param = new RegistrationExamination();
		param.setStartTimeStr(startTime);
		param.setEndTimeStr(endTime);
		events = eventService.searchAdvance(param, 0, 0);
		ModelMapper mm = new ModelMapper();
		List<EventDTO> result = events.stream()
				.map(cus -> mm.map(cus, EventDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
	
}
	
