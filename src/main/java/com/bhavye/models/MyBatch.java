package com.bhavye.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="batch_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyBatch {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "batch_sequence", sequenceName = "batch_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_sequence")
    private Integer id;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "average")
    private Double average;

    @Column(name = "minimum")
    private Integer minimum;

    @Column(name = "maximum")
    private Integer maximum;

}
