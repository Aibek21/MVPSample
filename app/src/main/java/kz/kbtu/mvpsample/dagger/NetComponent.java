package kz.kbtu.mvpsample.dagger;

import javax.inject.Singleton;

import dagger.Component;
import kz.kbtu.mvpsample.mosby.MosbyActivity;
import kz.kbtu.mvpsample.mosby.MosbyPresenter;
import kz.kbtu.mvpsample.ui.MainActivity;
import kz.kbtu.mvpsample.presenter.MainPresenter;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(MosbyActivity activity);

    MainPresenter mainPresenter();
    MosbyPresenter mosbyPresenter();
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}