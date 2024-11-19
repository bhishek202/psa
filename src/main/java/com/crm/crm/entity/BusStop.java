package com.crm.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bus_stop")
public class BusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_stop_id")
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Stop stop;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

}