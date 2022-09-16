package com.billow.domain.entity.program;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    private String title;

    private String age;

    @Column(length = 1000)
    private String summary;

    private String broadcastingDay;

    private String broadcastingEpisode;

    private String broadcastingStation;

    private boolean endFlag;

    private Float averageRating;

    private String posterImg;

    private String backdropPath;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Genre> genreList = new ArrayList<>();

    @Builder

    public Program(Long id, String title, String age, String summary, String broadcastingDay, String broadcastingEpisode, String broadcastingStation, boolean endFlag, Float averageRating, String posterImg, String backdropPath, List<Genre> genreList) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.summary = summary;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingEpisode = broadcastingEpisode;
        this.broadcastingStation = broadcastingStation;
        this.endFlag = endFlag;
        this.averageRating = averageRating;
        this.posterImg = posterImg;
        this.backdropPath = backdropPath;
        this.genreList = genreList;
    }
}
