package com.progi.progi.model;

public class ArticleFront {
    private String elementName;
    private String closetName;
    private String articleName;
    private String text;
    private String image;
    private boolean forSharing;
    private String category;
    private String season;
    private String color;
    private String secondaryColor;
    private String formality;
    private String condition;
    private String description;

    public ArticleFront() {
    }

    public ArticleFront(String elementName, String closetName, String articleName, String text, String image, boolean forSharing, String category, String season, String color, String secondaryColor, String formality, String condition, String description) {
        this.elementName = elementName;
        this.closetName = closetName;
        this.articleName = articleName;
        this.text = text;
        this.image = image;
        this.forSharing = forSharing;
        this.category = category;
        this.season = season;
        this.color = color;
        this.secondaryColor = secondaryColor;
        this.formality = formality;
        this.condition = condition;
        this.description = description;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getClosetName() {
        return closetName;
    }

    public void setClosetName(String closetName) {
        this.closetName = closetName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isForSharing() {
        return forSharing;
    }

    public void setForSharing(boolean forSharing) {
        this.forSharing = forSharing;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getFormality() {
        return formality;
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
