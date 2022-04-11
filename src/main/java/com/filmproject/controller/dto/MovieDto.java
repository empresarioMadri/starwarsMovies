package com.filmproject.controller.dto;

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
    private Integer id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Date getSubmissionDateConverted() throws ParseException {
        return dateFormat.parse(this.release_date);
    }

    public void setSubmissionDate(Date date) {
        this.release_date = dateFormat.format(date);
    }


}
