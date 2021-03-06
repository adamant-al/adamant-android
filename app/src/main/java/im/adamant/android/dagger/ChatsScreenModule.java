package im.adamant.android.dagger;

import im.adamant.android.Screens;
import im.adamant.android.interactors.ChatsInteractor;
import im.adamant.android.presenters.ChatsPresenter;
import im.adamant.android.ui.adapters.ChatsAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import im.adamant.android.ui.fragments.ChatsScreen;
import io.reactivex.disposables.CompositeDisposable;
import ru.terrakok.cicerone.Router;

@Module
public class ChatsScreenModule {
    @FragmentScope
    @Provides
    public ChatsPresenter provideChatsPresenter(
            Router router,
            ChatsInteractor interactor,
            @Named(Screens.CHATS_SCREEN) CompositeDisposable subscriptions
    ){
        return new ChatsPresenter(router,interactor,subscriptions);
    }

    @FragmentScope
    @Provides
    @Named(value = Screens.CHATS_SCREEN)
    public CompositeDisposable provideComposite() {
        return new CompositeDisposable();
    }

    @FragmentScope
    @Provides
    public ChatsAdapter provideAdapter(ChatsScreen chatsScreen){
        return new ChatsAdapter(null, chatsScreen);
    }
}
