package com.example.eivenlou_subbook;

/**
 * Created by eivenlou on 2018-02-05.
 */

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Represents a subscription
 *
 * @author eivenlou
 *
 * version 1.0
 */

public abstract class Subscription implements Subscribable{
    private String name;
    private String date;
    private Double charge;
    private String comment;

    Subscription(String name, String date, Double charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameInvalidException {
        if ((name.length() <= 20)|| (name != null)) {
            this.name = name;
        }
        else {
            throw new NameInvalidException();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws DateInvalidException, ParseException {
        Date full_date = new SimpleDateFormat("YYYY/MM/DD").parse(date);
        Date now = new Date();
        if ((full_date.compareTo(now) < 0) || (full_date.compareTo(now) == 0) || (date != null)){
            this.date = date;
        } else {
            throw new DateInvalidException();
        }

    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) throws ChargeInvalidException {
        if (charge != null) {
            this.charge = charge;
        }
        else {
            throw new ChargeInvalidException();
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws CommentInvalidException {
        if (comment.length() <= 30) {
            this.comment = comment;
        }
        else {
            throw new CommentInvalidException();
        }
    }

    public String toString() {
        DecimalFormat rounded = new DecimalFormat("0.00");
        return name + "\n" + "$" + rounded.format(charge);
    }

}
