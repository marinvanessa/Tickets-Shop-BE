package com.example.tickets.convertor;


import com.example.tickets.dto.EventDto;
import com.example.tickets.dto.TicketDto;
import com.example.tickets.dto.UserDto;
import com.example.tickets.model.Event;
import com.example.tickets.model.Ticket;
import com.example.tickets.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Convertor {

   public UserDto mapUserToUserDto(User user) {
       UserDto dto = new UserDto();
       dto.setId(user.getId());
       dto.setRole(user.getRole());
       dto.setEmail(user.getEmail());
       dto.setFirstName(user.getFirstName());
       dto.setLastName(user.getLastName());
       return dto;
   }

}
