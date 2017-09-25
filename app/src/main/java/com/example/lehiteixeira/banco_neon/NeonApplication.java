package com.example.lehiteixeira.banco_neon;

import android.app.Application;

import com.example.lehiteixeira.banco_neon.Data.Model.Person;

import java.util.ArrayList;

/**
 * Created by Lehiteixeira on 04/09/17.
 */

public class NeonApplication extends Application {
    private String someVariable;
    private  static ArrayList<Person> persons = new ArrayList<Person>();

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(){
        // populate static list
        persons.add(new Person("1","Lehi Lima Fonseca","(11)93456-1789",0.00,R.drawable.eu));
        persons.add(new Person("2","John Cloude Vandaime","(11)98445-1456",0.00,R.drawable.john_cloude_vandaime));
        persons.add(new Person("3","Rock Balboa","(11)92278-8799",0.00,0));
        persons.add(new Person("4","Night King ","(11)93154-3040",0.00,R.drawable.night_king));
        persons.add(new Person("5","John Targaryan","(11)98664-1288",0.00,R.drawable.john_snow));
        persons.add(new Person("6","Aegon Targaryan","(11)98792-4558",0.00,0));
        persons.add(new Person("7","Danarys StormBorn","(11)95497-6548",0.00,R.drawable.danarys_targaryan));
        persons.add(new Person("8","Milesandre Lay Fire","(11)99874-7946",0.00,R.drawable.milissandre));
        persons.add(new Person("9","Sansa Stark","(11)98797-4658",0.00,R.drawable.sansa_stark));
        persons.add(new Person("10","Cersei Lannister","(11)93456-3001",0.00,R.drawable.cersei_lannister));
        persons.add(new Person("11","Baby Groot","(11)93456-3001",0.00,R.drawable.groot));
        persons.add(new Person("12","Darth Vader","(11)93456-3001",0.00,R.drawable.dath_vader));
        persons.add(new Person("13","Chewbacca","(11)93456-3001",0.00,R.drawable.chewbacca));
        persons.add(new Person("14","Tyrion Lannister","(11)93456-3001",0.00,R.drawable.tyrion_lannister));
        persons.add(new Person("15","Iron Man","(11)93456-3001",0.00,R.drawable.iron_man));
    }


}
