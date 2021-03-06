package im.adamant.android.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import im.adamant.android.R;
import im.adamant.android.core.entities.ServerNode;
import im.adamant.android.rx.ObservableRxList;
import im.adamant.android.ui.holders.ServerNodeHolder;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class ServerNodeAdapter extends RecyclerView.Adapter<ServerNodeHolder> {
    private PublishSubject<Integer> removeClickSubject = PublishSubject.create();
    private ObservableRxList<ServerNode> items = new ObservableRxList<>();
    private Disposable subscription;

    public ServerNodeAdapter(ObservableRxList<ServerNode> items) {
        if (items != null){
            this.items = items;
            subscription = items
                    .getObservable()
                    .subscribe(serverNodeRxList -> {
                        notifyDataSetChanged();
                    });
        }
    }

    @NonNull
    @Override
    public ServerNodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_servernodes, parent, false);
        return new ServerNodeHolder(v, parent.getContext(), removeClickSubject);
    }

    @Override
    public void onBindViewHolder(@NonNull ServerNodeHolder holder, int position) {
        ServerNode item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }


    public Observable<ServerNode> getRemoveObservable() {
        return removeClickSubject
                .map(i -> items.get(i));
    }

}
