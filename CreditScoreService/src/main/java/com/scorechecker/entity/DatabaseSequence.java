package com.scorechecker.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityScan
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DatabaseSequence {

    @Id
     String id;
     long seq;
}
