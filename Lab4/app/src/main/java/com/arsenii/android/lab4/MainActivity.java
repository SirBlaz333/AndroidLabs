package com.arsenii.android.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends Activity implements ExpandableListView.OnChildClickListener {
    private final List<Group> groupList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initGroups();
        ExpandableListView listView = findViewById(R.id.demoListView);
        SimpleExpandableListAdapter adapter =
                new SimpleExpandableListAdapter(
                        this,
                        createGroupList(),
                        R.layout.group_row,
                        new String[] {"Group item"},
                        new int[] {R.id.row_name},

                        createChildList(),
                        R.layout.child_row,
                        new String[] {"Child item"},
                        new int[] {R.id.child_name}
                        );
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(this);
    }

    private void initGroups() {
        Group web = new Group("Web");
        web.addChild("Molfar Tech", WebActivity.class);
        Group map = new Group("Map");
        map.addChild("Display Object on Map", MapActivity.class);
        Group phone = new Group("Phone");
        phone.addChild("Telephone", PhoneActivity.class);

        groupList.addAll(List.of(web, map, phone));
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition,
                                int childPosition, long l) {
        Group group = groupList.get(groupPosition);
        Class<? extends Activity> action = group.actions.get(childPosition);
        if (action != null) {
            Intent intent = new Intent(getApplicationContext(), action);
            intent.setAction(Intent.ACTION_VIEW);
            try {
                startActivity(intent);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return true;
    }


    private static class Group {
        String name;
        List<String> children = new ArrayList<>();
        List<Class<? extends Activity>> actions = new ArrayList<>();

        public Group(String name) {
            this.name = name;
        }

        private void addChild(String name, Class<? extends Activity> action) {
            children.add(name);
            actions.add(action);
        }
    }

    private List<Map<String, String>> createGroupList() {
        return groupList.stream().map(group -> {
            Map<String, String> map = new HashMap<>();
            map.put("Group item", group.name);
            return map;
        }
        ).collect(Collectors.toList());
    }

    private List<List<Map<String, String>>> createChildList() {
        return groupList.stream().map(group -> {
                List<Map<String, String>> childList = new ArrayList<>();
                for(String name : group.children) {
                    Map<String, String> map = new HashMap<>();
                    map.put("Child item", name);
                    childList.add(map);
                }
                return childList;
            }
        ).collect(Collectors.toList());
    }
}
