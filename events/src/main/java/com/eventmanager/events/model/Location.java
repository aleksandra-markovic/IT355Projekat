package com.eventmanager.events.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Location {
    private String city;
    private String address;
    private String locationName;
}
