package com.proyecto.gestorcine.repository;

import com.proyecto.gestorcine.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
