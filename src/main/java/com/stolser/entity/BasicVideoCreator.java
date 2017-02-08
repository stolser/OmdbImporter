package com.stolser.entity;

import com.stolser.search.SingleVideoResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class BasicVideoCreator implements VideoCreator {
    private static final String N_A = "N/A";
    private static final String YEAR_SEPARATOR = "–";
    private static final String COMMA_SEPARATOR = ", ";
    private static final int MIN_IN_HOUR = 60;
    private static final String DASH_SIGN = "-";
    private static final String UNDERSCORE_SIGN = "_";
    private static final String COMMA_SIGN = ",";
    private static final String EMPTY_STRING = "";
    private static Pattern hourPattern = Pattern.compile("(\\d+)\\sh");
    private static Pattern minutePattern = Pattern.compile("(\\d+)\\smin");

    public static void main(String[] args) {
        System.out.println("result = " + Arrays.toString("N/A".split(", ")));
    }

    protected Video fillCommonVideoFields(Video video, SingleVideoResult json) {
        video.setImdbId(parseImdbId(json.getImdbId()));
        video.setType(parseMediaType(json.getType()));
        video.setTitle(json.getTitle());
        video.setYear(parseYear(json.getYear()));
        video.setMpaaRating(parseMpaaRating(json.getRated()));
        video.setReleaseDate(parseReleaseDate(json.getReleased()));
        video.setRuntime(parseRuntime(json.getRuntime()));
        video.setGenres(parseListOfEnums(json.getGenre(), Video.Genre.class));
        video.setDirectors(parseListOfStrings(json.getDirector()));
        video.setWriters(parseListOfStrings(json.getWriter()));
        video.setActors(parseListOfStrings(json.getActors()));
        video.setPlot(json.getPlot());
        video.setLanguages(parseListOfStrings(json.getLanguage()));
        video.setCountries(parseListOfStrings(json.getCountry()));
        video.setAwards(json.getAwards());
        video.setPoster(json.getPoster());
        video.setMetascore(parseIntField("Metascore", json.getMetascore()));
        video.setImdbRating(parseImdbRating(json.getImdbRating()));
        video.setImdbVotes(parseImdbVotes(json.getImdbVotes()));
        video.setTomatoesRating(parseTomatoesRating(video, json));

        return video;
    }

    private String parseImdbId(String imdbId) {
        if (isNotAvailable(imdbId)) {
            throw new VideoValidationException("ImdbId must be present.");
        }

        return imdbId;
    }

    private Video.Type parseMediaType(String type) {
        if (isNotAvailable(type)) {
            throw new VideoValidationException("Video media type must be present.");
        }

        try {
            return Video.Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new VideoValidationException(
                    String.format("Video media type (%s) cannot be parsed.", type), e);
        }
    }

    private Year parseYear(String yearStr) {
        if (isNotAvailable(yearStr)) {
            return null;
        }

        Year year = new Year();
        Year.Type type = yearStr.contains(YEAR_SEPARATOR) ? Year.Type.RANGE : Year.Type.SINGLE;
        year.setType(type);

        String[] entries = yearStr.split(YEAR_SEPARATOR);
        year.setBegin(entries[0]);

        if (entries.length == 2) {
            year.setEnd(entries[1]);
        }

        year.setFinished(isYearClosedRange(type, entries));

        return year;
    }

    private Video.MpaaRating parseMpaaRating(String rated) {
        if (isNotAvailable(rated)) {
            return null;
        }

        try {
            return Video.MpaaRating.valueOf(getNormalizedEnumValue(rated));
        } catch (IllegalArgumentException e) {
            throw new VideoValidationException(
                    String.format("MpaaRating (%s) cannot be parsed.", rated), e);
        }
    }

    private Date parseReleaseDate(String released) {
        if (isNotAvailable(released)) {
            return null;
        }

        try {
            Date date = new SimpleDateFormat("dd MMM YYYY", Locale.US).parse(released);
            return date;
        } catch (ParseException e) {
            throw new VideoValidationException(
                    String.format("Released date (%s) cannot be parsed.", released));
        }
    }

    Runtime parseRuntime(String runtimeStr) {
        if (isNotAvailable(runtimeStr)) {
            return null;
        }

        Runtime runtime = new Runtime();
        runtime.setOriginal(runtimeStr);
        runtime.setMinutes(getTotalMinutes(
                parseHours(runtimeStr),
                parseMinutes(runtimeStr)));

        return runtime;
    }

    private <T extends Enum<T>> List<T> parseListOfEnums(String entries, Class<T> tClass) {
        if (isNotAvailable(entries)) {
            return new ArrayList<>();
        }

        return Arrays.asList(entries.split(COMMA_SEPARATOR))
                .stream()
                .map(entry -> {
                    entry = entry.replaceAll(DASH_SIGN, UNDERSCORE_SIGN);
                    return Enum.valueOf(tClass, entry.toUpperCase());
                })
                .collect(Collectors.toList());
    }

    private List<String> parseListOfStrings(String entries) {
        if (Objects.isNull(entries) || isNotAvailable(entries)) {
            return new ArrayList<>();
        }

        return Arrays.asList(entries.split(COMMA_SEPARATOR));
    }

    int parseIntField(String fieldName, String fieldValue) {
        if (isNotAvailable(fieldValue)) {
            return -1;
        }

        try {
            return Integer.parseInt(fieldValue);
        } catch (NumberFormatException e) {
            throw new VideoValidationException(
                    String.format("%s (%s) cannot be parsed.", fieldName, fieldValue), e);
        }
    }

    private double parseImdbRating(String imdbRating) {
        if (isNotAvailable(imdbRating)) {
            return -1.0;
        }

        try {
            return Double.parseDouble(imdbRating);
        } catch (NumberFormatException e) {
            throw new VideoValidationException(
                    String.format("ImdbRating (%s) cannot be parsed.", imdbRating), e);
        }
    }

    private long parseImdbVotes(String imdbVotes) {
        if (isNotAvailable(imdbVotes)) {
            return -1;
        }

        try {
            return Long.parseLong(replaceDigitalSeparators(imdbVotes));
        } catch (NumberFormatException e) {
            throw new VideoValidationException(
                    String.format("ImdbVotes (%s) cannot be parsed.", imdbVotes), e);
        }
    }

    private TomatoesRating parseTomatoesRating(Video video, SingleVideoResult json) {
        TomatoesRating tomato = new TomatoesRating();
        tomato.setTomatoMeter(json.getTomatoMeter());
        tomato.setTomatoImage(json.getTomatoImage());
        tomato.setTomatoRating(json.getTomatoRating());
        tomato.setTomatoReviews(json.getTomatoReviews());
        tomato.setTomatoFresh(json.getTomatoFresh());
        tomato.setTomatoRotten(json.getTomatoRotten());
        tomato.setTomatoConsensus(json.getTomatoConsensus());
        tomato.setTomatoUserMeter(json.getTomatoUserMeter());
        tomato.setTomatoUserRating(json.getTomatoUserRating());
        tomato.setTomatoUserReviews(json.getTomatoUserReviews());
        tomato.setTomatoUrl(json.getTomatoUrl());
        tomato.setDvdRelease(json.getDvd());
        tomato.setBoxOffice(json.getBoxOffice());
        tomato.setProduction(json.getProduction());
        tomato.setWebsite(json.getWebsite());
        tomato.setVideo(video);

        return tomato;
    }

    boolean isNotAvailable(String field) {
        return N_A.equalsIgnoreCase(field);
    }

    private boolean isYearClosedRange(Year.Type type, String[] entries) {
        return type == Year.Type.SINGLE
                || entries.length == 2;
    }

    private String getNormalizedEnumValue(String rated) {
        return rated.replaceAll(DASH_SIGN, UNDERSCORE_SIGN).toUpperCase();
    }

    private int getTotalMinutes(int hours, int minutes) {
        return hours * MIN_IN_HOUR + minutes;
    }

    private int parseHours(String runtimeStr) {
        int hours = 0;
        Matcher hourMatcher = hourPattern.matcher(runtimeStr);

        if (hourMatcher.find()) {
            hours = Integer.parseInt(hourMatcher.group(1));
        }

        return hours;
    }

    private int parseMinutes(String runtimeStr) {
        int minutes = 0;

        Matcher minuteMatcher = minutePattern.matcher(runtimeStr);
        if (minuteMatcher.find()) {
            minutes = Integer.parseInt(minuteMatcher.group(1));
        }

        return minutes;
    }

    private String replaceDigitalSeparators(String imdbVotes) {
        return imdbVotes.replaceAll(COMMA_SIGN, EMPTY_STRING);
    }

    @Override
    public Video create(SingleVideoResult jsonVideo) {
        throw new UnsupportedOperationException();
    }
}
