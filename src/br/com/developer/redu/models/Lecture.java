package br.com.developer.redu.models;

import java.util.List;

public class Lecture {
    public String created_at;
    public String name;
    public String position;
    public String type;
    public String id;
    public String rating;
    public String view_count;
    public String updated_at;

    public List<Link> links;

    @Override
    public String toString(){
        return String.format("created_at: %s\nname: %s\nposition: %s\ntype: %s\nid: %s\nrating: %s\nview_count: %s\nupdated_at: %s\nlinks: %s",
                created_at, name, position, type, id, rating, view_count, updated_at, links);
    }
}
