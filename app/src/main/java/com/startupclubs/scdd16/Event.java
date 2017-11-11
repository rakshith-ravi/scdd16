package com.startupclubs.scdd16;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public static class Agenda {
        private String timing;
        private String title;

        public String getTiming() {
            return timing;
        }
        public void setTiming(String timing) {
            this.timing = timing;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }
    public static class Speaker {
        private Bitmap photo;
        private String name;
        private String about;

        public Bitmap getPhoto() {
            return photo;
        }
        public void setPhoto(Bitmap photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getAbout() {
            return about;
        }
        public void setAbout(String about) {
            this.about = about;
        }
    }
    public static class Sponsor {
        private String name;
        private Bitmap logo;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public Bitmap getLogo() {
            return logo;
        }
        public void setLogo(Bitmap logo) {
            this.logo = logo;
        }
    }

    private static List<Event> events = new ArrayList<>();

    private String city;
    private String title;
    private String year;
    private String date;
    private String day;
    private List<Agenda> agendas = new ArrayList<>();
    private List<Speaker> speakers = new ArrayList<>();
    private List<Sponsor> sponsors = new ArrayList<>();

    //Event stuff
    public static Event getEvent(int position) {
        return events.get(position);
    }
    public static void setEvent(int position, Event event) {
        events.set(position, event);
    }

    public static List<Event> getEvents() {
        return events;
    }

    public static int eventsSize() {
        return events.size();
    }

    public static void addEvent(Event event) {
        events.add(event);
    }
    public static void addEvent(int position, Event event) {
        events.add(position, event);
    }

    public static void removeEvent(int position) {
        events.remove(position);
    }
    public static void removeEvent(Event event) {
        events.remove(event);
    }

    public static int indexOf(Event event) {
        return events.indexOf(event);
    }

    public static void clear() {
        events.clear();
    }
    //Event stuff

    //Agenda stuff
    public Agenda getAgenda(int position) {
        return agendas.get(position);
    }
    public void setAgenda(int position, Agenda agenda) {
        agendas.set(position, agenda);
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public int agendaSize() {
        return agendas.size();
    }

    public void addAgenda(Agenda agenda) {
        agendas.add(agenda);
    }
    public void addAgenda(int position, Agenda agenda) {
        agendas.add(position, agenda);
    }

    public void removeAgenda(int position) {
        agendas.remove(position);
    }
    public void removeAgenda(Agenda agenda) {
        agendas.remove(agenda);
    }

    public int indexOf(Agenda agenda) {
        return agendas.indexOf(agenda);
    }

    public void clearAgendas() {
        agendas.clear();
    }
    //Agenda stuff

    //Speaker stuff
    public Speaker getSpeaker(int position) {
        return speakers.get(position);
    }
    public void setSpeaker(int position, Speaker Speaker) {
        speakers.set(position, Speaker);
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public int speakerSize() {
        return speakers.size();
    }

    public void addSpeaker(Speaker Speaker) {
        speakers.add(Speaker);
    }
    public void addSpeaker(int position, Speaker Speaker) {
        speakers.add(position, Speaker);
    }

    public void removeSpeaker(int position) {
        speakers.remove(position);
    }
    public void removeSpeaker(Speaker Speaker) {
        speakers.remove(Speaker);
    }

    public int indexOf(Speaker Speaker) {
        return speakers.indexOf(Speaker);
    }

    public void clearSpeakers() {
        speakers.clear();
    }
    //Speaker stuff

    //Sponsor stuff
    public Sponsor getSponsor(int position) {
        return sponsors.get(position);
    }
    public void setSponsor(int position, Sponsor Sponsor) {
        sponsors.set(position, Sponsor);
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public int sponsorSize() {
        return sponsors.size();
    }

    public void addSponsor(Sponsor Sponsor) {
        sponsors.add(Sponsor);
    }
    public void addSponsor(int position, Sponsor Sponsor) {
        sponsors.add(position, Sponsor);
    }

    public void removeSponsor(int position) {
        sponsors.remove(position);
    }
    public void removeSponsor(Sponsor Sponsor) {
        sponsors.remove(Sponsor);
    }

    public int indexOf(Sponsor Sponsor) {
        return sponsors.indexOf(Sponsor);
    }

    public void clearSponsors() {
        sponsors.clear();
    }
    //Sponsor stuff

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
}
