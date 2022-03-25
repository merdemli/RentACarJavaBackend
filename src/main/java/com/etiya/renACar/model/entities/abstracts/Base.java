package com.etiya.renACar.model.entities.abstracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Base {

        @JsonIgnore
        @Column(name = "createdAt",  columnDefinition = "Date default CURRENT_DATE")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate createdAt =  LocalDate.now();

        @Column(name = "isActive", columnDefinition = "boolean default true")
        private boolean isActive = true;

        @Column(name = "isDeleted", columnDefinition = "boolean default false")
        private boolean isDeleted = false;

}
