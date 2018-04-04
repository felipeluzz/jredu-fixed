package br.com.developer.redu.models;

import java.util.List;

public class Progress {
    public String id;
    public String finalized;
    public String updated_at;

    public List<Link> links;

    @Override
    public String toString(){
        return String.format("id: %s\nfinalized: %s\nupdated_at: %s\nlinks: %s",
                id, finalized, updated_at, links);
    }
}
