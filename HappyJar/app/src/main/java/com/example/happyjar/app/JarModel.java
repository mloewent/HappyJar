package com.example.happyjar.app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Marty.Loewenthal & Vivian Fong on 2/25/14.
 */
public class JarModel
{
    private ArrayList<Thought> thoughts;

    public JarModel()
    {
        thoughts = new ArrayList<Thought>();
    }

    public void addThought(Thought t)
    {
        thoughts.add(0, t);
    }

    public Thought getThought()
    {
        if (thoughts.size() > 0)
        {
            Thought get = thoughts.remove(0);
            reinsertThought(get);
            return get;
        }
        return null;
    }

    public void reinsertThought(Thought t)
    {
        t.incrementCount();
        int min = t.getReads();

        if (min < 2)
        {
            min = 80;
        }
        else if (min < 3)
        {
            min = 60;
        }
        else
        {
            min = 20;
        }

        int insertIndex = randomBetween(min, 100);

        insertIndex = insertIndex * thoughts.size() / 100;

        thoughts.add(insertIndex, t);
    }

    public Set<String> getAllThoughts()
    {
        Set<String> strings = new HashSet<String>();

        for (Thought t : thoughts)
        {
            strings.add(t.getText() + " !/!" + t.getDate());
        }

        return strings;
    }

    public void addAllThoughts(Set<String> strings)
    {
        for (String s : strings)
        {
            thoughts.add(new Thought(s.split("!/!")[0], s.split("!/!")[1]));
        }
    }

    public int randomBetween(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
