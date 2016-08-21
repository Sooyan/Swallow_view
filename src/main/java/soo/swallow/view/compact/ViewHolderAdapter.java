/*
 * Copyright 2015 Soo [154014022@qq.com | sootracker@gmail.com]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 */
package soo.swallow.view.compact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ViewHolderAdapter<T, VH extends ViewHolderAdapter.ViewHolder<T>> extends BaseAdapter {
    
    public static abstract class ViewHolder<T> {

        protected Context context;
        protected ViewGroup parent;
        public ViewHolderAdapter<T, ? extends ViewHolder<T>> adapter;

        public abstract View onCreateView();

        public abstract void onBindData(T data, int position);
    }
    
    private Context context;
    
    private List<T> lisT = new ArrayList<T>();
    
    private int testCount = 0;
    
    public ViewHolderAdapter(Context context) {
        this.context = context;
    }

    public void setTestCase(int count) {
        this.testCount = count;
    }
    
    public void add(T data) {
        add(data, false);
    }
    
    public void add(T data, boolean head) {
        if (data == null) {
            return;
        }
        if (head) {
            lisT.add(0, data);
        } else {
            lisT.add(data);
        }
        lisT = onDataChanged(lisT);
        notifyDataSetChanged();
    }
    
    public void add(Collection<T> data) {
        add(data, false);
    }
    
    public void add(Collection<T> data, boolean head) {
        if (data == null) {
            return;
        }
        if (head) {
            lisT.addAll(0, data);
        } else {
            lisT.addAll(data);
        }
        lisT = onDataChanged(lisT);
        notifyDataSetChanged();
    }
    
    public void setNewData(Collection<T> data) {
        lisT.clear();
        add(data);
    }
    
    public void removeData(T data) {
        if (data == null) {
            return;
        }
        lisT.remove(data);
        lisT = onDataChanged(lisT);
        notifyDataSetChanged();
    }
    
    public void removeData(Collection<T> data) {
        if (data == null) {
            return;
        }
        lisT.removeAll(data);
        lisT = onDataChanged(lisT);
        notifyDataSetChanged();
    }
    
    public List<T> getData() {
        return lisT;
    }
    
    protected List<T> onDataChanged(List<T> data) {
        return data;
    }
    
    @Override
    public int getCount() {
        if (testCount > 0) {
            return testCount;
        }
        return lisT.size();
    }
    
    @Override
    public Object getItem(int position) {
        if (testCount > 0) {
            return null;
        }
        return lisT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VH viewHolder;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent, position);

            viewHolder.context = context;
            viewHolder.parent = parent;
            viewHolder.adapter = this;

            convertView = viewHolder.onCreateView();
            if (convertView == null) {
                throw new IllegalArgumentException("The itemView is null " + viewHolder.getClass().getSimpleName());
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag();
        }

        Object obj = getItem(position);
        T t = null;
        if (obj != null) {
            t = (T) obj;
        }
        viewHolder.onBindData(t, position);

        return convertView;
    }
    
    protected abstract VH onCreateViewHolder(ViewGroup parent, int position);
    
    public Context getContext() {
        return context;
    }
    
}
