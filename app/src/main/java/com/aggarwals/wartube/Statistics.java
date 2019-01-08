
package com.aggarwals.wartube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("etag")
    @Expose
    private String etag;

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;

    @SerializedName("items")
    @Expose
    private InnerData[] items;

    public Statistics(String etag, String kind, PageInfo pageInfo, InnerData[] items) {
        this.etag = etag;
        this.kind = kind;
        this.pageInfo = pageInfo;
        this.items = items;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public InnerData[] getItems() {
        return items;
    }

    public void setItems(InnerData[] items) {
        this.items = items;
    }


    class PageInfo {
        private int totalResults;
        private int resultsPerPage;

        public PageInfo(int totalResults, int resultsPerPage) {
            this.totalResults = totalResults;
            this.resultsPerPage = resultsPerPage;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(int resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }

    class InnerData{
        private String kind;
        private String etag;
        private String id;
        private InnerStatistics statistics;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public InnerStatistics getStatistics() {
            return statistics;
        }

        public void setStatistics(InnerStatistics statistics) {
            this.statistics = statistics;
        }
    }

    class InnerStatistics{
        private String viewCount;
        private int commentCount;
        private String subscriberCount;
        private String hiddenSubscriberCount;
        private String videoCount;

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getSubscriberCount() {
            return subscriberCount;
        }

        public void setSubscriberCount(String subscriberCount) {
            this.subscriberCount = subscriberCount;
        }

        public String getHiddenSubscriberCount() {
            return hiddenSubscriberCount;
        }

        public void setHiddenSubscriberCount(String hiddenSubscriberCount) {
            this.hiddenSubscriberCount = hiddenSubscriberCount;
        }

        public String getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(String videoCount) {
            this.videoCount = videoCount;
        }
    }
}