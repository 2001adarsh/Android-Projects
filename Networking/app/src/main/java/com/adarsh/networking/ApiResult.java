package com.adarsh.networking;

import java.util.ArrayList;

public class ApiResult {

    Integer total_count;
    Boolean incomplete_results;
    ArrayList<Details> items;

    public ApiResult() {
    }

    public ApiResult(Integer total_count, Boolean incomplete_results, ArrayList<Details> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<Details> getItems() {
        return items;
    }

    public void setItems(ArrayList<Details> items) {
        this.items = items;
    }
}
