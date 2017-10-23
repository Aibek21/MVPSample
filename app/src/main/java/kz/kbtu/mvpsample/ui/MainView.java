package kz.kbtu.mvpsample.ui;

import java.util.List;

import kz.kbtu.mvpsample.data.Post;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

public interface MainView {

    void refreshList(List<Post> posts);
}
