package com.filmproject.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Setter
@Getter
@NoArgsConstructor
public class MovieDto {
    @Schema(defaultValue = "null",nullable = true,example = "null")
    private Integer id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    @Schema(format = "yyyy-MM-dd HH:mm",example = "2022-04-11 16:55")
    private String release_date;

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @JsonIgnore
    public Date getSubmissionDateConverted() throws ParseException {
        return dateFormat.parse(this.release_date);
    }

    @JsonIgnore
    public void setSubmissionDate(Date date) {
        this.release_date = dateFormat.format(date);
    }


}
