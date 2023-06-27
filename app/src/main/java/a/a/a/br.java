package a.a.a;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.besome.sketch.beans.ComponentBean;
import com.besome.sketch.beans.EventBean;
import com.besome.sketch.beans.ProjectFileBean;
import com.besome.sketch.editor.LogicEditorActivity;
import com.besome.sketch.editor.component.CollapsibleComponentLayout;
import com.besome.sketch.editor.component.ComponentAddActivity;
import com.besome.sketch.editor.component.ComponentEventButton;
import com.besome.sketch.editor.event.CollapsibleButton;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import mod.hey.studios.util.Helper;

public class br extends qA implements View.OnClickListener {
    public ProjectFileBean f;
    public a h;
    public TextView i;
    public RecyclerView k;
    public FloatingActionButton n;
    public String o;
    public ArrayList<ComponentBean> g = new ArrayList<>();
    public boolean j = false;
    public final String l = "component";
    public final int m = 4;

    public void d() {
        if (this.f == null || this.h == null) {
            return;
        }
        this.g = jC.a(this.o).e(this.f.getJavaName());
        this.h.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 224 && i2 == -1) {
            d();
        }
    }

    @Override
    public void onClick(View view) {
        if (!mB.a() && view.getId() == 2131231054) {
            Intent intent = new Intent(getContext(), ComponentAddActivity.class);
            intent.putExtra("sc_id", this.o);
            intent.putExtra("project_file", this.f);
            startActivityForResult(intent, 224);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (getUserVisibleHint()) {
            if (menuItem.getItemId() != 4) {
                return true;
            }
            a(this.h.c);
            return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        if (view.getTag().equals("component")) {
            contextMenu.setHeaderTitle(xB.b().a(getContext(), 2131625093));
            contextMenu.add(0, 4, 0, xB.b().a(getContext(), 2131625094));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(2131427424, viewGroup, false);
        a(viewGroup2);
        setHasOptionsMenu(true);
        if (bundle != null) {
            this.o = bundle.getString("sc_id");
        } else {
            this.o = getActivity().getIntent().getStringExtra("sc_id");
        }
        return viewGroup2;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("sc_id", this.o);
        super.onSaveInstanceState(bundle);
    }

    class a extends RecyclerView.Adapter<br.a.ViewHolder> {
        public int c = -1;

        class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout A;
            public LinearLayout B;
            public ImageView t;
            public TextView u;
            public TextView v;
            public ImageView w;
            public CollapsibleComponentLayout x;
            public LinearLayout y;
            public LinearLayout z;

            public ViewHolder(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(2131231151);
                this.u = (TextView) view.findViewById(2131231926);
                this.v = (TextView) view.findViewById(2131231923);
                this.w = (ImageView) view.findViewById(2131231156);
                this.y = (LinearLayout) view.findViewById(2131231049);
                this.A = (LinearLayout) view.findViewById(2131230921);
                this.z = (LinearLayout) view.findViewById(2131230920);
                this.B = (LinearLayout) view.findViewById(2131230918);
                this.x = new CollapsibleComponentLayout(br.this.getContext());
                this.x.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                this.x.n.e.setText(xB.b().a(br.this.getContext(), 2131625094));
                this.z.addView(this.x);
                this.x.setButtonOnClickListener(v -> {
                    c = getLayoutPosition();
                    ComponentBean bean = jC.a(o).a(f.getJavaName(), c);
                    if (v instanceof CollapsibleButton) {
                        bean.isConfirmation = true;
                        notifyItemChanged(c);
                    } else {
                        int id = v.getId();
                        if (id == 2131230923) {
                            bean.isConfirmation = false;
                            notifyItemChanged(c);
                        } else if (id == 2131230927) {
                            jC.a(o).b(f.getJavaName(), bean);
                            bean.isConfirmation = false;
                            notifyItemRemoved(c);
                            notifyItemRangeChanged(c, getItemCount());
                        }
                    }
                });
                view.setOnClickListener(v -> {
                    c = getLayoutPosition();
                    ComponentBean bean = jC.a(o).a(f.getJavaName(), getLayoutPosition());
                    if (bean.isCollapsed) {
                        bean.isCollapsed = false;
                        E();
                    } else {
                        bean.isCollapsed = true;
                        D();
                    }
                });
                this.w.setOnClickListener(v -> {
                    c = getLayoutPosition();
                    ComponentBean bean = g.get(c);
                    if (bean.isCollapsed) {
                        bean.isCollapsed = false;
                        E();
                    } else {
                        bean.isCollapsed = true;
                        D();
                    }
                });
                view.setOnLongClickListener(v -> {
                    c = getLayoutPosition();
                    ComponentBean bean = g.get(c);
                    if (bean.isCollapsed) {
                        bean.isCollapsed = false;
                        E();
                    } else {
                        bean.isCollapsed = true;
                        D();
                    }
                    return true;
                });
            }

            public void D() {
                gB.a(this.w, 0.0f, (Animator.AnimatorListener) null);
                gB.a((ViewGroup) this.A, 200, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        A.setVisibility(8);
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {
                    }
                });
                this.y.animate().translationX(0.0f).alpha(1.0f).setStartDelay(120L).setDuration(150L).start();
                this.B.animate().translationX(-this.B.getWidth()).setDuration(150L).alpha(0.0f).start();
            }

            public void E() {
                this.A.setVisibility(0);
                gB.a(this.w, -180.0f, (Animator.AnimatorListener) null);
                gB.b(this.A, 200, null);
                this.y.animate().translationX(this.y.getWidth()).alpha(0.0f).setDuration(150L).start();
                LinearLayout linearLayout = this.B;
                linearLayout.setTranslationX(-linearLayout.getWidth());
                this.B.setAlpha(0.0f);
                this.B.animate().translationX(0.0f).setStartDelay(200L).setDuration(120L).alpha(1.0f).start();
            }
        }

        public a(RecyclerView recyclerView) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 2) {
                            if (n.isEnabled()) {
                                n.hide();
                            }
                        } else if (dy < -2) {
                            if (n.isEnabled()) {
                                n.show();
                            }
                        }
                    }
                });
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ComponentBean componentBean = br.this.g.get(position);
            holder.u.setText(ComponentBean.getComponentName(br.this.getContext(), componentBean.type));
            holder.t.setImageResource(ComponentBean.getIconResource(componentBean.type));
            int i2 = componentBean.type;
            if (i2 == 2) {
                TextView textView = holder.v;
                textView.setText(componentBean.componentId + " : " + componentBean.param1);
            } else if (i2 != 6 && i2 != 14 && i2 != 16) {
                holder.v.setText(componentBean.componentId);
            } else {
                String str = componentBean.param1;
                if (str.length() <= 0) {
                    str = "/";
                }
                TextView textView2 = holder.v;
                textView2.setText(componentBean.componentId + " : " + str);
            }
            ArrayList<EventBean> a2 = jC.a(br.this.o).a(br.this.f.getJavaName(), componentBean);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(oq.a(componentBean.getClassInfo())));
            holder.y.removeAllViews();
            holder.B.removeAllViews();
            holder.y.setAlpha(1.0f);
            holder.y.setTranslationX(0.0f);
            if (componentBean.isCollapsed) {
                holder.A.setVisibility(8);
                holder.w.setRotation(0.0f);
            } else {
                holder.A.setVisibility(0);
                holder.w.setRotation(-180.0f);
                if (componentBean.isConfirmation) {
                    holder.x.b();
                } else {
                    holder.x.a();
                }
            }
            holder.A.getLayoutParams().height = -2;
            Iterator<EventBean> it = a2.iterator();
            while (it.hasNext()) {
                EventBean next = it.next();
                if (arrayList.contains(next.eventName)) {
                    LinearLayout linearLayout = (LinearLayout) wB.a(br.this.getContext(), 2131427435);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, 0, (int) wB.a(br.this.getContext(), 4.0f), 0);
                    linearLayout.setLayoutParams(layoutParams);
                    ((ImageView) linearLayout.findViewById(2131231090)).setImageResource(oq.a(next.eventName));
                    ((LinearLayout) linearLayout.findViewById(2131231092)).setBackgroundResource(2131165429);
                    ComponentEventButton componentEventButton = new ComponentEventButton(br.this.getContext());
                    componentEventButton.e.setText(next.eventName);
                    componentEventButton.c.setImageResource(oq.a(next.eventName));
                    componentEventButton.setClickListener(v -> {
                        if (!mB.a()) {
                            a(next.targetId, next.eventName, next.eventName);
                        }
                    });
                    holder.y.addView(linearLayout);
                    holder.B.addView(componentEventButton);
                    arrayList.remove(next.eventName);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                LinearLayout linearLayout2 = (LinearLayout) wB.a(br.this.getContext(), 2131427435);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.setMargins(0, 0, (int) wB.a(br.this.getContext(), 4.0f), 0);
                linearLayout2.setLayoutParams(layoutParams2);
                ImageView imageView = (ImageView) linearLayout2.findViewById(2131231090);
                imageView.setImageResource(oq.a(str2));
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setSaturation(0.0f);
                imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                holder.y.addView(linearLayout2);
                linearLayout2.setScaleX(0.8f);
                linearLayout2.setScaleY(0.8f);
                ComponentEventButton componentEventButton2 = new ComponentEventButton(br.this.getContext());
                componentEventButton2.a();
                componentEventButton2.e.setText(str2);
                componentEventButton2.c.setImageResource(oq.a(str2));
                componentEventButton2.setClickListener(v -> {
                    if (!mB.a()) {
                        EventBean event = new EventBean(2, componentBean.type, componentBean.componentId, str2);
                        jC.a(o).a(f.getJavaName(), event);
                        bB.a(getContext(), xB.b().a(getContext(), 2131625331), 0).show();
                        componentEventButton2.b();
                        notifyItemChanged(c);
                        a(event.targetId, event.eventName, event.eventName);
                    }
                });
                holder.B.addView(componentEventButton2);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(2131427433, parent, false));
        }

        @Override
        public int getItemCount() {
            int size = br.this.g.size();
            if (size == 0) {
                br.this.i.setVisibility(0);
            } else {
                br.this.i.setVisibility(8);
            }
            return size;
        }
    }

    public void c() {
        if (this.f == null) {
            return;
        }
        Iterator<ComponentBean> it = jC.a(this.o).e(this.f.getJavaName()).iterator();
        while (it.hasNext()) {
            it.next().initValue();
        }
        this.h.notifyDataSetChanged();
    }

    public final void a(ViewGroup viewGroup) {
        this.i = (TextView) viewGroup.findViewById(2131231017);
        this.k = (RecyclerView) viewGroup.findViewById(2131230919);
        this.k.setHasFixedSize(true);
        this.i.setVisibility(8);
        this.i.setText(xB.b().a(getContext(), 2131625126));
        this.k.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.h = new a(this.k);
        this.k.setAdapter(this.h);
        this.n = (FloatingActionButton) viewGroup.findViewById(2131231054);
        this.n.setOnClickListener(this);
    }

    public void a(ProjectFileBean projectFileBean) {
        this.f = projectFileBean;
    }

    public final void a(int i) {
        aB aBVar = new aB(this.a);
        aBVar.b(xB.b().a(getContext(), 2131625094));
        aBVar.a(2131165524);
        aBVar.a(xB.b().a(getContext(), 2131625326));
        aBVar.b(xB.b().a(getContext(), 2131624986), v -> {
            ComponentBean component = jC.a(o).a(f.getJavaName(), i);
            jC.a(o).b(f.getJavaName(), component);
            a("Remove", component.type, 0);
            d();
            bB.a(getContext(), xB.b().a(getContext(), 2131624935), 0).show();
            aBVar.dismiss();
        });
        aBVar.a(xB.b().a(getContext(), 2131624974), Helper.getDialogDismissListener(aBVar));
        aBVar.show();
    }

    public final void a(String str, int i, long j) {
        String str2 = "INTENT";
        if (i != 1) {
            if (i == 2) {
                str2 = "FILE";
            } else if (i == 3) {
                str2 = "CALENDAR";
            } else if (i == 4) {
                str2 = "VIBRATOR";
            } else if (i == 5) {
                str2 = "TIMER";
            } else if (i == 6) {
                str2 = "FIREBASE";
            } else if (i == 12) {
                str2 = "FIREBASEAUTH";
            } else if (i == 7) {
                str2 = "DIALOG";
            } else if (i == 8) {
                str2 = "MEDIAPLAYER";
            } else if (i == 9) {
                str2 = "SOUNDPOOL";
            } else if (i == 10) {
                str2 = "OBJECTANIMATOR";
            } else if (i == 11) {
                str2 = "GYROSCOPE";
            } else if (i == 14) {
                str2 = "FIREBASESTORAGE";
            } else if (i == 15) {
                str2 = "CAMERA";
            } else if (i == 16) {
                str2 = "FILEPICKER";
            } else if (i == 17) {
                str2 = "REQUESTNETWORK";
            } else if (i == 18) {
                str2 = "TEXTTOSPEECH";
            } else if (i == 19) {
                str2 = "SPEECHTOTEXT";
            } else if (i == 20) {
                str2 = "BLUETOOTHCONNECT";
            } else if (i == 21) {
                str2 = "LOCATIONMANAGER";
            }
        }
        a("Component", str, str2, j);
    }

    public final void a(String str, String str2, String str3, long j) {
        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
        eventBuilder.setCategory(str);
        eventBuilder.setAction(str2);
        eventBuilder.setLabel(str3);
        eventBuilder.setValue(j);
        this.c.send(eventBuilder.build());
    }

    public final void a(String str, String str2, String str3) {
        Intent intent = new Intent(getActivity(), LogicEditorActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("sc_id", this.o);
        intent.putExtra("id", str);
        intent.putExtra("event", str2);
        intent.putExtra("project_file", this.f);
        intent.putExtra("event_text", str3);
        startActivity(intent);
    }
}
